package com.saucedo.molinoapp.views.almacen;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.almacen.JProcedencia;
import com.saucedo.molinoapp.utils.KCheck;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.error_message.General;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProcedenciaDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	public static final String DIALOG_KEY ="com.saucedo.molinoapp.views.almacen.ProcedenciaDialog";
	private static final long serialVersionUID = 143243L;
	public static final String TITLE_MODE_EDIT = "Editar el tipo de arroz";
	public static final String TITLE_MODE_NEW = "Nuevo tipo de arroz";
	
	public static final String MODE_EDIT = "productor.edit";
	public static final String MODE_NEW = "productor.new";
	private JProcedencia procedencia;
	private String mode;
	ISubmitDialog targetListener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtLugar;

	public ProcedenciaDialog(ISubmitDialog target, JProcedencia procedencia) {
		this(target);
		this.procedencia = procedencia;
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ProcedenciaDialog(ISubmitDialog target) {
		this.procedencia = new JProcedencia();
		this.setModal(true);
		this.mode = MODE_NEW;
		this.setTitle(TITLE_MODE_NEW);
		this.targetListener = target;
		setBounds(100, 100, 359, 191);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(127, 111, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);
		
		txtLugar = new JTextField();
		txtLugar.setBounds(29, 53, 281, 20);
		contentPanel.add(txtLugar);
		txtLugar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Lugar de procedencia");
		lblNewLabel.setBounds(29, 28, 189, 14);
		contentPanel.add(lblNewLabel);
	}

	public void prepareForm() {
		if (mode.equals(MODE_EDIT)) {
			
		}
			//this.loadProductorToFilds();
	}

	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String lugar = this.txtLugar.getText();
		if(check.in(lugar).noInvalidSpaces().onlyBasicsCaracteres().notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		this.procedencia = new JProcedencia(lugar);
		return true;
	}

//	private void loadEntity() {
//		if (this.procedencia != null) {
//			
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.procedencia, this.mode,DIALOG_KEY);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
	}
}
