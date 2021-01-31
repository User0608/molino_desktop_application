package com.saucedo.molinoapp.views.personal;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.personal.JEmpleado;
import com.saucedo.molino_json_models.security.JUsuario;
import com.saucedo.molinoapp.Config;
import com.saucedo.molinoapp.utils.KCheck;
import com.saucedo.molinoapp.views.error_message.General;
import com.saucedo.molinoapp.views.usuario.*;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;

public class EmpleadoDialog extends JDialog implements ActionListener, UsuarioDialog.ISubmitUser {

	private static final long serialVersionUID = 143243L;
	public static final String TITLE_MODE_EDIT = "Editar empleado";
	public static final String TITLE_MODE_NEW = "Nuevo empleado";

	public static final String MODE_EDIT = "empleado.edit";
	public static final String MODE_NEW = "empleado.new";
	private JEmpleado empleado;
	private String mode;
	ISubmitProductor targetListener;
	
	private boolean hadUser=false;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellidoP;
	private JTextField txtApellidoM;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtSueldo;
	private JComboBox<String> cmbEstado;
	private JButton btnAddUsuario;
	private JPanel uPanel;
	private JLabel lblRoles;
	private JLabel lblUsername;
	/**
	 * @wbp.nonvisual location=299,219
	 */

	private UsuarioDialog usuarioDialog;
	private JUsuario usuario;
	private JButton btnremove;

	public EmpleadoDialog(ISubmitProductor target, JEmpleado empleado) {
		this(target);
		this.empleado = empleado;
		JUsuario u = this.empleado.getUsuario();
		if(u!=null) {
			this.hadUser=true;
			this.usuario=u;
			this.loadUserData();
		}
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);

	}

	/**
	 * @wbp.parser.constructor
	 */
	public EmpleadoDialog(ISubmitProductor target) {
		this.empleado = new JEmpleado();
		this.mode = MODE_NEW;
		this.setTitle("Empleado");
		this.targetListener = target;
		setBounds(100, 100, 564, 413);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(235, 322, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);

		txtDni = new JTextField();
		txtDni.setToolTipText("Dni del empleado");
		txtDni.setBounds(35, 36, 127, 20);
		contentPanel.add(txtDni);
		txtDni.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(35, 81, 174, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidoP = new JTextField();
		txtApellidoP.setBounds(35, 125, 174, 20);
		contentPanel.add(txtApellidoP);
		txtApellidoP.setColumns(10);

		txtApellidoM = new JTextField();
		txtApellidoM.setBounds(35, 169, 174, 20);
		contentPanel.add(txtApellidoM);
		txtApellidoM.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(35, 214, 201, 20);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(275, 80, 153, 20);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(275, 124, 226, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(35, 23, 127, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(35, 67, 142, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apellido Paterno");
		lblNewLabel_2.setBounds(35, 112, 174, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Apellido Materno");
		lblNewLabel_3.setBounds(35, 156, 174, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Direccion");
		lblNewLabel_4.setBounds(35, 200, 226, 14);
		contentPanel.add(lblNewLabel_4);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(275, 67, 153, 14);
		contentPanel.add(lblTelefono);

		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(275, 111, 226, 14);
		contentPanel.add(lblNewLabel_5);

		btnAddUsuario = new JButton("Add User");
		btnAddUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (usuario == null) {
					usuarioDialog = new UsuarioDialog(EmpleadoDialog.this);
					usuarioDialog.loadDialog();
					usuarioDialog.setVisible(true);
				}
			}

		});
		btnAddUsuario.setBounds(35, 245, 113, 19);
		contentPanel.add(btnAddUsuario);

		txtSueldo = new JTextField();
		txtSueldo.setBounds(275, 169, 86, 20);
		contentPanel.add(txtSueldo);
		txtSueldo.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Sueldo");
		lblNewLabel_6.setBounds(275, 156, 46, 14);
		contentPanel.add(lblNewLabel_6);

		cmbEstado = new JComboBox<String>();
		cmbEstado.setModel(new DefaultComboBoxModel<String>(
				new String[] { EmpleadoView.STATUS_ACTIVE, EmpleadoView.STATUS_INHABILIDATDO }));
		cmbEstado.setSelectedIndex(0);
		cmbEstado.setBounds(275, 213, 190, 22);
		contentPanel.add(cmbEstado);

		JLabel lblNewLabel_7 = new JLabel("Estado");
		lblNewLabel_7.setBounds(275, 200, 46, 14);
		contentPanel.add(lblNewLabel_7);

		btnremove = new JButton("Remove User");
		btnremove.setBounds(402, 245, 113, 19);
		this.btnremove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usuario = null;
				loadUserData();
			}

		});
		contentPanel.add(btnremove);

		uPanel = new JPanel();
		uPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		uPanel.setBounds(35, 265, 480, 31);
		contentPanel.add(uPanel);
		uPanel.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Username:");
		lblNewLabel_8.setBounds(10, 11, 69, 14);
		uPanel.add(lblNewLabel_8);

		lblUsername = new JLabel("New label");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(79, 11, 93, 14);
		uPanel.add(lblUsername);

		JLabel lblNewLabel_9 = new JLabel("Roles:");
		lblNewLabel_9.setBounds(180, 11, 46, 14);
		uPanel.add(lblNewLabel_9);

		lblRoles = new JLabel("New label");
		lblRoles.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoles.setBounds(218, 11, 173, 14);
		uPanel.add(lblRoles);
		this.loadUserData();
	}

	public void prepareForm() {
		if (mode.equals(MODE_EDIT))
			this.loadProductorToFilds();
	}

	public void loadUserData() {
		if (this.usuario == null) {
			this.btnremove.setVisible(false);
			this.btnAddUsuario.setEnabled(true);
			this.btnAddUsuario.setVisible(true);	
			this.uPanel.setVisible(false);

		} else {
			if(this.hadUser) {
				this.btnremove.setVisible(false);
				this.btnAddUsuario.setVisible(false);	
			}else {
				this.btnremove.setVisible(true);
				this.btnAddUsuario.setEnabled(false);				
			}
			this.uPanel.setVisible(true);
			this.lblUsername.setText(this.usuario.getUsername());
			this.lblRoles.setText(this.usuario.getStringRoles());
		}
	}

	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String dni = this.txtDni.getText();
		String nombre = this.txtNombre.getText();
		String apellidop = this.txtApellidoP.getText();
		String apellidom = this.txtApellidoM.getText();
		String direccion = this.txtDireccion.getText();
		String telefono = this.txtTelefono.getText();
		String email = this.txtEmail.getText();
		String sueldo = this.txtSueldo.getText();
		String ests = (String) this.cmbEstado.getSelectedItem();
		boolean estado = ests == EmpleadoView.STATUS_ACTIVE ? true : false;

		if (check.in(dni).onlyNumbers().noEmptySpaces().allowLength(Config.DNI_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo DNI");
			return false;
		}

		if (check.in(nombre).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces().minLen(Config.MIN_LENGTH)
				.notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo nombre");
			return false;
		}
		if (check.in(apellidop).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces()
				.minLen(Config.MIN_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo apellido paterno");
			return false;
		}
		if (check.in(apellidom).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces()
				.minLen(Config.MIN_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo apellido materno");
			return false;
		}
		if (check.in(direccion).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces()
				.minLen(Config.MIN_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo dirección");
			return false;
		}
		if (check.in(telefono).onlyNumbers().noEmptySpaces().minLen(Config.MIN_LENGTH_PHONE_NUMBRE)
				.maxLen(Config.MAX_LENGTH_PHONE_NUMBRE).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo telefono");
			return false;
		}
		if (check.in(email).noEmptySpaces().minLen().isEmail().notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo email");
			return false;
		}
		if (check.in(sueldo).noEmptySpaces().isNumber().notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo sueldo");
			return false;
		}
		this.empleado.setDni(dni);
		this.empleado.setNombre(nombre);
		this.empleado.setApellidoPaterno(apellidop);
		this.empleado.setApellidoMaterno(apellidom);
		this.empleado.setDireccion(direccion);
		this.empleado.setTelefon(telefono);
		this.empleado.setEmail(email);
		this.empleado.setSueldo(Double.parseDouble(sueldo));
		this.empleado.setEstado(estado);
		this.empleado.setUsuario(usuario);
		return true;

	}

	private void loadProductorToFilds() {
		if (this.empleado != null) {
			this.txtDni.setText(this.empleado.getDni());
			this.txtNombre.setText(this.empleado.getNombre());
			this.txtApellidoP.setText(this.empleado.getApellidoPaterno());
			this.txtApellidoM.setText(this.empleado.getApellidoMaterno());
			this.txtDireccion.setText(this.empleado.getDireccion());
			this.txtTelefono.setText(this.empleado.getTelefon());
			this.txtEmail.setText(this.empleado.getEmail());
			this.txtSueldo.setText(Double.toString(this.empleado.getSueldo()));
			if (this.empleado.isEstado())
				this.cmbEstado.setSelectedIndex(0);
			else
				this.cmbEstado.setSelectedIndex(1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.empleado, this.mode);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
	}

	public interface ISubmitProductor {
		void notifyDialogAction(JEmpleado empleado, String mode);
	}

	@Override
	public void notifyDialogAction(JUsuario usuario, String mode) {
		this.usuario = usuario;
		this.usuarioDialog.setVisible(false);
		this.loadUserData();

	}
}
