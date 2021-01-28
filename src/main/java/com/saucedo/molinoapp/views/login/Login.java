package com.saucedo.molinoapp.views.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.saucedo.molinoapp.services.parseimplements.FactoryParse;
import com.saucedo.molinoapp.services.security.UsuarioService;
import com.saucedo.molino_json_models.security.SessionRequest;
import com.saucedo.molino_json_models.security.SessionResponse;
import com.saucedo.molinoapp.Config;
import com.saucedo.molinoapp.Error;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.exceptions.ResponseException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 42365L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton signin;

	private ISession notifySession;

	/**
	 * Create the panel.
	 */
	public Login(ISession target) {
		this.notifySession = target;
		setLayout(null);
		txtUsername = new JTextField();
		txtUsername.setText("kevin002");
		txtUsername.setToolTipText("Ingrese usuario");
		txtUsername.setBounds(55, 54, 237, 20);
		add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lbluser = new JLabel("Usuario");
		lbluser.setBounds(55, 37, 194, 14);
		add(lbluser);

		JLabel lblpass = new JLabel("Password");
		lblpass.setBounds(55, 77, 148, 14);
		add(lblpass);

		signin = new JButton("Sign IN");
		signin.setBounds(201, 122, 89, 23);
		add(signin);

		txtPassword = new JPasswordField();
		txtPassword.setText("maira002");
		txtPassword.setToolTipText("Ingrese contraseña ");
		txtPassword.setBounds(55, 91, 237, 20);
		add(txtPassword);
		this.initEventsComponents();

	}

	private boolean validString(String cadena) {
		if (cadena == null) {
			return false;
		}
		if (cadena.length() < Config.MIN_LENGHT_PASSWORD_AND_PASSWORD) {
			return false;
		}
		return true;
	}



	private Map<String, String> getDataLogin() {
		Map<String, String> datos = new HashMap<String, String>();
		String username = this.txtUsername.getText().toString();
		@SuppressWarnings("deprecation")
		String password = this.txtPassword.getText().toString();
		if (this.validString(username) && this.validString(password)) {
			datos.put("username", username);
			datos.put("password", password);
			return datos;
		}
		return null;
	}

	private void initEventsComponents() {
		this.signin.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				Map<String,String> data = getDataLogin();
				if (data == null) {
					 JOptionPane.showMessageDialog(Login.this,
							 Error.ERROR_DATA_FIELDS_USER_OR_PASSWORD,"Error",
							 JOptionPane.WARNING_MESSAGE);    
					return;
				}
			
				UsuarioService service = new UsuarioService(FactoryParse.getUsurioParse(),new Route(Route.ROUTE_USUARIO));
				SessionResponse response=null;
				try {
					response = service.initSession(new SessionRequest(data.get("username"),data.get("password")));
				} catch (ResponseException e1) {
					JOptionPane.showMessageDialog(Login.this,
							Error.ERROR_SESSION_USER_OR_PASSWORD,"Error",
							 JOptionPane.WARNING_MESSAGE);  
					return;
				}
				if (notifySession != null && response!=null) {
					notifySession.startSession(response);
				}

			}

		});
	}
}
