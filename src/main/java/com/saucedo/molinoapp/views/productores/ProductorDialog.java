package com.saucedo.molinoapp.views.productores;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molinoapp.Config;
import com.saucedo.molinoapp.utils.KCheck;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.error_message.General;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductorDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	public static final String DIALOG_KEY ="com.saucedo.molinoapp.views.productores.ProductoresDialog";
	private static final long serialVersionUID = 143243L;
	public static final String TITLE_MODE_EDIT = "Editar productor";
	public static final String TITLE_MODE_NEW = "Nuevo productor";
	
	public static final String MODE_EDIT = "productor.edit";
	public static final String MODE_NEW = "productor.new";
	private JProductor productor;
	private String mode;
	ISubmitDialog targetListener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDni;
	private JTextField txtNombre;
	private JTextField txtApellidoP;
	private JTextField txtApellidoM;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;

	public ProductorDialog(ISubmitDialog target, JProductor productor) {
		this(target);
		this.productor = productor;
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public ProductorDialog(ISubmitDialog target) {
		this.productor = new JProductor();
		this.mode = MODE_NEW;
		this.setTitle(TITLE_MODE_NEW);
		this.targetListener = target;
		setBounds(100, 100, 530, 291);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JButton btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(220, 215, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);

		txtDni = new JTextField();
		txtDni.setToolTipText("Dni del productor");
		txtDni.setBounds(35, 36, 127, 20);
		contentPanel.add(txtDni);
		txtDni.setColumns(10);

		txtNombre = new JTextField();
		txtNombre.setBounds(35, 81, 174, 20);
		contentPanel.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidoP = new JTextField();
		txtApellidoP.setBounds(35, 125, 174, 20);
		contentPanel.add(txtApellidoP);
		txtApellidoP.setColumns(10);

		txtApellidoM = new JTextField();
		txtApellidoM.setBounds(35, 169, 174, 20);
		contentPanel.add(txtApellidoM);
		txtApellidoM.setColumns(10);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(274, 81, 226, 20);
		contentPanel.add(txtDireccion);
		txtDireccion.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(274, 125, 153, 20);
		contentPanel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(274, 169, 226, 20);
		contentPanel.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel = new JLabel("DNI");
		lblNewLabel.setBounds(35, 23, 127, 14);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setBounds(35, 67, 142, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Apellido Paterno");
		lblNewLabel_2.setBounds(35, 112, 174, 14);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Apellido Materno");
		lblNewLabel_3.setBounds(35, 156, 174, 14);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Direccion");
		lblNewLabel_4.setBounds(274, 67, 226, 14);
		contentPanel.add(lblNewLabel_4);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(274, 112, 153, 14);
		contentPanel.add(lblTelefono);

		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(274, 156, 226, 14);
		contentPanel.add(lblNewLabel_5);
	}

	public void prepareForm() {
		if (mode.equals(MODE_EDIT))
			this.loadProductorToFilds();
	}

	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String dni = this.txtDni.getText();
		String nombre = this.txtNombre.getText();
		String apellidop = this.txtApellidoP.getText();
		String apellidom = this.txtApellidoM.getText();
		String direccion = this.txtDireccion.getText();
		String telefono = this.txtTelefono.getText();
		String email = this.txtEmail.getText();	
		if (check.in(dni).onlyNumbers().noEmptySpaces().allowLength(Config.DNI_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo DNI");
			return false;
		}

		if (check.in(nombre).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces().minLen(Config.MIN_LENGTH)
				.notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo nombre");
			return false;
		}
		if (check.in(apellidop).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces()
				.minLen(Config.MIN_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo apellido paterno");
			return false;
		}
		if (check.in(apellidom).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces()
				.minLen(Config.MIN_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo apellido materno");
			return false;
		}
		if (check.in(direccion).noStartWithEmptySpaces().noEndWithEmptySpaces().noInvalidSpaces()
				.minLen(Config.MIN_LENGTH).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo dirección");
			return false;
		}
		if (check.in(telefono).onlyNumbers().noEmptySpaces().minLen(Config.MIN_LENGTH_PHONE_NUMBRE)
				.maxLen(Config.MAX_LENGTH_PHONE_NUMBRE).notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo telefono");
			return false;
		}
		if (check.in(email).noEmptySpaces().minLen().isEmail().notOk()) {
			this.showBoxMessage(check.getMessage() + ", campo email");
			return false;
		}
		this.productor.setDni(dni);
		this.productor.setNombre(nombre);
		this.productor.setApellidoPaterno(apellidop);
		this.productor.setApellidoMaterno(apellidom);
		this.productor.setDireccion(direccion);
		this.productor.setTelefon(telefono);
		this.productor.setEmail(email);
		return true;

	}

	private void loadProductorToFilds() {
		if (this.productor != null) {
			this.txtDni.setText(this.productor.getDni());
			this.txtNombre.setText(this.productor.getNombre());
			this.txtApellidoP.setText(this.productor.getApellidoPaterno());
			this.txtApellidoM.setText(this.productor.getApellidoMaterno());
			this.txtDireccion.setText(this.productor.getDireccion());
			this.txtTelefono.setText(this.productor.getTelefon());
			this.txtEmail.setText(this.productor.getEmail());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.productor, this.mode,DIALOG_KEY);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
	}

}
