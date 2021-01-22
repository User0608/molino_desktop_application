package com.saucedo.molinoapp.views.login;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.saucedo.molinoapp.Global;
import com.saucedo.molinoapp.Config;
import com.saucedo.molinoapp.Error;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

	public JSONObject requestPost(String sURL, JSONObject JSONSend) throws Exception {
		URL url = new URL(sURL);
		byte[] postDataBytes = JSONSend.toJSONString().getBytes("UTF-8");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (int c; (c = in.read()) >= 0;)
			sb.append((char) c);
		String response = sb.toString();
		JSONParser parser = new JSONParser();
		JSONObject jsonResult = (JSONObject) parser.parse(response);
		return jsonResult;
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
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				Map<String, String> datos = getDataLogin();
				JSONObject jsonResponse;
				if (datos == null) {
					 JOptionPane.showMessageDialog(Login.this,
							 Error.ERROR_DATA_FIELDS_USER_OR_PASSWORD,"Error",
							 JOptionPane.WARNING_MESSAGE);    
					return;
				}
				JSONObject request = new JSONObject();
				request.put("username", datos.get("username"));
				request.put("password", datos.get("password"));
				try {
					jsonResponse = requestPost(Global.LOGING_PATH, request);
				} catch (Exception e1) {
					 JOptionPane.showMessageDialog(Login.this,
							 Error.ERROR_SESSION_USER_OR_PASSWORD,"Error",
							 JOptionPane.WARNING_MESSAGE);     
					return;
				}

				if (notifySession != null && jsonResponse!=null) {
					String token = (String) jsonResponse.get("token");
					String username =(String) jsonResponse.get("username");
					Set<String> roles = new HashSet<String>((JSONArray) jsonResponse.get("roles")); 
					notifySession.startSession(token,username,roles);
				}

			}

		});
	}
}
