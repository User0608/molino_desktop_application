package com.saucedo.molinoapp.views.usuario;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

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

	public ToolbarUsuario(ButtonActionToolbar listener) {
		this.buttonActionToolbar = listener;
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBackground(SystemColor.control);
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		loadButton();
	}

	public void loadButton() {
		this.btnAddnew = new JButton("Nuevo");
		this.btnAddnew.setToolTipText("Agregar nuevo usuario");
		this.btnAddnew.addActionListener(this);
		this.add(this.btnAddnew);

		this.btnUpdate = new JButton("Editar");
		this.btnUpdate.setToolTipText("Eliminar un usuari, seleccione usuario");
		this.btnUpdate.setHorizontalAlignment(SwingConstants.RIGHT);
		this.btnUpdate.addActionListener(this);
		this.add(this.btnUpdate);

		this.btnDelete = new JButton("Eliminar");
		this.btnDelete.setBackground(UIManager.getColor("ToggleButton.light"));
		this.btnDelete.addActionListener(this);
		this.add(this.btnDelete);
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
