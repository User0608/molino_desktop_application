package com.saucedo.molinoapp.views.almacen;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.Service;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.IMenu;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.almacen.components.RIngresoToolbar;
import com.saucedo.molinoapp.views.components.KToolbar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RegistroIngresoView extends JPanel implements IMenu, KToolbar.ButtonActionToolbar, ISubmitDialog {
	public static final String THIS_WINDOWS_TITLE = "Registro de ingreso de arroz al molino";
	public static final String MENU_ITEM_NAME = "Registro Ingreso Arroz";
	private boolean huboErrorAlCargar;
	private JMenuItem menuitem;
	private RIngresoToolbar toolbar;
	private static final long serialVersionUID = 54541L;
	IMainContainer parent;
	private JTable table;
	private RegistroIngresoDialog ingresoDialog;
	private Service<JLoteArroz> service;

	public RegistroIngresoView(IMainContainer parent) {
		this.huboErrorAlCargar = false;
		this.parent = parent;
		this.menuitem = new JMenuItem(MENU_ITEM_NAME);
		setLayout(new BorderLayout(0, 0));
		this.service = new Service<JLoteArroz>(FParse.getLoteArrozParseParse(), new Route(Route.ROUTE_ARROZ_LOTE));
		table = new JTable();
		add(table, BorderLayout.CENTER);
		initializeComponents();
	}

	private void initializeComponents() {
		this.toolbar = new RIngresoToolbar(this);
		this.add(this.toolbar, BorderLayout.NORTH);
		this.menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				if (huboErrorAlCargar) {
					showBoxMessage("El modulo de clientes no fue cargado, hubo un error");
				} else {
					if (parent != null)
						parent.setMainPanel(RegistroIngresoView.this, THIS_WINDOWS_TITLE);
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
		Object[] columns = { "ID",  "Productor","DNI", "Numero Sacos", "Tipo Arroz", "Empleado", "Fecha", "Hora"};
		return columns;

	}

//	private JProductor getProductorFromTable(int row) {
//		JProductor productor = new JProductor();
//		TableModel model = this.table.getModel();
//		productor.setId((Long) model.getValueAt(row, 0));
//		productor.setDni((String) model.getValueAt(row, 1));
//		productor.setNombre((String) model.getValueAt(row, 2));
//		productor.setApellidoPaterno((String) model.getValueAt(row, 3));
//		productor.setApellidoMaterno((String) model.getValueAt(row, 4));
//		productor.setDireccion((String) model.getValueAt(row, 5));
//		productor.setTelefon((String) model.getValueAt(row, 6));
//		productor.setEmail((String) model.getValueAt(row, 7));
//		return productor;
//	}

	public Object[][] dataInitialize() {
		Object[][] data = null;
	List<JLoteArroz> lotesArroz;
	try {
		lotesArroz = this.service.findAll();
		
		data = new Object[lotesArroz.size()][this.generateColumnNames().length];
			for (int i = 0; i < lotesArroz.size(); i++) {
				JLoteArroz lote = lotesArroz.get(i);
				data[i][0] = lote.getId();
				data[i][1] = lote.getProductor().completeName();
				data[i][2] = lote.getProductor().getDni();
				data[i][3] = Integer.toString(lote.getNumeroSacos());
				data[i][4] = lote.getTipoArroz().getNombre();
				data[i][5] = lote.getIngreso().getEmpleado().completeName();
				data[i][6] = lote.getIngreso().getFecha().toString();
				data[i][7] = lote.getIngreso().getHora().toString();
		}
		} catch (ResponseException e) {
			this.huboErrorAlCargar = true;
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
		System.out.println("Bunton Type: " + buttontype);
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
			this.ingresoDialog = new RegistroIngresoDialog(this);
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
				response = this.service.update((JLoteArroz)object);
				break;
			case RegistroIngresoDialog.MODE_NEW:
				response = this.service.insert((JLoteArroz)object);
				if (response.getResponse().equals(JResponse.ERROR_USUARI_EXISTE))
					this.showBoxMessage("El user name ya existe");
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
		this.updateTable();
	}

}
