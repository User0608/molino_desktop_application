package com.saucedo.molinoapp.views.almacen.components;
import javax.swing.JTextField;

import com.saucedo.molinoapp.views.components.KToolbar;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class RIngresoToolbar extends KToolbar {
	public static final String BUTTON_DETALLE = "action.detalle";
	/**
	 * 
	 */
	private static final long serialVersionUID = -7200916498009691187L;
	private JTextField txtBuscar;
	private JComboBox<String> cmbFiltroType;
	private JButton btnDetalle;
	private JLabel lblRegistroDeIngreso;
	private Component rigidArea_1;
	
	public RIngresoToolbar(ButtonActionToolbar listener) {
		super(listener);
		
		btnDetalle = new JButton("Detalles");
		btnDetalle.setForeground(new Color(255, 255, 255));
		btnDetalle.setBackground(new Color(0, 128, 128));
		btnDetalle.addActionListener(this);
		add(btnDetalle);
		
		Component rigidArea = Box.createRigidArea(new Dimension(55, 20));
		add(rigidArea);
		
		lblRegistroDeIngreso = new JLabel("Registro de ingreso de arroz al molino");
		lblRegistroDeIngreso.setForeground(new Color(0, 128, 128));
		lblRegistroDeIngreso.setFont(new Font("Tahoma", Font.BOLD, 23));
		add(lblRegistroDeIngreso);
		
		rigidArea_1 = Box.createRigidArea(new Dimension(55, 20));
		add(rigidArea_1);
		
		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("Insertar dato a buscar");
		add(txtBuscar);
		txtBuscar.setColumns(20);
		
		cmbFiltroType = new JComboBox<String>();
		cmbFiltroType.setForeground(new Color(255, 255, 255));
		cmbFiltroType.setToolTipText("Seleccion de criterio del filtrado");
		cmbFiltroType.setBackground(new Color(178, 34, 34));
		cmbFiltroType.setModel(new DefaultComboBoxModel<String>(new String[] {"Productores"}));
		add(cmbFiltroType);
	}

	@Override
	public void loadButton() {
		super.loadNewAndUpdateButton();
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		JButton button = (JButton) e.getSource();
		String buttonType=null;
		if (this.btnDetalle == button)
			buttonType = RIngresoToolbar.BUTTON_DETALLE;		
	
		if (buttonActionToolbar != null && buttonType!=null) {
			buttonActionToolbar.onClickToolbarOption(buttonType);
		}
	}
	
	

}
