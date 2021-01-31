package com.saucedo.molinoapp.views.personal;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molino_json_models.JResponse;
import com.saucedo.molino_json_models.personal.JEmpleado;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.Service;
import com.saucedo.molinoapp.services.parseimplements.FParse;
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
import javax.swing.table.TableModel;

public class EmpleadoView extends JPanel
		implements IMenu, KToolbar.ButtonActionToolbar, EmpleadoDialog.ISubmitProductor {
	public static final String THIS_WINDOWS_TITLE="Registro de empleados";
	public static final String STATUS_ACTIVE="activo";
	public static final String STATUS_INHABILIDATDO="inhabilitado";
	

	private boolean huboErrorAlCargar;
	private JMenuItem menuitem;
	private KToolbar toolbar;
	private Service<JEmpleado> service;
	private static final long serialVersionUID = 54541L;
	IMainContainer parent;
	private JTable table;
	private EmpleadoDialog empleadoDialog;

	public EmpleadoView(IMainContainer parent) {
		this.huboErrorAlCargar = false;
		this.parent = parent;
		this.menuitem = new JMenuItem("Empleados");
		setLayout(new BorderLayout(0, 0));
		this.service = new Service<JEmpleado>(FParse.getEmpleadoParse(),new Route(Route.ROUTE_EMPLEADO));
		table = new JTable();
		add(table, BorderLayout.CENTER);
		initializeComponents();
	}

	private void initializeComponents() {
		this.toolbar = new KToolbar(this);
		this.add(this.toolbar, BorderLayout.NORTH);
		this.menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
				if (huboErrorAlCargar) {
					showBoxMessage("El modulo de clientes no fue cargado, hubo un error");
				} else {
					if (parent != null)
						parent.setMainPanel(EmpleadoView.this,THIS_WINDOWS_TITLE);
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
		Object[] columns = { "ID", "DNI", "Nombre", "Apellido Paterno", "Apellido Materno", "Direccion", "Telefono",
				"Email","Fecha Contrato","Sueldo","Estado" };
		return columns;

	}

	private JEmpleado getEmpleadoFromTable(int row) {		
		TableModel model = this.table.getModel();
		Long id = (Long) model.getValueAt(row, 0);
		JEmpleado empleado=null;
		try {
			empleado = this.service.findByField(id.toString());
		} catch (ResponseException e) {
			this.showBoxMessage(Error.ERROR_BASIC);
			e.printStackTrace();
		}	
		return empleado;
	}

	public Object[][] dataInitialize() {
		Object[][] data = null;
		List<JEmpleado> productores;
		try {
			productores = this.service.findAll();
			data = new Object[productores.size()][this.generateColumnNames().length];
			for (int i = 0; i < productores.size(); i++) {
				JEmpleado usuario = productores.get(i);
				data[i][0] = usuario.getId();
				data[i][1] = usuario.getDni();
				data[i][2] = usuario.getNombre();
				data[i][3] = usuario.getApellidoPaterno();
				data[i][4] = usuario.getApellidoMaterno();
				data[i][5] = usuario.getDireccion();
				data[i][6] = usuario.getTelefon();
				data[i][7] = usuario.getEmail();				
				data[i][8] = usuario.getFechaContrato()==null?"none": usuario.getFechaContrato().toString();
				data[i][9] = usuario.getSueldo();
				data[i][10] = usuario.isEstado()?STATUS_ACTIVE:STATUS_INHABILIDATDO;
			}
		} catch (ResponseException e) {
			
			e.printStackTrace();
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
		int rowSelected = this.table.getSelectedRow();
		switch (buttontype) {
		case KToolbar.BUTTON_UPDATE:
			if (rowSelected != -1) {
				this.empleadoDialog = new EmpleadoDialog(this, this.getEmpleadoFromTable(rowSelected));
				this.empleadoDialog.prepareForm();
				this.empleadoDialog.setVisible(true);
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}
			break;
		case KToolbar.BUTTON_NEW:
			this.empleadoDialog = new EmpleadoDialog(this);
			this.empleadoDialog.prepareForm();
			this.empleadoDialog.setVisible(true);
			break;
		case KToolbar.BUTTON_DELETE:
			if (rowSelected != -1) {
				JResponse response = null;
				Long id = (Long) table.getModel().getValueAt(rowSelected, 0);
				try {
					int input = JOptionPane.showConfirmDialog(this, "Do you want to proceed?", "Select an Option...",
			                JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
					if(input==JOptionPane.YES_OPTION) {
						response = this.service.delete(id);
						this.updateTable();
					}			
				} catch (ResponseException e) {
					this.showBoxMessage(Error.ERROR_BASIC);
					e.printStackTrace();
				}
				if (response!=null&&response.getResponse().equals(JResponse.ERROR))
					this.showBoxMessage(Error.ERROR_BASIC);
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}

			break;
		}
		this.table.clearSelection();
	}

	@Override
	public void notifyDialogAction(JEmpleado empleado, String mode) {
		JResponse response = null;
		try {
			switch (mode) {
			case EmpleadoDialog.MODE_EDIT:
				response = this.service.update(empleado);
				break;
			case EmpleadoDialog.MODE_NEW:
				response = this.service.insert(empleado);
				if (response.getResponse().equals(JResponse.ERROR_USUARI_EXISTE))
					this.showBoxMessage("El user name ya existe");
				break;
			}
			if (response != null) {
				if (response.getResponse().equals(JResponse.OK))
					this.empleadoDialog.setVisible(false);
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
