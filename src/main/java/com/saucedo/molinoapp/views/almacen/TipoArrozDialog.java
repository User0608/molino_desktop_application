package com.saucedo.molinoapp.views.almacen;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.almacen.JTipoArroz;
import com.saucedo.molinoapp.utils.KCheck;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.error_message.General;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class TipoArrozDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	public static final String DIALOG_KEY ="com.saucedo.molinoapp.views.almacen.TipoArrozDialog";
	private static final long serialVersionUID = 143243L;
	public static final String TITLE_MODE_EDIT = "Editar el tipo de arroz";
	public static final String TITLE_MODE_NEW = "Nuevo tipo de arroz";
	
	public static final String MODE_EDIT = "productor.edit";
	public static final String MODE_NEW = "productor.new";
	private JTipoArroz tipoArroz;
	private String mode;
	ISubmitDialog targetListener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextArea txtDescripction;

	public TipoArrozDialog(ISubmitDialog target, JTipoArroz tipoArroz) {
		this(target);
		this.tipoArroz = tipoArroz;
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public TipoArrozDialog(ISubmitDialog target) {
		this.tipoArroz = new JTipoArroz();
		this.mode = MODE_NEW;
		this.setTitle(TITLE_MODE_NEW);
		this.setModal(true);
		this.targetListener = target;
		setBounds(100, 100, 368, 241);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(145, 168, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(26, 40, 222, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDescripction = new JTextArea();
		txtDescripction.setBounds(26, 96, 303, 57);
		contentPanel.add(txtDescripction);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(26, 15, 189, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion");
		lblNewLabel_1.setBounds(26, 71, 254, 14);
		contentPanel.add(lblNewLabel_1);
	}

	public void prepareForm() {
		if (mode.equals(MODE_EDIT)) {
			
		}
			//this.loadProductorToFilds();
	}

	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String tipo = this.txtNombre.getText();
		String descripcion = this.txtDescripction.getText();
		if(check.in(tipo).noInvalidSpaces().onlyBasicsCaracteres().maxLen(60).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		if(check.in(descripcion).noInvalidSpaces().onlyBasicsCaracteres().maxLen(200).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;			
		}
		this.tipoArroz = new JTipoArroz(tipo,descripcion);			
		return true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.tipoArroz, this.mode,DIALOG_KEY);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
		
	}
}
