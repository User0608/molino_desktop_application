package com.saucedo.molinoapp.main;

import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.views.IMainContainer;
import com.saucedo.molinoapp.views.IMenu;
import java.awt.Color;

public class MainView extends JPanel implements IMenu{

	private static final long serialVersionUID = 323434341L;
	public static final String THIS_WINDOWS_TITLE_VIEW_ONE="Home";
	private	JMenuItem homeView ;
	IMainContainer parent;
	private JLabel lblUsuarioDatos;
	/**
	 * Create the panel.
	 */
	public MainView(IMainContainer parent) {
		setBackground(Color.WHITE);
		this.parent=parent;
		setLayout(null);		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(20, 36, 2041, 1145);
		lblLogo.setIcon(new ImageIcon(MainView.class.getResource("/com/saucedo/molinoapp/imgs/logo.png")));
		add(lblLogo);
		
		lblUsuarioDatos = new JLabel("New label");
		lblUsuarioDatos.setBounds(10, 11, 531, 14);
		add(lblUsuarioDatos);
		this.homeView = new JMenuItem("Inicio");
		this.loadActions();
		if(SessionStatus.isStarted()) {
			SessionStatus s = SessionStatus.getInst();
			String txt= "Username: "+s.getUsername();
			this.lblUsuarioDatos.setText(txt);
		}
	}
	private void loadActions(){
		
		this.homeView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (parent != null)
					parent.setMainPanel(MainView.this, THIS_WINDOWS_TITLE_VIEW_ONE);				
			}
		});
	}
	@Override
	public JMenuItem getMenuItem() {
		return this.homeView;
	}	
}
