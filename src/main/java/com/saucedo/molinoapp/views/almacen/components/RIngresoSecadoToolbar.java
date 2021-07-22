package com.saucedo.molinoapp.views.almacen.components;

import com.saucedo.molinoapp.views.components.KToolbar;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class RIngresoSecadoToolbar extends KToolbar implements ItemListener{
	public static final String BUTTON_DETALLE = "action.detalle";
	public static final String BUTTON_REGISTRAR_TENDIDO = "action.registrar_tendido";
	public static final String BUTTON_REGISTRAR_RECOJO ="action.registrar_recojo";
	
	public static final String TABLE_VIEW_ONE = "Area de Secado";
	public static final String TABLE_VIEW_TWO = "Tendido";
	public static final String TABLE_VIEW_THREE = "Proceso completado";

	public static final String TABLE_VIEW_HISTORY = "Historial";

	private static final long serialVersionUID = -7200916498009691187L;
	private JButton btnDetalle;
	private JLabel lblRegistroDeIngreso;
	private JComboBox<String> vistaSelected;
	private Component rigidArea_1;
	private ButtonActionToolbarSecado listenerSecado;
	private boolean flag=true;
	private JButton btnRegistroTendido;
	private JButton btnRecogerTendido;
	
	public RIngresoSecadoToolbar(ButtonActionToolbarSecado listener) {
		super(listener);
		listenerSecado = listener;
		btnDetalle = new JButton("Detalles");
		btnDetalle.setForeground(new Color(255, 255, 255));
		btnDetalle.setBackground(new Color(0, 128, 128));
		btnDetalle.addActionListener(this);
		
		btnRegistroTendido = new JButton("Registrar Tendido");
		btnRegistroTendido.setBackground(new Color(250, 128, 114));
		add(btnRegistroTendido);
		btnRegistroTendido.addActionListener(this);
		
		btnRecogerTendido = new JButton("Recoger Tendido");
		btnRecogerTendido.setForeground(new Color(255, 255, 255));
		btnRecogerTendido.setBackground(new Color(75, 0, 130));
		add(btnRecogerTendido);
		add(btnDetalle);
		btnRecogerTendido.addActionListener(this);
		Component rigidArea = Box.createRigidArea(new Dimension(55, 20));
		add(rigidArea);
		
		lblRegistroDeIngreso = new JLabel("Registro de ingreso de al area de secado");
		lblRegistroDeIngreso.setForeground(new Color(0, 128, 128));
		lblRegistroDeIngreso.setFont(new Font("Tahoma", Font.BOLD, 23));
		add(lblRegistroDeIngreso);
		
		rigidArea_1 = Box.createRigidArea(new Dimension(55, 20));
		add(rigidArea_1);
		
		vistaSelected = new JComboBox<String>();
		vistaSelected.setModel(new DefaultComboBoxModel<String>(new String[] {TABLE_VIEW_ONE,TABLE_VIEW_TWO, TABLE_VIEW_THREE}));
		vistaSelected.addItemListener(this);
		add(vistaSelected);
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
			buttonType = RIngresoSecadoToolbar.BUTTON_DETALLE;		
		if(this.btnRecogerTendido == button)
			buttonType = RIngresoSecadoToolbar.BUTTON_REGISTRAR_RECOJO;
		if(this.btnRegistroTendido == button)
			buttonType = RIngresoSecadoToolbar.BUTTON_REGISTRAR_TENDIDO;
	
		if (buttonActionToolbar != null && buttonType!=null) {
			buttonActionToolbar.onClickToolbarOption(buttonType);
		}
	}
	public void setNone() {
		this.btnRecogerTendido.setVisible(false);
		this.btnRegistroTendido.setVisible(false);
		buttonsVisible(true);
	}
	
	public void setTendido() {
		btnRecogerTendido.setVisible(false);
		btnRegistroTendido.setVisible(true);
		buttonsVisible(true);
	}
	public void setRecojo() {
		btnRecogerTendido.setVisible(true);
		btnRegistroTendido.setVisible(false);
		buttonsVisible(true);
	}
	public void setHistory() {
		btnRecogerTendido.setVisible(false);
		btnRegistroTendido.setVisible(false);
		buttonsVisible(false);
	}
	private void buttonsVisible(boolean flag) {
		if (btnDelete !=null) btnDelete.setVisible(flag);
		if (btnUpdate !=null) btnUpdate.setVisible(flag);
		if (btnAddnew !=null) btnAddnew.setVisible(flag);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(listenerSecado!=null) {
			if(flag==true) { listenerSecado.onViewChangeEvent((String)this.vistaSelected.getSelectedItem());}
			flag=!flag;
		}
	}
	
	public interface ButtonActionToolbarSecado extends ButtonActionToolbar{
		public void onViewChangeEvent(String view);
	}

}
