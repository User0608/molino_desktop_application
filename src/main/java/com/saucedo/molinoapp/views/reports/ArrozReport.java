package com.saucedo.molinoapp.views.reports;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;

import com.saucedo.molino_json_models.reportes.RArroz;
import com.saucedo.molino_json_models.reportes.RIngresoArroz;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.ReportService;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.IMenu;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ArrozReport extends JPanel implements IMenu, ItemListener {

	private static final long serialVersionUID = 323434341L;
	public static final String THIS_WINDOWS_TITLE_VIEW_ONE = "Reportes, Arroz";
	private JMenuItem homeView;
	IMainContainer parent;
	private JComboBox<String> comboBox;

	private ReportService service;
	private JTable tableArroz;
	private JTable tableDos;

	public ArrozReport(IMainContainer parent) {
		this.service = new ReportService();
		this.parent = parent;
		this.homeView = new JMenuItem("Arroz");
		setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Reportes Arroz");
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(337, 25, 433, 21);
		mainPanel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(47, 79, 311, 411);
		mainPanel.add(scrollPane);

		tableArroz = new JTable();
		scrollPane.setViewportView(tableArroz);

		JLabel lblNewLabel_1 = new JLabel("Procedencia y tipo de arroz mas ingresados");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(47, 54, 299, 14);
		mainPanel.add(lblNewLabel_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(510, 79, 311, 411);
		mainPanel.add(scrollPane_1);

		tableDos = new JTable();
		scrollPane_1.setViewportView(tableDos);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ingreso de arroz por mes");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(510, 57, 299, 14);
		mainPanel.add(lblNewLabel_1_1);

		JPanel hederPanel = new JPanel();
		add(hederPanel, BorderLayout.NORTH);
		hederPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		JLabel lblYear = new JLabel("Año:  ");
		hederPanel.add(lblYear);

		comboBox = new JComboBox<String>();
		hederPanel.add(comboBox);
		comboBox.addItemListener(this);
		Component horizontalStrut = Box.createHorizontalStrut(50);
		hederPanel.add(horizontalStrut);
		this.loadActions();
		this.loadYears();

	}

	private void loadYears() {
		try {
			List<String> years = this.service.findAllIngresoYears();
			this.comboBox.setModel(new DefaultComboBoxModel<String>(years.toArray(new String[0])));
			if (years.size() > 0) {
				this.loadDataToTable(years.get(0));
				this.loadDataToTableDos(years.get(0));
			}
		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}

	private void loadActions() {
		this.homeView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setMainPanel(ArrozReport.this, THIS_WINDOWS_TITLE_VIEW_ONE);
			}
		});
	}

	private String[] headerTable() {
		String[] response = { "Procedencia", "Tipo de arroz", "Sacos" };
		return response;
	}

	private void loadDataToTable(String year) {
		try {
			List<RArroz> arrozs = this.service.findAllArroz(year);
			String[][] dataTable = new String[arrozs.size()][this.headerTable().length];
			int i = 0;
			for (RArroz a : arrozs) {
				dataTable[i][0] = a.getProcedencia();
				dataTable[i][1] = a.getTipoArroz();
				dataTable[i][2] = a.getSacos();
				i++;
			}
			DefaultTableModel modeltable = new DefaultTableModel();
			modeltable.setDataVector(dataTable, this.headerTable());
			this.tableArroz.setModel(modeltable);
			this.tableArroz.repaint();

		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}

	private String[] headerTableDos() {
		String[] response = { "Mes", "Sacos" };
		return response;
	}

	private void loadDataToTableDos(String year) {
		try {
			List<RIngresoArroz> arrozs = this.service.findAllArrozIngreso(year);
			String[][] dataTable = new String[arrozs.size()][this.headerTableDos().length];
			int i = 0;
			for (RIngresoArroz a : arrozs) {
				dataTable[i][0] = a.getMes();
				dataTable[i][1] = a.getSacos();
				i++;
			}
			DefaultTableModel modeltable = new DefaultTableModel();
			modeltable.setDataVector(dataTable, this.headerTableDos());
			this.tableDos.setModel(modeltable);
			this.tableDos.repaint();

		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JMenuItem getMenuItem() {
		return this.homeView;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String item = (String) e.getItem();
			this.loadDataToTable(item);
			this.loadDataToTableDos(item);
		}
	}
}