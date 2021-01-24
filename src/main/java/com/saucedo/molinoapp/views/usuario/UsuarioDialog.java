package com.saucedo.molinoapp.views.usuario;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.security.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class UsuarioDialog extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 32222222545323L;
	private static final String MODE_EDIT="usuario.edit";
	private static final String MODE_NEW ="usuario.new";
	private JUsuario usuario;
	private String mode;
	private ISubmitUser listener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JPasswordField txtPassword2;
	private JComboBox<String> cmbEstado;
	//button
	private JButton btnSubmit;
	//Check box for select the role
	private JCheckBox chRecepcion;
	private JCheckBox chSecado ;
	
	private JLabel lblRoles;
	
	
	public UsuarioDialog(ISubmitUser listener,JUsuario usuario) {
		this(listener);
		this.usuario=usuario;
		this.mode=MODE_EDIT;
	}
	/**
	 * @wbp.parser.constructor
	 */
	public UsuarioDialog(ISubmitUser listener) {
		this.listener=listener;
		this.mode=MODE_NEW;
		this.setModal(true);		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 481, 291);
		getContentPane().setLayout(new BorderLayout());
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
		
		cmbEstado = new JComboBox<String>();
		cmbEstado.setToolTipText("Estado del usuario");
		cmbEstado.setModel(new DefaultComboBoxModel<String>(new String[] {JUsuario.STRATUS_ACTIVE,JUsuario.STRATUS_INHABILIDATDO}));
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
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(43, 113, 171, 20);
		contentPanel.add(txtPassword);
		
		txtPassword2 = new JPasswordField();
		txtPassword2.setBounds(43, 144, 171, 20);
		contentPanel.add(txtPassword2);
		
	}
	public void loadDialog() {
		switch(mode) {
		case MODE_EDIT: this.prepareDialogToEdit();break;
		case MODE_NEW: this.prepareDialogToNewUser();break;
		}
	}
	private void prepareDialogToNewUser() {
		this.usuario=new JUsuario();
	}
	private void prepareDialogToEdit() {
		if(usuario!=null) {
			this.txtUsername.setText(this.usuario.getUsername());
			this.cmbEstado.setSelectedItem(this.usuario.getStringOfStatus());
			this.loadRoles(this.usuario.getRoles());			
		}		
	}
	private void loadRoles(List<JRole> roles) {
		if(roles.size()==1) {if(roles.get(0).getName().equals(JRole.ROLE_ADMIN)) {
			this.stateCheckBoxRoles(true);
			return;
		}}
		for(JRole rol:roles) {
			if(rol.getName()==JRole.ROLE_RECEPCION) this.chRecepcion.setSelected(true);
			if(rol.getName()==JRole.ROLE_SECADO) this.chSecado.setSelected(true);
		}		
	}
	private void stateCheckBoxRoles(boolean disableForAdmin) {
		if(disableForAdmin) {
			this.lblRoles.setText("Administrador del sistema");
			this.chRecepcion.setVisible(!disableForAdmin);
			this.chSecado.setVisible(!disableForAdmin);			
		}
	}
	public interface ISubmitUser{
		void notifyDialogAction(JUsuario usuario,String mode);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.listener!=null) {
			this.listener.notifyDialogAction(usuario, mode);
		}
	}
}
