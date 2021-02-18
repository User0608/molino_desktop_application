package com.saucedo.molinoapp.views.usuario;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;

public class ToolbarUsuario extends JPanel implements ActionListener {
	public static final String BUTTON_NEW = "usuario.nevo";
	public static final String BUTTON_UPDATE = "usuario.update";
	public static final String BUTTON_DELETE = "usuario.delete";
	ButtonActionToolbar buttonActionToolbar;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4309549832489L;
	private JButton btnAddnew;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JLabel lblNewLabel;
	private Component horizontalStrut;

	public ToolbarUsuario(ButtonActionToolbar listener) {
		this.buttonActionToolbar = listener;
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(new Color(220, 220, 220));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		loadButton();
	}

	public void loadButton() {
		this.btnAddnew = new JButton("Nuevo");
		btnAddnew.setBackground(new Color(0, 250, 154));
		this.btnAddnew.setToolTipText("Agregar nuevo usuario");
		this.btnAddnew.addActionListener(this);
		this.add(this.btnAddnew);

		this.btnUpdate = new JButton("Editar");
		btnUpdate.setBackground(new Color(30, 144, 255));
		this.btnUpdate.setToolTipText("Eliminar un usuari, seleccione usuario");
		this.btnUpdate.setHorizontalAlignment(SwingConstants.RIGHT);
		this.btnUpdate.addActionListener(this);
		this.add(this.btnUpdate);

		this.btnDelete = new JButton("Eliminar");
		btnDelete.setForeground(new Color(255, 255, 255));
		this.btnDelete.setBackground(new Color(128, 0, 0));
		this.btnDelete.addActionListener(this);
		this.add(this.btnDelete);
		
		horizontalStrut = Box.createHorizontalStrut(100);
		add(horizontalStrut);
		
		lblNewLabel = new JLabel("Usuarios del sistema");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 128, 128));
		add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String buttonType=null;
		if (this.btnAddnew == button)
			buttonType = ToolbarUsuario.BUTTON_NEW;
		else if (this.btnUpdate == button)
			buttonType = ToolbarUsuario.BUTTON_UPDATE;
		else
			buttonType = ToolbarUsuario.BUTTON_DELETE;
		if (this.buttonActionToolbar != null) {
			this.buttonActionToolbar.onClickToolbarOption(buttonType);
		}
	}
	
	public interface ButtonActionToolbar {
		public void onClickToolbarOption(String buttontype);
	}
}
