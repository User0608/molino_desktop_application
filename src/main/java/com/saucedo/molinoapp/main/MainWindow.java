package com.saucedo.molinoapp.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saucedo.molino_json_models.security.SessionResponse;
import com.saucedo.molinoapp.Config;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.almacen.RegistroIngresoSecadoView;
import com.saucedo.molinoapp.views.almacen.RegistroIngresoView;
import com.saucedo.molinoapp.views.login.ISession;
import com.saucedo.molinoapp.views.login.Login;
import com.saucedo.molinoapp.views.personal.EmpleadoView;
import com.saucedo.molinoapp.views.productores.ProductorView;
import com.saucedo.molinoapp.views.usuario.*;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow implements IMainContainer, ISession {

	private JFrame frmMolino;
	private JPanel statusbar;
	private JPanel containerPanel;
	private JMenuBar menubar;

	private Map<String, JMenu> menus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMolino.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		this.menus = new HashMap<String, JMenu>();
		this.initialize();
		this.loadLogin();
		// this.loadOthers();
		//this.loadAdminView();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMolino = new JFrame();
		frmMolino.setTitle("Molino");
		frmMolino.setBounds(100, 100, 676, 397);
		frmMolino.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMolino.getContentPane().setLayout(new BorderLayout(0, 0));

		statusbar = new JPanel();
		frmMolino.getContentPane().add(statusbar, BorderLayout.SOUTH);

		containerPanel = new JPanel();
		containerPanel.setToolTipText("");
		containerPanel.setForeground(Color.GRAY);
		frmMolino.getContentPane().add(containerPanel, BorderLayout.CENTER);
		containerPanel.setLayout(new BorderLayout(0, 0));

		menubar = new JMenuBar();
		frmMolino.setJMenuBar(menubar);
		frmMolino.setMinimumSize(new Dimension(Config.MIN_WIDTH_MAIN_WINDOWS,Config.MIN_HEIGHT_MAIN_WINDOWS));
		
	}

	private void loadLogin() {
		this.setMainPanel(new Login(this),"Login");
	}

	private void removeLoginAndStartSystem() {
		this.setMainPanel(new JPanel(),"Principal"); // TODO menu de bienvenida
	}

	private void loadOthers() {
		loadCloseSessionButton();
		this.removeLoginAndStartSystem();
		List<String> roles = SessionStatus.getInst().getRoles(); // TODO
		for (String role : roles) {
			switch (role) {
			case Role.ADMIN:
					this.loadAdminView();
				break;
			case Role.RECEPCION:
				this.loadProductoresView();
				this.loadRegistroIngresoView();
			break;
			}
		}

	}

	private void loadAdminView() {
		UsuarioPanel usuarioPanel = new UsuarioPanel(this);
		this.addMenuItem(usuarioPanel.getMenuItem(), MenuCategory.MANAGER_CATEGORY);
		loadProductoresView();
		loadEmpleadoView();
		loadRegistroIngresoView();
		loadRegistroIngresoSecadoView();
	}
	
	private void loadProductoresView() {
		ProductorView produtorview = new ProductorView(this);
		this.addMenuItem(produtorview.getMenuItem(), MenuCategory.REGISTRO_CATEGORY);
	}
	private void loadEmpleadoView() {
		EmpleadoView empleadoView = new EmpleadoView(this);
		this.addMenuItem(empleadoView.getMenuItem(), MenuCategory.MANAGER_CATEGORY);
	}
	private void loadRegistroIngresoView(){
		RegistroIngresoView ingreso =  new RegistroIngresoView(this);
		this.addMenuItem(ingreso.getMenuItem(),MenuCategory.REGISTRO_CATEGORY);
	}
	private void loadRegistroIngresoSecadoView() {
		RegistroIngresoSecadoView registro = new RegistroIngresoSecadoView(this);
		this.addMenuItem(registro.getMenuItem(), MenuCategory.REGISTRO_CATEGORY);
	}
	private void loadCloseSessionButton() {
		JMenuItem closeSession = new JMenuItem("Salir");
		closeSession.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menubar.removeAll();
				menubar.revalidate();
				menubar.repaint();
				SessionStatus.destroySession();
				loadLogin();
				menus = new HashMap<String, JMenu>();
			}
		});
		this.addMenuItem(closeSession, MenuCategory.START_HOME);
	}

	private void addMenuItem(JMenuItem item, String category) {
		JMenu menu = this.menus.get(category);
		if (menu == null) {
			menu = new JMenu(category);
			this.menus.put(category, menu);
			this.menubar.add(menu);
		}
		menu.add(item);
	}

	// Method implemented from the interface IModule
	@Override
	public void setMainPanel(JPanel panel,String title) {
		this.frmMolino.setTitle(title);
		containerPanel.removeAll();
		containerPanel.revalidate();
		containerPanel.repaint();
		containerPanel.add(panel, BorderLayout.CENTER);
	}

	// this method is execute for the login form.

	@Override
	public void startSession(SessionResponse r) {		
		SessionStatus.getInst().setUsername(r.getUsername());
		SessionStatus.getInst().setToken(r.getToken());
		SessionStatus.getInst().setRoles(r.getRoles());
		SessionStatus.getInst().setOwner(r.getOwner());		
		this.loadOthers();
	}
}
