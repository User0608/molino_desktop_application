package com.saucedo.molinoapp.views.usuario;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.saucedo.molinoapp.views.IMainContainer;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

public class UsuarioPanel extends JPanel implements ToolbarUsuario.ButtonActionToolbar {

	private static final long serialVersionUID = 12143544532L;
	private IMainContainer parent;
	private JTable table;
	private JMenuItem menuitem;
	private ToolbarUsuario toolbar;

	/**
	 * Create the panel.
	 */
	public UsuarioPanel(IMainContainer parent) {
		this.parent=parent;
		setBackground(Color.ORANGE);
		this.menuitem=new JMenuItem("Usuarios");
		setLayout(new BorderLayout());
		this.initializeComponents();
		this.loadTable();
	}
	
	public JMenuItem getMenuItem() {
		return this.menuitem;
	}
	private void initializeComponents() {
		this.toolbar =new ToolbarUsuario(this);
		this.add(toolbar,BorderLayout.NORTH);
		this.menuitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(parent!=null) parent.setMainPanel(UsuarioPanel.this);				
			}			
		});
	}
	///Codigo para la tabla
	public void loadTable() {
		
		DefaultTableModel modeltable = new DefaultTableModel();
		modeltable.setDataVector(this.dataInitialize(),this.generateColumnNames());
		this.table = new JTable(modeltable);		
		this.table.setRowSelectionAllowed(true);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public Object[] generateColumnNames() {
		Object[] columns= {"Nombre","Apellido"};
		return columns;
	}
	public Object[][] dataInitialize() {
		Object[][] data = {{"Kevin","Saucedo"},
				{"Kev111in","Saucedo"},{"Kevin","Saucedo"},
						
				
				{"Kev111in","Saucedo"},{"Kevin","Saucedo"},
				{"Kevin","Saucedo"},{"Kevin","Saucedo"},
				{"Kevin","Saucedo"},{"Kevin","Saucedo"},
				{"Kevin","Saucedo"},{"Kevin","Saucedo"},
				{"Kevin","Saucedo"},{"Kevin","Saucedo"},
				{"Kevin","Saucedo"},{"Kevin","Saucedo"}
			};
		return data;
	}

	@Override
	public void onClickToolbarOption(String buttontype) {
		// TODO Auto-generated method stub
		System.out.println(buttontype);
		System.out.println(this.table.getSelectedRow());
	}
}
