package com.saucedo.molinoapp.views.almacen;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.almacen.JCamionProductor;
import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molino_json_models.almacen.JProcedencia;
import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molino_json_models.almacen.JRegistroIngreso;
import com.saucedo.molino_json_models.almacen.JTipoArroz;
import com.saucedo.molino_json_models.personal.JEmpleado;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;
import com.saucedo.molinoapp.services.ProductorService;
import com.saucedo.molinoapp.services.Service;
import com.saucedo.molinoapp.services.parseimplements.FParse;
import com.saucedo.molinoapp.utils.KCheck;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.error_message.General;
import com.saucedo.molinoapp.Error;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class RegistroIngresoDialog extends JDialog implements ActionListener, ISubmitDialog {
	/**
	 * 
	 */
	public static final String DIALOG_KEY = "com.saucedo.molinoapp.views.almacen.TipoArrozDialog";
	private static final long serialVersionUID = 143243L;
	public static final String TITLE_MODE_EDIT = "Editar el tipo de arroz";
	public static final String TITLE_MODE_NEW = "Nuevo tipo de arroz";

	public static final String MODE_EDIT = "productor.edit";
	public static final String MODE_NEW = "productor.new";
	private ProcedenciaDialog procedenciaDialog;
	private TipoArrozDialog tipoarrozDialog;

	// Lists of combo box
	private List<JProcedencia> procedencias;
	private List<JTipoArroz> tiposdeArroz;
	private ProductorService service;
	private Service<JProcedencia> procedenciaService;
	private Service<JTipoArroz> tipoarrozService;
	private Service<JEmpleado> emepleadoService;

	
	private String mode;
	ISubmitDialog targetListener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumeroSacos;
	private JTextField txtKilosSaco;
	private JTextField txtKilosTotal;
	private JTextField txtPlaca;
	private JTextField txtChofer;
	private JTextField txtDniProductor;
	private JComboBox<String> cmbTipoArroz;
	private JButton btnNewArroz;
	private JComboBox<String> cmbProcedencia;
	private JButton btnNewProcedencia;
	private JTextArea txtDescripcionV;
	private JButton btnSubmit;
	private JLabel lblErrorMessage;
	private JTextField txtNombreProductor;
	/// Asosiated entities
	private JProductor productor;
	private JProcedencia procedencia;
	private JTipoArroz tipoarroz;
	private JEmpleado empleado;
	private JCamionProductor camion;
	private JRegistroIngreso ingreso;
	private JLoteArroz lotearroz;

	public RegistroIngresoDialog(ISubmitDialog target, JRegistroIngreso ingreso) {
		this(target);
		this.ingreso = ingreso;
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public RegistroIngresoDialog(ISubmitDialog target) {
		this.lotearroz = new JLoteArroz();
		this.service = new ProductorService(FParse.getProductorParse(), new Route(Route.ROUTE_PRODUCTOR));
		this.procedenciaService = new Service<JProcedencia>(FParse.getProcedenciaParse(),
				new Route(Route.ROUTE_ARROZ_PROCEDENCIA));
		this.tipoarrozService = new Service<JTipoArroz>(FParse.getTipoArrozParse(), new Route(Route.ROUTE_ARROZ_TIPO));
		this.emepleadoService = new Service<JEmpleado>(FParse.getEmpleadoParse(),new Route(Route.ROUTE_EMPLEADO));
		this.empleadoGet();
		setResizable(false);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.mode = MODE_NEW;
		this.setTitle("Registro");
		this.targetListener = target;
		setBounds(100, 100, 376, 517);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(138, 416, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);

		txtNumeroSacos = new JTextField();
		txtNumeroSacos.setBounds(22, 132, 106, 20);
		contentPanel.add(txtNumeroSacos);
		txtNumeroSacos.setColumns(10);

		JLabel lblNewLabel = new JLabel("Numero sacos");
		lblNewLabel.setBounds(21, 109, 89, 14);
		contentPanel.add(lblNewLabel);

		txtDniProductor = new JTextField();
		txtDniProductor.setBounds(231, 58, 113, 20);
		contentPanel.add(txtDniProductor);
		txtDniProductor.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Productor");
		lblNewLabel_1.setBounds(21, 36, 149, 14);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("DNI productor");
		lblNewLabel_2.setBounds(231, 36, 106, 14);
		contentPanel.add(lblNewLabel_2);

		txtKilosSaco = new JTextField();
		txtKilosSaco.setBounds(138, 132, 90, 20);
		contentPanel.add(txtKilosSaco);
		txtKilosSaco.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Kilos por saco");
		lblNewLabel_3.setBounds(137, 109, 90, 14);
		contentPanel.add(lblNewLabel_3);

		txtKilosTotal = new JTextField();
		txtKilosTotal.setBounds(238, 132, 107, 20);
		contentPanel.add(txtKilosTotal);
		txtKilosTotal.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Total kilos");
		lblNewLabel_4.setBounds(241, 109, 86, 14);
		contentPanel.add(lblNewLabel_4);

		JSeparator separator = new JSeparator();
		separator.setBounds(24, 286, 323, 2);
		contentPanel.add(separator);

		txtPlaca = new JTextField();
		txtPlaca.setBounds(23, 311, 106, 20);
		contentPanel.add(txtPlaca);
		txtPlaca.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Datos vehiculares");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(24, 270, 157, 14);
		contentPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Placa");
		lblNewLabel_6.setBounds(23, 295, 73, 14);
		contentPanel.add(lblNewLabel_6);

		txtChofer = new JTextField();
		txtChofer.setBounds(139, 311, 207, 20);
		contentPanel.add(txtChofer);
		txtChofer.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Nombre chofer");
		lblNewLabel_7.setBounds(140, 295, 98, 14);
		contentPanel.add(lblNewLabel_7);

		txtDescripcionV = new JTextArea();
		txtDescripcionV.setToolTipText("Campo opcional, registro de descripcion");
		txtDescripcionV.setLineWrap(true);
		txtDescripcionV.setBorder(UIManager.getBorder("TextField.border"));
		txtDescripcionV.setBounds(22, 359, 323, 41);
		contentPanel.add(txtDescripcionV);

		JLabel lblNewLabel_8 = new JLabel("Comentario");
		lblNewLabel_8.setBounds(23, 334, 323, 14);
		contentPanel.add(lblNewLabel_8);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(21, 102, 323, 2);
		contentPanel.add(separator_1);

		JLabel lblNewLabel_9 = new JLabel("Datos del arroz");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(21, 84, 132, 14);
		contentPanel.add(lblNewLabel_9);

		cmbTipoArroz = new JComboBox<String>();
		cmbTipoArroz.setBounds(23, 180, 238, 22);
		contentPanel.add(cmbTipoArroz);

		JLabel lblNewLabel_10 = new JLabel("Tipo de arroz");
		lblNewLabel_10.setBounds(22, 155, 107, 14);
		contentPanel.add(lblNewLabel_10);

		btnNewArroz = new JButton("Nuevo");
		btnNewArroz.setToolTipText("Agregar nuevo tipo de arroz");
		btnNewArroz.setBounds(271, 180, 73, 23);
		contentPanel.add(btnNewArroz);

		cmbProcedencia = new JComboBox<String>();
		cmbProcedencia.setBounds(23, 236, 238, 22);
		contentPanel.add(cmbProcedencia);

		btnNewProcedencia = new JButton("Nuevo");
		btnNewProcedencia.setToolTipText("Agregar nueva procedencia");
		btnNewProcedencia.setBounds(271, 236, 73, 23);
		contentPanel.add(btnNewProcedencia);

		JLabel lblNewLabel_11 = new JLabel("Procedencia");
		lblNewLabel_11.setBounds(22, 211, 106, 14);
		contentPanel.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Registro de entrada al molino");
		lblNewLabel_12.setForeground(SystemColor.textHighlight);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBounds(88, 11, 199, 14);
		contentPanel.add(lblNewLabel_12);

		lblErrorMessage = new JLabel("New label");
		lblErrorMessage.setVerticalAlignment(SwingConstants.TOP);
		lblErrorMessage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(22, 450, 321, 23);
		this.lblErrorMessage.setVisible(false);
		contentPanel.add(lblErrorMessage);

		txtNombreProductor = new JTextField();
		txtNombreProductor.setEditable(false);
		txtNombreProductor.setBounds(22, 58, 205, 20);
		contentPanel.add(txtNombreProductor);
		txtNombreProductor.setColumns(10);
		this.prepareAllEvents();
		this.loadDataToComboBox();
	}

	public void loadDataToComboBox() {
		try {
			this.procedencias = this.procedenciaService.findAll();
			this.tiposdeArroz = this.tipoarrozService.findAll();
		} catch (ResponseException e) {
			this.showBoxMessage(e.getMessage());
			e.printStackTrace();
		}

		this.cmbProcedencia.removeAllItems();
		this.cmbTipoArroz.removeAllItems();
		if (this.procedencias != null) {
			List<String> values = new ArrayList<String>();
			for (JProcedencia p : this.procedencias) {
				values.add(p.getLugar());
			}
			this.cmbProcedencia.setModel(new DefaultComboBoxModel<String>(values.toArray(new String[0])));
		}
		if (this.tiposdeArroz != null) {
			List<String> values = new ArrayList<String>();
			for (JTipoArroz tpa : this.tiposdeArroz) {
				values.add(tpa.getNombre());
			}
			this.cmbTipoArroz.setModel(new DefaultComboBoxModel<String>(values.toArray(new String[0])));
		}
	}


	private void prepareAllEvents() {

		KeyAdapter keyListener = new KeyAdapter() {
			private String message;

			private boolean validField(String field) {
				KCheck check = new KCheck();
				if (check.in(field).noEmptySpaces().onlyNumbers().maxLen(8).notOk()) {
					message = check.getMessage();
					return false;
				}

				message = "";
				return true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				JTextField t = (JTextField) e.getSource();
				lblErrorMessage.setVisible(false);
				t.setForeground(Color.BLACK);
				if (this.validField(t.getText())) {
					String dni = t.getText();
					if (dni.length() == 8) {						
						try {
							productor = service.getProductorByDNI(dni);
						} catch (ResponseException e1) {
							showBoxMessage("Error al buscar los datos de productores.");
						}
						if (productor != null) {
							txtNombreProductor
									.setText(productor.getApellidoPaterno() + " " + productor.getApellidoMaterno());
							RegistroIngresoDialog.this.productor = productor;
							t.setForeground(Color.BLUE);
						} else {
							lblErrorMessage.setVisible(true);
							t.setForeground(Color.ORANGE);
							txtNombreProductor.setText("");
							lblErrorMessage.setText("Productor no encontrado");
						}
					}
				} else {
					lblErrorMessage.setVisible(true);
					t.setForeground(Color.RED);
					lblErrorMessage.setText(message + ", campo dni productor");
				}
			}

		};
		ActionListener listenerNewArrozType = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tipoarrozDialog = new TipoArrozDialog(RegistroIngresoDialog.this);
				tipoarrozDialog.prepareForm();
				tipoarrozDialog.setVisible(true);
			}

		};
		ActionListener listenerBtnProcedencia = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				procedenciaDialog = new ProcedenciaDialog(RegistroIngresoDialog.this);
				procedenciaDialog.prepareForm();
				procedenciaDialog.setVisible(true);
			}

		};

		this.txtDniProductor.addKeyListener(keyListener);
		this.btnNewArroz.addActionListener(listenerNewArrozType);
		this.btnNewProcedencia.addActionListener(listenerBtnProcedencia);
	}

	public void prepareForm() {
		if (mode.equals(MODE_EDIT)) {

		}
		// this.loadProductorToFilds();
	}


	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String placa = this.txtPlaca.getText();
		String chofer = this.txtChofer.getText();
		String descripcion = this.txtChofer.getText();
		
		String numeroSacos = this.txtNumeroSacos.getText();
		String kilosSaco = this.txtKilosSaco.getText();
		String totalKilos = this.txtKilosTotal.getText();
		
		if(check.in(placa).onlyBasicsCaracteres().noEmptySpaces().maxLen(12).minLen(6).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		if(check.in(chofer).onlyBasicsCaracteres().noInvalidSpaces().maxLen(60).minLen(2).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		if(check.in(descripcion).onlyBasicsCaracteres().noInvalidSpaces().maxLen(200).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		if(check.in(numeroSacos).noEmptySpaces().onlyNumbers().maxLen(12).minLen(1).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		if(check.in(kilosSaco).noEmptySpaces().isNumber().maxLen(12).minLen(1).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		if(check.in(totalKilos).noEmptySpaces().isNumber().maxLen(12).minLen(1).notOk()) {
			this.showBoxMessage(check.getMessage());
			return false;
		}
		 
		int index = this.cmbTipoArroz.getSelectedIndex();
		if(this.tiposdeArroz.size()>index) {
			this.tipoarroz = this.tiposdeArroz.get(index);
		}
	
		index = this.cmbProcedencia.getSelectedIndex();
		if(this.procedencias.size()>index) {
			this.procedencia = this.procedencias.get(index);
		}
		
		//Definicion detalle ingreso
		this.ingreso = new JRegistroIngreso();
		this.ingreso.setNumeroSacos(Integer.parseInt(numeroSacos));
		this.ingreso.setKilosPorSaco(Integer.parseInt(kilosSaco));
		this.ingreso.setTotalKilos(Integer.parseInt(totalKilos));		
		this.camion = new JCamionProductor(placa,chofer,descripcion);	
		this.ingreso.setTransporte(camion);		
		if(this.empleado==null) {
			this.showBoxMessage("Error en las session.");			
			return false;
		}
		this.ingreso.setEmpleado(empleado);
		
		//Definicion lote
		this.lotearroz.setIngreso(ingreso);
		if(this.productor!=null) {
			this.lotearroz.setProductor(productor);
		}else {
			this.showBoxMessage("Productor no registrado.");
			return false;
		}
		this.lotearroz.setNumeroSacos(Integer.parseInt(numeroSacos));		
		this.lotearroz.setTipoArroz(tipoarroz);
		this.lotearroz.setProcedencia(procedencia);
		return true;
	}
	private void empleadoGet() {
		String[] data = SessionStatus.getInst().getOwner().split("[.]+");
		try {
			this.empleado= this.emepleadoService.findByField(data[1]);
			System.out.println(this.empleado.toString());
		} catch (ResponseException e) {
			this.showBoxMessage(Error.ERROR_BASIC);
			e.printStackTrace();
		}
	}
//	private void loadEntity() {
//		

	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.lotearroz, this.mode, DIALOG_KEY);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
	}

	@Override
	public void notifyDialogAction(Object model, String mode, String senderKey) {
		if (this.procedenciaDialog != null)
			this.procedenciaDialog.setVisible(false);
		if (this.tipoarrozDialog != null)
			this.tipoarrozDialog.setVisible(false);
		switch (senderKey) {
		case ProcedenciaDialog.DIALOG_KEY:
			this.procedencia = (JProcedencia) model;
			String lugar = this.procedencia.getLugar();
			this.cmbProcedencia.addItem(lugar);
			this.cmbProcedencia.setSelectedItem(lugar);
			break;
		case TipoArrozDialog.DIALOG_KEY:
			this.tipoarroz = (JTipoArroz) model;
			String tipo = this.tipoarroz.getNombre();
			this.cmbTipoArroz.addItem(tipo);
			this.cmbTipoArroz.setSelectedItem(tipo);
			
			break;
		}
	}
}
