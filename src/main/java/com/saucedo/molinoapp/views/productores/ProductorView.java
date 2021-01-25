package com.saucedo.molinoapp.views.productores;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.almacen.ProductorService;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.IMenu;
import com.saucedo.molinoapp.views.components.KToolbar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ProductorView extends JPanel implements IMenu,KToolbar.ButtonActionToolbar{
	private boolean huboErrorAlCargar;
	private JMenuItem menuitem;
	private KToolbar toolbar;
	private ProductorService productorService;
	private static final long serialVersionUID = 54541L;
	IMainContainer parent;
	private JTable table;

	public ProductorView(IMainContainer parent) {
		this.huboErrorAlCargar=false;
		this.parent=parent;
		this.menuitem = new JMenuItem("Productores");
		setLayout(new BorderLayout(0, 0));
		this.productorService = new ProductorService();
		table = new JTable();
		add(table, BorderLayout.CENTER);
		initializeComponents();
		
	}
	private void initializeComponents() {	
		this.toolbar= new KToolbar(this);
		this.add(this.toolbar,BorderLayout.NORTH);
		this.menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				if(huboErrorAlCargar) {
					showBoxMessage("El modulo de clientes no fue cargado, hubo un error");					
				}else {
					if (parent != null )
						parent.setMainPanel(ProductorView.this);
				}				
			}
		});
		this.loadTable();
	}

	/// Codigo para la tabla
	public void loadTable() {		
		DefaultTableModel modeltable = new DefaultTableModel();
		modeltable.setDataVector(this.dataInitialize(), this.generateColumnNames());
		this.table = new JTable(modeltable);
		this.table.setRowSelectionAllowed(true);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(table);		
		add(scrollPane, BorderLayout.CENTER);
	}
	private void updateTable() {
		this.table.removeAll();
		DefaultTableModel modeltable = new DefaultTableModel();
		modeltable.setDataVector(this.dataInitialize(), this.generateColumnNames());
		this.table.setModel(modeltable);
		this.table.repaint();
	}

	public Object[] generateColumnNames() {
		Object[] columns = { "ID","DNI", "Nombre", "Apellido Paterno", "Apellido Materno", "Direccion","Telefono","Email"};
		return columns;

	}

	public Object[][] dataInitialize() {
		Object[][] data = null;
		List<JProductor> productores;
		try {
			productores = this.productorService.findAll();
			data = new Object[productores.size()][this.generateColumnNames().length];
			for (int i = 0; i < productores.size(); i++) {
				JProductor usuario = productores.get(i);
				data[i][0] = usuario.getId();
				data[i][1] = usuario.getDni();
				data[i][2] = usuario.getNombre();
				data[i][3] = usuario.getApellidoPaterno();
				data[i][4] = usuario.getApellidoMaterno();
				data[i][5] = usuario.getDireccion();
				data[i][6] = usuario.getTelefon();
				data[i][7] = usuario.getEmail();
			}
		} catch (ResponseException e) {
			this.huboErrorAlCargar=true;
		}		
		return data;		
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, "Error", JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public JMenuItem getMenuItem() {
		return this.menuitem;
	}
	@Override
	public void onClickToolbarOption(String buttontype) {
		System.out.println(buttontype);		
	}
}
