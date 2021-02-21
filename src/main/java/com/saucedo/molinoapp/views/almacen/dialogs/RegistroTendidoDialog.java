package com.saucedo.molinoapp.views.almacen.dialogs;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.saucedo.molino_json_models.almacen.JDetalleTendido;
import com.saucedo.molinoapp.utils.KCheck;
import com.saucedo.molinoapp.views.ISubmitDialog;
import com.saucedo.molinoapp.views.error_message.General;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.SystemColor;

public class RegistroTendidoDialog extends JDialog implements ActionListener {

	public static final String DIALOG_KEY = "com.saucedo.molinoapp.views.almacen.RegistroTendidoDialog";
	private static final long serialVersionUID = 143242423L;
	public static final String TITLE_MODE_EDIT = "Editar datos ingreso area secado";
	public static final String TITLE_MODE_NEW = "Ingreso al area de secado";

	public static final String MODE_EDIT = "RegistroTendido.edit";
	public static final String MODE_NEW = "RegistroTendido.new";

	private String mode;
	ISubmitDialog targetListener;

	private final JPanel contentPanel = new JPanel();
	private JButton btnSubmit;
	/// Asosiated entities
	private JDetalleTendido tendido;
	private Long loteSecadoId;
	
	private JTextField txtUbicacion;
	private JLabel lbLoteArroz;
	private JLabel lblFecha;
	private JLabel lblHora;
	
	public RegistroTendidoDialog(ISubmitDialog target,  JDetalleTendido tendido) {
		this(target);
		this.tendido=tendido;
		this.mode = MODE_EDIT;
		this.setTitle(TITLE_MODE_EDIT);
	}

	/**
	 * @wbp.parser.constructor
	 */
	public RegistroTendidoDialog(ISubmitDialog target) {	
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.mode = MODE_NEW;
		this.tendido = new JDetalleTendido();
		this.setTitle("Registro");
		this.targetListener = target;
		setBounds(100, 100, 363, 285);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnSubmit = new JButton("Guardar");
		btnSubmit.setBounds(133, 212, 89, 23);
		btnSubmit.addActionListener(this);
		contentPanel.add(btnSubmit);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 54, 323, 2);
		contentPanel.add(separator_1);

		JLabel lblNewLabel_9 = new JLabel("Datos del arroz");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(10, 36, 132, 14);
		contentPanel.add(lblNewLabel_9);

		lbLoteArroz = new JLabel("");
		lbLoteArroz.setBounds(11, 56, 323, 14);
		contentPanel.add(lbLoteArroz);

		lblFecha = new JLabel("Fecha:  La del sistema al guarda");
		lblFecha.setBounds(10, 74, 261, 14);
		contentPanel.add(lblFecha);

		JLabel lblNewLabel_12 = new JLabel("Tendido de arroz");
		lblNewLabel_12.setForeground(SystemColor.textHighlight);
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_12.setBounds(107, 11, 106, 14);
		contentPanel.add(lblNewLabel_12);

		lblHora = new JLabel("Hora:  La del sistema al guardar el registro");
		lblHora.setBounds(10, 99, 323, 14);
		contentPanel.add(lblHora);

		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 133, 323, 2);
		contentPanel.add(separator_1_1);

		JLabel lblNewLabel_9_1 = new JLabel("Datos Tendido");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9_1.setBounds(10, 118, 132, 14);
		contentPanel.add(lblNewLabel_9_1);
		
		txtUbicacion = new JTextField();
		txtUbicacion.setBounds(10, 162, 323, 20);
		contentPanel.add(txtUbicacion);
		txtUbicacion.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Descripcion ubicacion");
		lblNewLabel.setBounds(10, 146, 176, 14);
		contentPanel.add(lblNewLabel);
		
		this.lblFecha.setText("Fecha: " + LocalDate.now().toString());
	
	}

	public void setDetalleLote(Long loteSecadoId, String detalle) {
		this.loteSecadoId=loteSecadoId;
		this.lbLoteArroz.setText(detalle);
	}
	public void prepareForm() {
		if (mode.equals(MODE_EDIT)) {
			if(this.tendido.getUbicacion()!=null) {				
				this.txtUbicacion.setText(this.tendido.getUbicacion());
			}
		}
	}

	private boolean validFildsAndPrepareObject() {
		KCheck check = new KCheck();
		String ubicacion  = this.txtUbicacion.getText();
		if(check.in(ubicacion).noInvalidSpaces().onlyBasicsCaracteres().notOk()) {
			this.showBoxMessage(check.getMessage()+", campo detelle ubicacion");
			return false;
		}
		if(this.loteSecadoId==null) {
			this.showBoxMessage("Error al procesar los datos");
			return false;
		}		
		this.tendido.setId(this.loteSecadoId);
		this.tendido.setUbicacion(ubicacion);
		return true;
	}

//	private void loadEntity() {
//		
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.targetListener != null) {
			if (this.validFildsAndPrepareObject())
				this.targetListener.notifyDialogAction(this.tendido, this.mode, DIALOG_KEY);
		}
	}

	private void showBoxMessage(String Message) {
		JOptionPane.showMessageDialog(this, Message, General.TITLE_DIALOG_ERROR_FILDS, JOptionPane.WARNING_MESSAGE);
	}
}
