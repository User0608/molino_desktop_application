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
	public static final String TABLE_VIEW_ONE = "Area de Secado";
	public static final String TABLE_VIEW_TWO = "Tendido";
	public static final String TABLE_VIEW_THREE = "Proceso completado";

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
		
		btnRecogerTendido = new JButton("Recoger Tendido");
		btnRecogerTendido.setForeground(new Color(255, 255, 255));
		btnRecogerTendido.setBackground(new Color(75, 0, 130));
		add(btnRecogerTendido);
		add(btnDetalle);
		
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
	
		if (buttonActionToolbar != null && buttonType!=null) {
			buttonActionToolbar.onClickToolbarOption(buttonType);
		}
	}
	public void setNone() {
		this.btnRecogerTendido.setVisible(false);
		this.btnRegistroTendido.setVisible(false);
	}
	
	public void setTendido() {
		this.btnRecogerTendido.setVisible(false);
		this.btnRegistroTendido.setVisible(true);
	}
	public void setRecojo() {
		this.btnRecogerTendido.setVisible(true);
		this.btnRegistroTendido.setVisible(false);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(listenerSecado!=null) {
			if(flag==true) { listenerSecado.onViewChangeEvent((String)this.vistaSelected.getSelectedItem());}
			flag=!flag;
		}
	}
	public interface ButtonActionToolbarSecado extends ButtonActionToolbar{
		public void onViewChangeEvent(String view);
	}

}
