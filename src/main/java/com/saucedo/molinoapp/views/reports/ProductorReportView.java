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

import com.saucedo.molino_json_models.RProductor;
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

public class ProductorReportView extends JPanel implements IMenu, ItemListener {

	private static final long serialVersionUID = 323434341L;
	public static final String THIS_WINDOWS_TITLE_VIEW_ONE = "Productores";
	private JMenuItem homeView;
	IMainContainer parent;
	private JComboBox<String> comboBox;

	private ReportService service;
	private JTable table;

	public ProductorReportView(IMainContainer parent) {
		this.service = new ReportService();
		this.parent = parent;
		this.homeView = new JMenuItem("Productores");
		setLayout(new BorderLayout(0, 0));

		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Los clientes que mas arroz trajeron");
		lblNewLabel.setForeground(new Color(0, 139, 139));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(208, 33, 433, 21);
		mainPanel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(47, 79, 728, 411);
		mainPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

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
			if(years.size()>0) this.loadDataToTable(years.get(0));
		} catch (ResponseException e) {
			e.printStackTrace();
		}
	}

	private void loadActions() {
		this.homeView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setMainPanel(ProductorReportView.this, THIS_WINDOWS_TITLE_VIEW_ONE);
			}
		});
	}
	private String[] headerTable() {
		String[] response = {"DNI","Nombre","Apellidos","Telefon","Numero de Sacos"};
		return response;
	}

	private void loadDataToTable(String year) {
		try {
			List<RProductor> productores = this.service.findAllProductores(year);
			String[][] dataTable = new String[productores.size()][this.headerTable().length];
			int i=0;
			for(RProductor p:productores) {
				dataTable[i][0] = p.getDni();
				dataTable[i][1] = p.getNombre();
				dataTable[i][2] = p.getApellidoPaterno()+" "+p.getApellidoMaterno();
				dataTable[i][3] = p.getTelefon();
				dataTable[i][4] = p.getSacos().toString();
				i++;
			}
			DefaultTableModel modeltable = new DefaultTableModel();
			modeltable.setDataVector(dataTable, this.headerTable());
			this.table.setModel(modeltable);
			this.table.repaint();
			
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
		}
	}
}