package com.saucedo.molinoapp.views.usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.security.*;
import com.saucedo.molinoapp.Config;
import com.saucedo.molinoapp.utils.*;
import com.saucedo.molinoapp.views.error_message.UserMessage;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

public class UsuarioDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 32222222545323L;
	public static final String MODE_EDIT = "usuario.edit";
	public static final String MODE_NEW = "usuario.new";
	private JUsuario usuario;
	private String mode;
	private ISubmitUser listener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtPassword2;
	private JComboBox<String> cmbEstado;
	// button
	private JButton btnSubmit;
	// Check box for select the role
	private JCheckBox chRecepcion;
	private JCheckBox chSecado;

	private JLabel lblRoles;
	
	public boolean isadmin;

	public UsuarioDialog(ISubmitUser listener, JUsuario usuario) {
		this(listener);
		this.usuario = usuario;
		this.mode = MODE_EDIT;
	}

	/**
	 * @wbp.parser.constructor
	 */
	public UsuarioDialog(ISubmitUser listener) {
		setModal(true);
		this.listener = listener;
		this.mode = MODE_NEW;
		this.isadmin=false;
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 291);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(341, 218, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);

		txtUsername = new JTextField();
		txtUsername.setBounds(43, 45, 171, 20);
		contentPanel.add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(43, 31, 171, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(43, 88, 83, 14);
		contentPanel.add(lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setToolTipText("Password");
		txtPassword.setBounds(43, 113, 171, 20);
		contentPanel.add(txtPassword);

		txtPassword2 = new JPasswordField();
		txtPassword2.setToolTipText("Validar Password");
		txtPassword2.setBounds(43, 144, 171, 20);
		contentPanel.add(txtPassword2);

		cmbEstado = new JComboBox<String>();
		cmbEstado.setToolTipText("Estado del usuario");
		cmbEstado.setModel(new DefaultComboBoxModel<String>(
				new String[] { JUsuario.STRATUS_ACTIVE, JUsuario.STRATUS_INHABILIDATDO }));
		cmbEstado.setSelectedIndex(0);
		cmbEstado.setBounds(257, 43, 186, 23);
		contentPanel.add(cmbEstado);

		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setBounds(257, 30, 46, 14);
		contentPanel.add(lblNewLabel_2);

		chRecepcion = new JCheckBox("Recepción");
		chRecepcion.setBounds(257, 109, 97, 23);
		contentPanel.add(chRecepcion);

		lblRoles = new JLabel("Roles");
		lblRoles.setBounds(257, 83, 186, 25);
		contentPanel.add(lblRoles);

		chSecado = new JCheckBox("Secado");
		chSecado.setBounds(257, 143, 97, 23);
		contentPanel.add(chSecado);
		contentPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtUsername, txtPassword, txtPassword2, chRecepcion, chSecado, btnSubmit}));


	}

	public void loadDialog() {
		switch (mode) {
		case MODE_EDIT:
			this.prepareDialogToEdit();
			break;
		case MODE_NEW:
			this.prepareDialogToNewUser();
			break;
		}
	}

	private void prepareDialogToNewUser() {
		this.usuario = new JUsuario();
	}

	private void prepareDialogToEdit() {
		if (usuario != null) {
			this.txtUsername.setEditable(false);
			this.txtUsername.setText(this.usuario.getUsername());
			this.cmbEstado.setSelectedItem(this.usuario.getStringOfStatus());
			this.loadRoles(this.usuario.getRoles());
		}
	}

	private void loadRoles(List<JRole> roles) {
		if (roles.size() == 1) {
			if (roles.get(0).getName().equals(JRole.ROLE_ADMIN)) {
				this.cmbEstado.removeItemAt(1);
				this.stateCheckBoxRoles(true);
				return;
			}			
		}
		for (JRole rol : roles) {
			if (rol.getName().equals(JRole.ROLE_RECEPCION))
				this.chRecepcion.setSelected(true);
			if (rol.getName().equals(JRole.ROLE_SECADO))
				this.chSecado.setSelected(true);
		}
	}

	private void stateCheckBoxRoles(boolean disableForAdmin) {
		if (disableForAdmin) {
			this.isadmin=disableForAdmin;
			this.lblRoles.setText("Administrador del sistema");
			this.chRecepcion.setVisible(!disableForAdmin);
			this.chSecado.setVisible(!disableForAdmin);
			this.cmbEstado.setEditable(!disableForAdmin);
		}	
	}


	@SuppressWarnings("deprecation")
	private boolean prepareUsuario() {
		KCheck check = new KCheck();
		String username = this.txtUsername.getText();
		String password1 = this.txtPassword.getText();
		String password2 = this.txtPassword2.getText();
		String owner = (this.mode == MODE_NEW) ? "usuario" : this.usuario.getOwner();
		String status = this.cmbEstado.getSelectedItem().toString();
		/// Roles
		if(check.in(username).noEmptySpaces().onlyBasicsCaracteres().minLen(Config.MIN_LENGHT_PASSWORD_AND_PASSWORD).notOk()) {
			this.showBoxMessage(check.getMessage()+", campo username");
			return false;
		}	
		List<JRole> roles = new ArrayList<>();
		if (this.chRecepcion.isSelected())
			roles.add(new JRole(null, JRole.ROLE_RECEPCION));
		if (this.chSecado.isSelected())
			roles.add(new JRole(null, JRole.ROLE_SECADO));
		// Valid data		
			
		if(password1.length()!=0 && password2.length()!=0 || mode==MODE_NEW) {			
			
			if(check.in(password1).noEmptySpaces().minLen(Config.MIN_LENGHT_PASSWORD_AND_PASSWORD).notOk()) {
				this.showBoxMessage(check.getMessage()+", campo password");
				return false; 
			}			
			if(!password1.equals(password2)) {
				this.showBoxMessage(UserMessage.PASSWORD_DIFRENTES);
				return false;
			}	
		}	
		if (roles.size() == 0 && !this.isadmin) {
			this.showBoxMessage(UserMessage.NO_ROLE_SELECT);
			return false;
		}	
		// Set data
		this.usuario.setUsername(username);
		this.usuario.setPassword(password2);
		this.usuario.setOwner(owner);
		this.usuario.setStatusWithString(status);
		if(!this.isadmin) this.usuario.setRoles(roles);
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.listener != null && this.prepareUsuario()) {
			if (usuario != null) {
				this.listener.notifyDialogAction(usuario, mode);
			}
		}
	}
	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, "Error", JOptionPane.WARNING_MESSAGE);
	}
	public interface ISubmitUser {
		void notifyDialogAction(JUsuario usuario, String mode);
	}

}
