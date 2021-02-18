package com.saucedo.molinoapp.views.almacen;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JLoteSecado;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.Service;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.IMenu;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.almacen.components.RIngresoSecadoToolbar;
import com.saucedo.molinoapp.views.components.KToolbar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RegistroIngresoSecadoView extends JPanel implements IMenu, RIngresoSecadoToolbar.ButtonActionToolbarSecado, ISubmitDialog {
	public static final String THIS_WINDOWS_TITLE = "Registro ingreso al area de secado";
	public static final String MENU_ITEM_NAME = "Registro Ingreso area de secado";
	private boolean huboErrorAlCargar;
	private JMenuItem menuitem;
	private RIngresoSecadoToolbar toolbar;
	private static final long serialVersionUID = 54541L;
	IMainContainer parent;
	private JTable table;
	private RegistroIngresoSecadoDialog ingresoDialog;
	private Service<JLoteSecado> service;

	private List<JLoteSecado> lotesSecado;
	
	public RegistroIngresoSecadoView(IMainContainer parent) {
		this.huboErrorAlCargar = false;
		this.parent = parent;
		this.menuitem = new JMenuItem(MENU_ITEM_NAME);
		setLayout(new BorderLayout(0, 0));
		this.service = new Service<JLoteSecado>(FParse.getLoteSecadoParse(), new Route(Route.ROUTE_ARROZ_INGRESO_SECADO));
		table = new JTable();
		add(table, BorderLayout.CENTER);
		initializeComponents();
	}

	private void initializeComponents() {
		this.toolbar = new RIngresoSecadoToolbar(this);
		this.add(this.toolbar, BorderLayout.NORTH);
		this.toolbar.setTendido();
		this.menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadData();
				loadTableViewOne();
				if (huboErrorAlCargar) {
					showBoxMessage("El modulo de clientes no fue cargado, hubo un error");
				} else {
					if (parent != null)
						parent.setMainPanel(RegistroIngresoSecadoView.this, THIS_WINDOWS_TITLE);
				}
			}
		});
		this.table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		this.table.setRowSelectionAllowed(true);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setDefaultEditor(Object.class, null);		
	}
	
	private void loadData() {
		try {
			this.lotesSecado = this.service.findAll();
		}  catch (ResponseException e) {
			this.huboErrorAlCargar = true;
			e.printStackTrace();
		}
	}
	/// Codigo para la tabla
	public void loadTableViewOne() {
		DefaultTableModel modeltable = new DefaultTableModel();
		modeltable.setDataVector(this.dataInitializeViewOne(), this.generateColumnNamesViewOne());
		this.table.setModel(modeltable);	
	}
	/// Codigo para la tabla
	public void loadTableViewTwo() {
		DefaultTableModel modeltable = new DefaultTableModel();
		modeltable.setDataVector(this.dataInitializeViewTwo(), this.generateColumnNamesViewTwo());
		this.table.setModel(modeltable);
	}
	/// Codigo para la tabla
	public void loadTableViewThree() {
		DefaultTableModel modeltable = new DefaultTableModel();
		modeltable.setDataVector(this.dataInitializeViewThree(), this.generateColumnNamesViewThree());
		this.table.setModel(modeltable);
	}

	public Object[] generateColumnNamesViewOne() {
		Object[] columns = { "ID", "Fecha", "Hora","Numero Sacos", "Nivel Humedad", "Ubicacion", "Productor","Lote","Empleado"};
		return columns;

	}
	public Object[] generateColumnNamesViewTwo() { 
		Object[] columns = { "ID",  "Fecha Tendido", "Hora Tendido","Numero Sacos","Ubicacion"};
		return columns;

	}
	public Object[] generateColumnNamesViewThree() {
		Object[] columns = { "ID",  "Fecha Tendido", "Hora Tendido","Fecha recojo","Hora recojo","Nivel Humedad","Numero de sacos"};
		return columns;

	}

	public Object[][] dataInitializeViewOne() {
		Object[][] data = null;
		List<JLoteSecado> auxs = new ArrayList<>();
		for(JLoteSecado l : this.lotesSecado) {
			if(l.getTendido()==null) {
				auxs.add(l);
			}
		}
		data = new Object[auxs.size()][generateColumnNamesViewOne().length];
		//{ "ID", "Fecha", "Hora","Numero Sacos", "Nivel Humedad", "Ubicacion", "Empleado"};
		int i =0;
		for(JLoteSecado ll:auxs) {
			data[i][0] = ll.getId();
			data[i][1] = ll.getIngreso().getFecha();
			data[i][2] = ll.getIngreso().getHora();
			data[i][3] = ll.getIngreso().getNumeroSacos();
			data[i][4] = ll.getIngreso().getHumedad();
			data[i][5] = ll.getUbicacion().getCodigo();
			data[i][6] = ll.getLotearroz().getProductor().completeName();
			data[i][7] = ll.getLotearroz().getId();
			data[i][8] = ll.getIngreso().getEmpleado().completeName();
			i++;
		}
		return data;
	}
	public Object[][] dataInitializeViewTwo() {
		Object[][] data = null;
		return data;
	}
	public Object[][] dataInitializeViewThree() {
		Object[][] data = null;
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
		int rowSelected = this.table.getSelectedRow();
		switch (buttontype) {
		case KToolbar.BUTTON_UPDATE:
			if (rowSelected != -1) {
				// this.ingresoDialog = new RegistroIngresoDialog(this,
				// this.getProductorFromTable(rowSelected));
				// this.productorDialog.prepareForm();
				// this.productorDialog.setVisible(true);
				this.showBoxMessage("No activado");
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}
			break;
		case KToolbar.BUTTON_NEW:
			this.ingresoDialog = new RegistroIngresoSecadoDialog(this);
			this.ingresoDialog.prepareForm();
			this.ingresoDialog.setVisible(true);
			break;
		case KToolbar.BUTTON_DELETE:
			if (rowSelected != -1) {
				this.showBoxMessage("No activado");
//				JResponse response = null;
//				Long id = (Long) table.getModel().getValueAt(rowSelected, 0);
//				try {
//					int input = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Select an Option...",
//			                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
//					if(input==JOptionPane.YES_OPTION) {
//						response = this.service.delete(id);
//						this.updateTable();
//					}			
//				} catch (ResponseException e) {
//					this.showBoxMessage(Error.ERROR_BASIC);
//					e.printStackTrace();
//				}
//				if (response != null && response.getResponse().equals(JResponse.ERROR))
//					this.showBoxMessage(Error.ERROR_BASIC);
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}

			break;
		}
		this.table.clearSelection();
	}

	@Override
	public void notifyDialogAction(Object object, String mode, String sender) {
		JResponse response = null;
		try {
			switch (mode) {
			case RegistroIngresoDialog.MODE_EDIT:
			//	response = this.service.update((JLoteArroz)object);
				break;
			case RegistroIngresoDialog.MODE_NEW:
				JLoteSecado lote = (JLoteSecado )object;
 				response = this.service.insert((JLoteSecado )object);
//				if (response.getResponse().equals(JResponse.ERROR_USUARI_EXISTE))
//					this.showBoxMessage("El user name ya existe");
				
				break;
			}
			if (response != null) {
				if (response.getResponse().equals(JResponse.OK))
					this.ingresoDialog.setVisible(false);
				else if (response.getResponse().equals(JResponse.ERROR))
					this.showBoxMessage(Error.ERROR_BASIC);
			} else {
				this.showBoxMessage(Error.ERROR_BASIC + ", No hubo respuesta del servidor");
			}
		} catch (ResponseException e) {
			this.showBoxMessage("Error " + e.toString());
		}
		//this.updateTable();
	}

	@Override
	public void onViewChangeEvent(String view) {
		switch(view) {
		case RIngresoSecadoToolbar.TABLE_VIEW_ONE:
			this.toolbar.setTendido();
			this.loadTableViewOne();
			break;
		case RIngresoSecadoToolbar.TABLE_VIEW_TWO:
			this.toolbar.setRecojo();
			this.loadTableViewTwo();
			break;
		case RIngresoSecadoToolbar.TABLE_VIEW_THREE:
			this.toolbar.setNone();
			this.loadTableViewThree();
			break;
		
		}
	}

}
