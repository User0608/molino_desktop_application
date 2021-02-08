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
import javax.swing.UIManager;

public class RIngresoToolbar extends KToolbar {
	public static final String BUTTON_DETALLE = "action.detalle";
	/**
	 * 
	 */
	private static final long serialVersionUID = -7200916498009691187L;
	private JTextField txtBuscar;
	private JComboBox<String> cmbFiltroType;
	private JButton btnDetalle;
	
	public RIngresoToolbar(ButtonActionToolbar listener) {
		super(listener);
		
		btnDetalle = new JButton("Detalles");
		btnDetalle.addActionListener(this);
		add(btnDetalle);
		
		Component rigidArea = Box.createRigidArea(new Dimension(55, 20));
		add(rigidArea);
		
		txtBuscar = new JTextField();
		txtBuscar.setToolTipText("Insertar dato a buscar");
		add(txtBuscar);
		txtBuscar.setColumns(40);
		
		cmbFiltroType = new JComboBox<String>();
		cmbFiltroType.setToolTipText("Seleccion de criterio del filtrado");
		cmbFiltroType.setBackground(UIManager.getColor("Button.background"));
		cmbFiltroType.setModel(new DefaultComboBoxModel(new String[] {"Productores"}));
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
