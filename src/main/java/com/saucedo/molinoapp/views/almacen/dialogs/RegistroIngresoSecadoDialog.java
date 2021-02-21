package com.saucedo.molinoapp.views.almacen.dialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.almacen.JAreaSecado;
import com.saucedo.molino_json_models.almacen.JIngresoSecado;
import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molino_json_models.almacen.JLoteSecado;
import com.saucedo.molino_json_models.almacen.JRegistroIngreso;
import com.saucedo.molino_json_models.almacen.JUbicacion;
import com.saucedo.molino_json_models.personal.JEmpleado;
import com.saucedo.molinoapp.Route;
import com.saucedo.molinoapp.SessionStatus;
import com.saucedo.molinoapp.exceptions.ResponseException;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;

public class RegistroIngresoSecadoDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	public static final String DIALOG_KEY = "com.saucedo.molinoapp.views.almacen.RegistroIngresoSecadoDialog";
	private static final long serialVersionUID = 143243L;
	public static final String TITLE_MODE_EDIT = "Editar datos ingreso area secado";
	public static final String TITLE_MODE_NEW = "Ingreso al area de secado";

	public static final String MODE_EDIT = "productor.edit";
	public static final String MODE_NEW = "productor.new";

	private Service<JEmpleado> emepleadoService;
	private Service<JLoteArroz> loteArrozSevice;
	private Service<JAreaSecado> areaSecadoService;

	private String mode;
	ISubmitDialog targetListener;

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNumeroSacos;
	private JTextField txtHumedad;
	private JComboBox<String> cmbLoteArroz;
	private JComboBox<String> cmbAreaSecado;
	private JComboBox<String> cmbUbicacion;
	private JButton btnSubmit;
	/// Asosiated entities

	private JEmpleado empleado;
	private JLoteArroz loteArroz;
	private JUbicacion ubicacion;
	//return
	private JLoteSecado loteSecado;
	// Otros elementos,
	private List<JLoteArroz> lotesDeArroz;
	private List<JAreaSecado> areasdeSecado;
	private List<JUbicacion> ubicaciones;

	public RegistroIngresoSecadoDialog(ISubmitDialog target, JRegistroIngreso ingreso) {
		this(target);
		// this.ingreso = ingreso;
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public RegistroIngresoSecadoDialog(ISubmitDialog target) {
		setResizable(false);
		this.loteArrozSevice = new Service<JLoteArroz>(FParse.getLoteArrozParse(), new Route(Route.ROUTE_ARROZ_LOTE));
		this.emepleadoService = new Service<JEmpleado>(FParse.getEmpleadoParse(), new Route(Route.ROUTE_EMPLEADO));
		this.areaSecadoService = new Service<JAreaSecado>(FParse.getAreaSecadoParse(),
				new Route(Route.ROUTE_AREA_SECADO));

		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.mode = MODE_NEW;
		this.loteSecado = new JLoteSecado();
		this.setTitle("Registro");
		this.targetListener = target;
		setBounds(100, 100, 363, 340);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(123, 262, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);

		txtNumeroSacos = new JTextField();
		txtNumeroSacos.setBounds(11, 129, 157, 20);
		contentPanel.add(txtNumeroSacos);
		txtNumeroSacos.setColumns(10);

		JLabel lblNewLabel = new JLabel("Numero sacos");
		lblNewLabel.setBounds(10, 106, 89, 14);
		contentPanel.add(lblNewLabel);

		txtHumedad = new JTextField();
		txtHumedad.setBounds(186, 129, 147, 20);
		contentPanel.add(txtHumedad);
		txtHumedad.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Nivel de Humedad");
		lblNewLabel_4.setBounds(192, 106, 129, 14);
		contentPanel.add(lblNewLabel_4);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 54, 323, 2);
		contentPanel.add(separator_1);

		JLabel lblNewLabel_9 = new JLabel("Datos del arroz");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 36, 132, 14);
		contentPanel.add(lblNewLabel_9);

		cmbLoteArroz = new JComboBox<String>();
		cmbLoteArroz.setBounds(12, 73, 321, 22);
		contentPanel.add(cmbLoteArroz);

		JLabel lblNewLabel_10 = new JLabel("Lote Arroz");
		lblNewLabel_10.setBounds(11, 56, 107, 14);
		contentPanel.add(lblNewLabel_10);

		cmbAreaSecado = new JComboBox<String>();
		cmbAreaSecado.setBounds(11, 212, 157, 22);
		contentPanel.add(cmbAreaSecado);

		JLabel lblNewLabel_11 = new JLabel("Area Secado");
		lblNewLabel_11.setBounds(10, 187, 106, 14);
		contentPanel.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Registro de ingreso, área de secado");
		lblNewLabel_12.setForeground(SystemColor.textHighlight);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBounds(35, 11, 262, 14);
		contentPanel.add(lblNewLabel_12);

		cmbUbicacion = new JComboBox<String>();
		cmbUbicacion.setBounds(191, 212, 141, 22);
		contentPanel.add(cmbUbicacion);

		JLabel lblNewLabel_11_1 = new JLabel("Ubicacion");
		lblNewLabel_11_1.setBounds(191, 187, 106, 14);
		contentPanel.add(lblNewLabel_11_1);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 174, 323, 2);
		contentPanel.add(separator_1_1);

		JLabel lblNewLabel_9_1 = new JLabel("Datos area de secado");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9_1.setBounds(10, 159, 132, 14);
		contentPanel.add(lblNewLabel_9_1);
		this.loadEntities();
		this.prepareAllEvents();
	}

	private void empleadoGet() {
		String[] data = SessionStatus.getInst().getOwner().split("[.]+");
		try {
			this.empleado = this.emepleadoService.findByField(data[1]);
		} catch (ResponseException e) {
			this.showBoxMessage(Error.ERROR_BASIC);
			e.printStackTrace();
		}
	}

	public JLoteArroz filtarListaLotes(Long id) {
		for (JLoteArroz lote : this.lotesDeArroz) {
			if (lote.getId() == id) {
				return lote;
			}

		}
		return null;
	}

	public JAreaSecado filtarListaAreasSecado(Long id) {
		for (JAreaSecado area : this.areasdeSecado) {
			if (area.getId() == id) {
				return area;
			}

		}
		return null;
	}

	public JUbicacion filtarListaUbicaciones(Long id) {
		for (JUbicacion ubicacion : this.ubicaciones) {
			if (ubicacion.getId() == id) {
				return ubicacion;
			}

		}
		return null;
	}

	public void loadEntities() {
		this.empleadoGet();
		try {
			this.lotesDeArroz = this.loteArrozSevice.findAll();
			this.areasdeSecado = this.areaSecadoService.findAll();
		} catch (ResponseException e) {
			e.printStackTrace();
		}
		loadComboBoxs();
	}

	private void loadComboBoxs() {
		List<String> lotes = new ArrayList<String>();
		lotes.add("");
		for (JLoteArroz arroz : this.lotesDeArroz) {
			if(arroz.getNumeroSacos()>0) {				
				lotes.add("Lote: " + arroz.getId() + ", " + arroz.getProductor().completeName()+" ("+arroz.getNumeroSacos()+")");
			}
		}
		this.cmbLoteArroz.setModel(new DefaultComboBoxModel<String>(lotes.toArray(new String[0])));
		List<String> areasSecado = new ArrayList<String>();
		areasSecado.add("");
		for (JAreaSecado area : this.areasdeSecado) {
			areasSecado.add(area.getId() + " : " + area.getUbicacion());
		}
		this.cmbAreaSecado.setModel(new DefaultComboBoxModel<String>(areasSecado.toArray(new String[0])));
	}

	private void loadComoBoxUbicaciones() {
		List<String> ubicaciones = new ArrayList<String>();
		ubicaciones.add("");
		for (JUbicacion ubicacion : this.ubicaciones) {
			ubicaciones.add(ubicacion.getCodigo());
		}
		this.cmbUbicacion.setModel(new DefaultComboBoxModel<String>(ubicaciones.toArray(new String[0])));
	}

	private void prepareAllEvents() {
		ItemListener eventLoteArroz = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) e.getItem();
					if (item != "") {
						String loteId = item.split(",")[0].split(":")[1].trim();
						loteArroz = filtarListaLotes(Long.parseLong(loteId));
						txtNumeroSacos.setText(Integer.toString(loteArroz.getNumeroSacos()));
					} else {
						txtNumeroSacos.setText("");
					}
				}
			}
		};

		ItemListener eventAreaSecado = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) e.getItem();
					if (item != "") {
						String areaId = item.split(":")[0].trim();
						ubicaciones = filtarListaAreasSecado(Long.parseLong(areaId)).getUbicaciones();
						loadComoBoxUbicaciones();
					}
				}

			}
		};
		ItemListener eventUbicacion = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					int inndex= cmbUbicacion.getSelectedIndex();
					if(inndex>0) {
						ubicacion = ubicaciones.get(inndex-1);
					}else {
						ubicacion=null;
					}
				}

			}
		};

		this.cmbLoteArroz.addItemListener(eventLoteArroz);
		this.cmbAreaSecado.addItemListener(eventAreaSecado);
		this.cmbUbicacion.addItemListener(eventUbicacion);
	}

	public void prepareForm() {
		if (mode.equals(MODE_EDIT)) {

		}
		// this.loadProductorToFilds();
	}

	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String numeroSacos = this.txtNumeroSacos.getText();
		String humedad = this.txtHumedad.getText();		
		
		if(check.in(numeroSacos).isNumber().notOk()) {
			this.showBoxMessage(check.getMessage()+", numero de sacos");
			return false;
		}
		if(check.in(humedad).isNumber().notOk()) {
			this.showBoxMessage(check.getMessage()+", humedad");
			return false;
		}
		if(this.ubicacion==null) {
			this.showBoxMessage("Seleccione una ubicacion");
		}		
		if(this.loteArroz==null) {
			this.showBoxMessage("El camopo lote de arroz, no puede quedar vacio.");
			return false;
		}
		if(this.empleado==null) {
			this.showBoxMessage("Error al procesar los datos");
			return false;
		}
		JIngresoSecado ingreso = new JIngresoSecado();
		ingreso.setEmpleado(this.empleado);
		ingreso.setNumeroSacos(Integer.parseInt(numeroSacos));
		ingreso.setHumedad(Double.parseDouble(humedad));
		this.loteSecado.setIngreso(ingreso);
		this.loteSecado.setTotalSacos(Integer.parseInt(numeroSacos));
		this.loteSecado.setUbicacion(this.ubicacion);
		this.loteSecado.setLotearroz(this.loteArroz);
		return true;
	}

//	private void loadEntity() {
//		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.loteSecado, this.mode, DIALOG_KEY);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
	}

}
