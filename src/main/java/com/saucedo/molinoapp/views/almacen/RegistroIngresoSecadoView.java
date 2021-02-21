package com.saucedo.molinoapp.views.almacen;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.almacen.JDetalleRecojo;
import com.saucedo.molino_json_models.almacen.JDetalleTendido;
import com.saucedo.molino_json_models.almacen.JLoteSecado;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.Service;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.IMenu;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.almacen.components.RIngresoSecadoToolbar;
import com.saucedo.molinoapp.views.almacen.detalles.DetalleRegistroSecadoLote;
import com.saucedo.molinoapp.views.almacen.dialogs.RegistroIngresoDialog;
import com.saucedo.molinoapp.views.almacen.dialogs.RegistroIngresoSecadoDialog;
import com.saucedo.molinoapp.views.almacen.dialogs.RegistroRecojoDialog;
import com.saucedo.molinoapp.views.almacen.dialogs.RegistroTendidoDialog;
import com.saucedo.molinoapp.views.components.KToolbar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class RegistroIngresoSecadoView extends JPanel
		implements IMenu, RIngresoSecadoToolbar.ButtonActionToolbarSecado, ISubmitDialog {
	public static final String THIS_WINDOWS_TITLE_VIEW_ONE = "Arroz en el area de secado";
	public static final String MENU_ITEM_NAME = "Registro Ingreso area de secado";
	private String right_view = RIngresoSecadoToolbar.TABLE_VIEW_ONE;
	private boolean huboErrorAlCargar;
	private JMenuItem menuitem;
	private RIngresoSecadoToolbar toolbar;
	private static final long serialVersionUID = 54541L;
	IMainContainer parent;
	private JTable table;
	private RegistroIngresoSecadoDialog ingresoDialog;
	private RegistroTendidoDialog registroTendidoDialog;
	private RegistroRecojoDialog registroRecojoDialog;
	
	private DetalleRegistroSecadoLote detalleLoteSecadoView;
	
	private Service<JLoteSecado> service;
	private Service<JDetalleTendido> tendidoSevice;
	private Service<JDetalleRecojo> recojoSevice;
	
	private List<JLoteSecado> lotesSecado;

	public RegistroIngresoSecadoView(IMainContainer parent) {
		this.huboErrorAlCargar = false;
		this.parent = parent;
		this.menuitem = new JMenuItem(MENU_ITEM_NAME);
		setLayout(new BorderLayout(0, 0));
		this.service = new Service<JLoteSecado>(FParse.getLoteSecadoParse(),
				new Route(Route.ROUTE_ARROZ_INGRESO_SECADO));
		this.tendidoSevice = new Service<JDetalleTendido>(FParse.getDetalleTendidoParse(),
				new Route(Route.ROUTE_ARROZ_INGRESO_SECADO_TENDIDO));
		this.recojoSevice = new Service<JDetalleRecojo>(FParse.getDetalleRecojoParse(),
				new Route(Route.ROUTE_ARROZ_INGRESO_SECADO_RECOJO));
		
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
						parent.setMainPanel(RegistroIngresoSecadoView.this, THIS_WINDOWS_TITLE_VIEW_ONE);
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
		} catch (ResponseException e) {
			this.huboErrorAlCargar = true;
			e.printStackTrace();
		}
	}
	private JLoteSecado filterLote(Long id) {
		for(JLoteSecado l:this.lotesSecado) {
			if(l.getId() == id) {
				return l;
			}
		}
		return null;
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
		Object[] columns = { "ID", "Fecha", "Hora", "Numero Sacos", "Humedad", "Ubicacion", "Productor", "Lote",
				"Empleado" };
		return columns;

	}

	public Object[] generateColumnNamesViewTwo() {
		Object[] columns = { "ID", "Fecha Tendido", "Hora Tendido", "Numero Sacos", "Ubicacion", "Productor", "Lote",
				"Empleado" };
		return columns;

	}

	public Object[] generateColumnNamesViewThree() {
		Object[] columns = { "ID", "Fecha Tendido", "Hora Tendido", "Fecha Recojo", "Hora Recojo", "Nivel Humedad",
				"N. sacos Tendidos", "N. Recojidos", "Productor", "Lote" };
		return columns;

	}

	public Object[][] dataInitializeViewOne() {
		Object[][] data = null;
		List<JLoteSecado> auxs = new ArrayList<>();
		for (JLoteSecado l : this.lotesSecado) {
			if (l.getTendido() == null) {
				auxs.add(l);
			}
		}
		data = new Object[auxs.size()][generateColumnNamesViewOne().length];
		// { "ID", "Fecha", "Hora","Numero Sacos", "Humedad", "Ubicacion",
		// "Productor","Lote","Empleado"};
		int i = 0;
		for (JLoteSecado ll : auxs) {
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
		List<JLoteSecado> auxs = new ArrayList<>();
		for (JLoteSecado l : this.lotesSecado) {
			if (l.getTendido() != null) {
				if (l.getTendido().getDetalleRecojo() == null) {
					auxs.add(l);
				}
			}
		}
		data = new Object[auxs.size()][generateColumnNamesViewTwo().length];
		// { "ID", "Fecha", "Hora","Numero Sacos", "Humedad", "Ubicacion",
		// "Productor","Lote","Empleado"};
		// { "ID", "Fecha Tendido", "Hora Tendido","Numero
		// Sacos","Ubicacion","Productor","Lote","Empleado"};
		int i = 0;
		for (JLoteSecado ll : auxs) {
			data[i][0] = ll.getId();
			data[i][1] = ll.getTendido().getFecha();
			data[i][2] = ll.getTendido().getHora();
			data[i][3] = ll.getIngreso().getNumeroSacos();
			data[i][4] = ll.getTendido().getUbicacion();
			data[i][5] = ll.getLotearroz().getProductor().completeName();
			data[i][6] = ll.getLotearroz().getId();
			data[i][7] = ll.getIngreso().getEmpleado().completeName();
			i++;
		}
		return data;
	}

	public Object[][] dataInitializeViewThree() {
		Object[][] data = null;
		List<JLoteSecado> auxs = new ArrayList<>();
		for (JLoteSecado l : this.lotesSecado) {
			if (l.getTendido() != null) {
				if (l.getTendido().getDetalleRecojo() != null) {
					auxs.add(l);
				}
			}
		}
		data = new Object[auxs.size()][generateColumnNamesViewThree().length];
		// { "ID", "Fecha", "Hora","Numero Sacos", "Humedad", "Ubicacion",
		// "Productor","Lote","Empleado"};
		// { "ID", "Fecha Tendido", "Hora Tendido","Fecha Recojo","Hora Recojo","Nivel
		// Humedad","N. sacos Tendidos","N. Recojidos", "Productor","Lote"};
		int i = 0;
		for (JLoteSecado ll : auxs) {
			data[i][0] = ll.getId();
			data[i][1] = ll.getTendido().getFecha();
			data[i][2] = ll.getTendido().getHora();
			data[i][3] = ll.getTendido().getDetalleRecojo().getFecha();
			data[i][4] = ll.getTendido().getDetalleRecojo().getHora();
			data[i][5] = ll.getTendido().getDetalleRecojo().getHumedad();
			data[i][6] = ll.getIngreso().getNumeroSacos();
			data[i][7] = ll.getTendido().getDetalleRecojo().getNumeroSacos();
			data[i][8] = ll.getLotearroz().getProductor().completeName();
			data[i][9] = ll.getLotearroz().getId();
			i++;
		}
		return data;
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, "Error", JOptionPane.WARNING_MESSAGE);
	}

	private void showBoxMessageOK(String Message) {
		JOptionPane.showMessageDialog(this, Message, "Error", JOptionPane.INFORMATION_MESSAGE);
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
			if (SessionStatus.getInst().hasRole("admin")) {
			if (rowSelected != -1) {
				Long id = (Long) table.getModel().getValueAt(rowSelected, 0);
				switch(this.right_view) {
				case RIngresoSecadoToolbar.TABLE_VIEW_ONE:
					
					break;
				case RIngresoSecadoToolbar.TABLE_VIEW_TWO:
					this.registroTendidoDialog = new RegistroTendidoDialog(this,this.filterLote(id).getTendido());
					this.registroTendidoDialog.setDetalleLote(id, "Lote tendido, Codigo: " + id.toString());
					this.registroTendidoDialog.prepareForm();
					this.registroTendidoDialog.setVisible(true);					
					break;
				case RIngresoSecadoToolbar.TABLE_VIEW_THREE:										
					this.registroRecojoDialog = new RegistroRecojoDialog(this,this.filterLote(id).getTendido().getDetalleRecojo());
					this.registroRecojoDialog.setDetalleLote(id, "Lote tendido, Codigo: " + id.toString());
					this.registroRecojoDialog.prepareForm();
					this.registroRecojoDialog.setVisible(true);
					break;			
				}
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}
			}else {
				this.showBoxMessage("No tiene permisos para realizar esta operacion");
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
		case RIngresoSecadoToolbar.BUTTON_REGISTRAR_TENDIDO:
			if (rowSelected != -1) {
				Long id = (Long) table.getModel().getValueAt(rowSelected, 0);
				this.registroTendidoDialog = new RegistroTendidoDialog(this);
				this.registroTendidoDialog.setDetalleLote(id, "Lote en area de secado, Codigo: " + id.toString());
				this.registroTendidoDialog.prepareForm();
				this.registroTendidoDialog.setVisible(true);
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}
			break;
		case RIngresoSecadoToolbar.BUTTON_REGISTRAR_RECOJO:
			if (rowSelected != -1) {
				Long id = (Long) table.getModel().getValueAt(rowSelected, 0);
				this.registroRecojoDialog = new RegistroRecojoDialog(this);
				this.registroRecojoDialog.setDetalleLote(id, "Lote tendido, Codigo: " + id.toString());
				this.registroRecojoDialog.prepareForm();
				this.registroRecojoDialog.setVisible(true);
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}
			break;
		case RIngresoSecadoToolbar.BUTTON_DETALLE:
			if (rowSelected != -1) {
				Long id = (Long) table.getModel().getValueAt(rowSelected, 0);
				this.detalleLoteSecadoView = new DetalleRegistroSecadoLote(this.filterLote(id));
				this.detalleLoteSecadoView.setVisible(true);
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
			if (sender == RegistroIngresoSecadoDialog.DIALOG_KEY) {

				switch (mode) {
				case RegistroIngresoDialog.MODE_EDIT:
					// response = this.service.update((JLoteArroz)object);
					break;
				case RegistroIngresoDialog.MODE_NEW:
					response = this.service.insert((JLoteSecado) object);
					// if (response.getResponse().equals(JResponse.ERROR_USUARI_EXISTE))
					// this.showBoxMessage("El user name ya existe");
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
			} else if (sender == RegistroTendidoDialog.DIALOG_KEY) {
				switch (mode) {
				case RegistroRecojoDialog.MODE_EDIT:
					 response = this.tendidoSevice.update((JDetalleTendido) object);
					break;
				case RegistroTendidoDialog.MODE_NEW:
					JDetalleTendido t = (JDetalleTendido) object;
					response = this.tendidoSevice.insert(t);
					break;
				}
				if (response != null) {
					if (response.getResponse().equals(JResponse.OK)) {
						this.registroTendidoDialog.setVisible(false);
						this.showBoxMessageOK("Registro guardado");
					} else if (response.getResponse().equals(JResponse.ERROR))
						this.showBoxMessage(Error.ERROR_BASIC);
				} else {
					this.showBoxMessage(Error.ERROR_BASIC + ", No hubo respuesta del servidor");
				}
			} else if(sender == RegistroRecojoDialog.DIALOG_KEY) {
				switch (mode) {
				case RegistroRecojoDialog.MODE_EDIT:
					 response = this.recojoSevice.update((JDetalleRecojo) object);
					break;
				case RegistroRecojoDialog.MODE_NEW:
					JDetalleRecojo r = (JDetalleRecojo) object;
					response = this.recojoSevice.insert(r);
					break;
				}
				if (response != null) {
					if (response.getResponse().equals(JResponse.OK)) {
						this.registroRecojoDialog.setVisible(false);
						this.showBoxMessageOK("Registro guardado");
					} else if (response.getResponse().equals(JResponse.ERROR))
						this.showBoxMessage(Error.ERROR_BASIC);
				} else {
					this.showBoxMessage(Error.ERROR_BASIC + ", No hubo respuesta del servidor");
				}
			}
		} catch (ResponseException e) {
			this.showBoxMessage("Error " + e.toString());
		}

		 this.loadData();
	}

	@Override
	public void onViewChangeEvent(String view) {
		this.right_view = view;
		switch (view) {
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
		case RIngresoSecadoToolbar.TABLE_VIEW_HISTORY:
			this.toolbar.setHistory();
			break;
		}
	}

}
