package com.saucedo.molinoapp.views.usuario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molino_json_models.security.JRole;
import com.saucedo.molino_json_models.security.JUsuario;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.security.UsuarioService;
import com.saucedo.molinoapp.views.IMainContainer;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class UsuarioPanel extends JPanel implements ToolbarUsuario.ButtonActionToolbar, UsuarioDialog.ISubmitUser {

	private static final long serialVersionUID = 12143544532L;
	private IMainContainer parent;
	private JTable table;
	private JMenuItem menuitem;
	private ToolbarUsuario toolbar;

	private UsuarioService service;
	private UsuarioDialog usuarioDialog;

	/**
	 * Create the panel.
	 */
	public UsuarioPanel(IMainContainer parent) {
		this.service = new UsuarioService();
		this.parent = parent;
		setBackground(Color.ORANGE);
		this.menuitem = new JMenuItem("Usuarios");
		setLayout(new BorderLayout());
		this.initializeComponents();
		this.loadTable();
	}

	public JMenuItem getMenuItem() {
		return this.menuitem;
	}

	private void initializeComponents() {
		this.toolbar = new ToolbarUsuario(this);
		this.add(toolbar, BorderLayout.NORTH);
		this.menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setMainPanel(UsuarioPanel.this);
			}
		});
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

	public Object[] generateColumnNames() {
		Object[] columns = { "ID", "Username", "Roles", "Owner", "Status" };
		return columns;

	}

	public Object[][] dataInitialize() {
		Object[][] data = null;
		try {
			List<JUsuario> usuarios = this.service.findAll();
			data = new Object[usuarios.size()][this.generateColumnNames().length];
			for (int i = 0; i < usuarios.size(); i++) {
				JUsuario usuario = usuarios.get(i);
				data[i][0] = usuario.getId();
				data[i][1] = usuario.getUsername();
				data[i][2] = usuario.getStringRoles();
				data[i][3] = usuario.getOwner();
				data[i][4] = usuario.getStringOfStatus();

			}

		} catch (ResponseException e) {
			this.showBoxMessage(Error.ERROR_CONNECTION_API_REST);
		}

		return data;
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, "Error", JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void onClickToolbarOption(String buttontype) {
		// TODO Auto-generated method stub
		switch (buttontype) {
		case ToolbarUsuario.BUTTON_UPDATE:
			int row = this.table.getSelectedRow();
			if (row != -1) {
				// Object[] columns= {"ID","Username","Roles","Owner","Status"};
				Long id = (Long) this.table.getModel().getValueAt(row, 0);
				String username = (String) this.table.getModel().getValueAt(row, 1);
				String roles = (String) this.table.getModel().getValueAt(row, 2);
				List<JRole> listroles = new ArrayList<JRole>();
				for (String r : roles.trim().split(",")) {
					listroles.add(new JRole(new Long(0), r));
				}
				String owner = (String) this.table.getModel().getValueAt(row, 3);
				String status = (String) this.table.getModel().getValueAt(row, 4);

				JUsuario usuario = new JUsuario();
				usuario.setId(id);
				usuario.setUsername(username.trim());
				usuario.setRoles(listroles);
				usuario.setOwner(owner);
				usuario.setStatusWithString(status);
				this.usuarioDialog = new UsuarioDialog(this, usuario);
				this.usuarioDialog.loadDialog();
				this.usuarioDialog.setVisible(true);
			} else {
				this.showBoxMessage(Error.ERROR_ROW_NO_SELECTED_TABLE);
			}
			break;
		case ToolbarUsuario.BUTTON_NEW:
			this.usuarioDialog = new UsuarioDialog(this);
			this.usuarioDialog.loadDialog();
			this.usuarioDialog.setVisible(true);
			break;
		case ToolbarUsuario.BUTTON_DELETE:
			break;

		}
	}

	@Override
	public void notifyDialogAction(JUsuario usuario, String mode) {
	
	}
}
