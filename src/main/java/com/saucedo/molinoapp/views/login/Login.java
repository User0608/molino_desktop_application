package com.saucedo.molinoapp.views.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.saucedo.molinoapp.services.parseimplements.FParse;
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
import java.awt.Font;
import java.awt.Color;

public class Login extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 42365L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton signin;

	private ISession notifySession;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public Login(ISession target) {
		this.notifySession = target;
		setLayout(null);
		txtUsername = new JTextField();
		txtUsername.setText("");
		txtUsername.setToolTipText("Ingrese usuario");
		txtUsername.setBounds(317, 236, 237, 20);
		add(txtUsername);
		txtUsername.setColumns(10);

		JLabel lbluser = new JLabel("Usuario");
		lbluser.setBounds(317, 219, 194, 14);
		add(lbluser);

		JLabel lblpass = new JLabel("Password");
		lblpass.setBounds(317, 259, 148, 14);
		add(lblpass);

		signin = new JButton("Sign IN");
		signin.setBounds(465, 313, 89, 23);
		add(signin);

		txtPassword = new JPasswordField();
		txtPassword.setText("");
		txtPassword.setToolTipText("Ingrese contraseña ");
		txtPassword.setBounds(317, 273, 237, 20);
		add(txtPassword);
		
		lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(344, 153, 173, 53);
		add(lblNewLabel);
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
				UsuarioService service = new UsuarioService(FParse.getUsurioParse(),new Route(Route.ROUTE_USUARIO));
				SessionResponse response=null;
				try {
					response = service.initSession(new SessionRequest(data.get("username"),data.get("password")));
				} catch (ResponseException e1) {
					JOptionPane.showMessageDialog(Login.this,
							Error.ERROR_SESSION_USER_OR_PASSWORD,"Error",
							 JOptionPane.WARNING_MESSAGE);  
					return;
				}
				if(response.getStatus()==0) {
					JOptionPane.showMessageDialog(Login.this,
							Error.USUARIO_INHABILITADO,"Error",
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
