package com.saucedo.molinoapp.views.components;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;

public class KToolbar extends JPanel implements ActionListener {
	public static final String BUTTON_NEW = "action.nevo";
	public static final String BUTTON_UPDATE = "action.update";
	public static final String BUTTON_DELETE = "action.delete";
	
	protected ButtonActionToolbar buttonActionToolbar;

	private static final long serialVersionUID = 4309549832489L;
	protected JButton btnAddnew;
	protected JButton btnUpdate;
	protected JButton btnDelete;
	private JLabel lblTitulo;
	private Component rigidArea;

	public KToolbar(ButtonActionToolbar listener) {
		this.buttonActionToolbar = listener;
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(new Color(220, 220, 220));
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		loadButton();
	}
	protected void loadNewAndUpdateButton() {
		this.btnAddnew = new JButton("Nuevo");
		btnAddnew.setBackground(new Color(0, 255, 0));
		this.btnAddnew.setToolTipText("Agregar nuevo usuario");
		this.btnAddnew.addActionListener(this);
		this.add(this.btnAddnew);

		this.btnUpdate = new JButton("Editar");
		btnUpdate.setBackground(new Color(255, 255, 0));
		this.btnUpdate.setToolTipText("Eliminar un usuari, seleccione usuario");
		this.btnUpdate.setHorizontalAlignment(SwingConstants.RIGHT);
		this.btnUpdate.addActionListener(this);
		this.add(this.btnUpdate);
	}
	protected void loadDeleteButton() {
		this.btnDelete = new JButton("Eliminar");
		btnDelete.setForeground(new Color(255, 255, 255));
		this.btnDelete.setBackground(new Color(220, 20, 60));
		this.btnDelete.addActionListener(this);
		this.add(this.btnDelete);
		
		
		
	}
	public void loadButton() {
		loadNewAndUpdateButton();
		loadDeleteButton();
	}
	
	public void setModeluTitle(String title) {
		rigidArea = Box.createRigidArea(new Dimension(100, 20));
		add(rigidArea);		
		lblTitulo = new JLabel(title);
		lblTitulo.setForeground(new Color(0, 128, 128));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));
		add(lblTitulo);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		String buttonType=null;
		if (this.btnAddnew == button)
			buttonType = KToolbar.BUTTON_NEW;
		else if (this.btnUpdate == button)
			buttonType = KToolbar.BUTTON_UPDATE;
		else if (this.btnDelete==button)
			buttonType = KToolbar.BUTTON_DELETE;
		if (this.buttonActionToolbar != null && buttonType!=null) {
			this.buttonActionToolbar.onClickToolbarOption(buttonType);
		}
	}
	
	public interface ButtonActionToolbar {
		public void onClickToolbarOption(String buttontype);
	}
}
