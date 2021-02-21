package com.saucedo.molinoapp.views.almacen.detalles;

import javax.swing.JDialog;

import com.saucedo.molino_json_models.almacen.JCamionProductor;
import com.saucedo.molino_json_models.almacen.JLoteArroz;
import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molino_json_models.almacen.JRegistroIngreso;
import com.saucedo.molino_json_models.personal.JEmpleado;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;

public class DetalleRegistroIngresoLote extends JDialog {

	private static final long serialVersionUID = 439893488934L;
	private JLabel lblDireccionProductor;
	private JLabel lblDniProductor;
	private JLabel lblEmailProveedor;
	private JLabel lblTelefonoProductor;
	private JLabel lblNombreProductor;
	private JLabel lblTotalSacosAlmacen;
	private JLabel lblNumeroSacosIngreso;
	private JLabel lblKilosxSaco;
	private JLabel lblTotalKilos;
	private JLabel lblFechaIngreso;
	private JLabel lblHora;
	private JLabel lblPlacaTransporte;
	private JLabel lblChofer;
	private JLabel lblLugar;
	private JLabel lblTipoArroz;
	private JLabel lblNombreEmpleado;
	private JLabel lblDniEmpleado;
	private JLabel lblTelefonEmpleado;
	private JLabel lblTitulo;

	public DetalleRegistroIngresoLote(JLoteArroz lotearroz) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 710, 350);
		getContentPane().setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Datos del productor");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblNewLabel.setBounds(20, 49, 163, 14);
			getContentPane().add(lblNewLabel);
		}
		{
			lblTitulo = new JLabel("Detalle del lote: ");
			lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblTitulo.setBounds(112, 11, 386, 27);
			getContentPane().add(lblTitulo);
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 63, 646, 2);
		getContentPane().add(separator);

		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(30, 74, 58, 14);
		getContentPane().add(lblNewLabel_1);

		lblNombreProductor = new JLabel("none");
		lblNombreProductor.setBounds(89, 74, 222, 14);
		getContentPane().add(lblNombreProductor);

		lblDniProductor = new JLabel("none");
		lblDniProductor.setBounds(360, 74, 132, 14);
		getContentPane().add(lblDniProductor);

		JLabel lblNewLabel_2 = new JLabel("DNI: ");
		lblNewLabel_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(321, 76, 46, 14);
		getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_1_1 = new JLabel("Dirección: ");
		lblNewLabel_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(30, 93, 58, 14);
		getContentPane().add(lblNewLabel_1_1);

		lblDireccionProductor = new JLabel("none");
		lblDireccionProductor.setBounds(89, 93, 222, 14);
		getContentPane().add(lblDireccionProductor);

		JLabel lblNewLabel_2_1 = new JLabel("Telefono: ");
		lblNewLabel_2_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(504, 74, 65, 14);
		getContentPane().add(lblNewLabel_2_1);

		lblTelefonoProductor = new JLabel("none");
		lblTelefonoProductor.setBounds(570, 74, 114, 14);
		getContentPane().add(lblTelefonoProductor);

		JLabel lblNewLabel_1_1_1 = new JLabel("Email: ");
		lblNewLabel_1_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(321, 93, 46, 14);
		getContentPane().add(lblNewLabel_1_1_1);

		lblEmailProveedor = new JLabel("none");
		lblEmailProveedor.setBounds(360, 93, 253, 14);
		getContentPane().add(lblEmailProveedor);
		
		JLabel lblDatosLote = new JLabel("Datos Lote");
		lblDatosLote.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosLote.setBounds(20, 122, 163, 14);
		getContentPane().add(lblDatosLote);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 136, 646, 2);
		getContentPane().add(separator_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("N. Sacos: ");
		lblNewLabel_1_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(30, 147, 58, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Kilos x Saco:");
		lblNewLabel_1_2_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1.setBounds(157, 147, 77, 14);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Total Kilos:");
		lblNewLabel_1_2_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_1.setBounds(289, 147, 65, 14);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Fecha: ");
		lblNewLabel_1_2_1_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_1_1.setBounds(433, 147, 46, 14);
		getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("Hora: ");
		lblNewLabel_1_2_1_1_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_1_1_1.setBounds(547, 147, 39, 14);
		getContentPane().add(lblNewLabel_1_2_1_1_1_1);
		
		lblNumeroSacosIngreso = new JLabel("none");
		lblNumeroSacosIngreso.setBounds(82, 147, 65, 14);
		getContentPane().add(lblNumeroSacosIngreso);
		
		lblKilosxSaco = new JLabel("none");
		lblKilosxSaco.setBounds(233, 147, 58, 14);
		getContentPane().add(lblKilosxSaco);
		
		lblTotalKilos = new JLabel("none");
		lblTotalKilos.setBounds(360, 147, 63, 14);
		getContentPane().add(lblTotalKilos);
		
		lblFechaIngreso = new JLabel("none");
		lblFechaIngreso.setBounds(480, 147, 67, 14);
		getContentPane().add(lblFechaIngreso);
		
		lblHora = new JLabel("none");
		lblHora.setBounds(581, 147, 85, 14);
		getContentPane().add(lblHora);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(30, 186, 230, 2);
		getContentPane().add(separator_1_1);
		
		JLabel lblDatosTransporte = new JLabel("Datos transporte");
		lblDatosTransporte.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosTransporte.setBounds(30, 172, 163, 14);
		getContentPane().add(lblDatosTransporte);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Placa: ");
		lblNewLabel_1_2_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2.setBounds(30, 199, 39, 14);
		getContentPane().add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Chofer:");
		lblNewLabel_1_2_2_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1.setBounds(30, 220, 58, 14);
		getContentPane().add(lblNewLabel_1_2_2_1);
		
		lblPlacaTransporte = new JLabel("none");
		lblPlacaTransporte.setBounds(82, 199, 121, 14);
		getContentPane().add(lblPlacaTransporte);
		
		lblChofer = new JLabel("none");
		lblChofer.setBounds(82, 220, 178, 14);
		getContentPane().add(lblChofer);
		
		JLabel lblProcedenciaDelArroz = new JLabel("Procedencia y Tipo de arroz");
		lblProcedenciaDelArroz.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProcedenciaDelArroz.setBounds(289, 172, 186, 14);
		getContentPane().add(lblProcedenciaDelArroz);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(289, 186, 178, 2);
		getContentPane().add(separator_1_1_1);
		
		JLabel lblNewLabel_1_2_2_2 = new JLabel("Lugar: ");
		lblNewLabel_1_2_2_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_2.setBounds(289, 199, 46, 14);
		getContentPane().add(lblNewLabel_1_2_2_2);
		
		JLabel lblNewLabel_1_2_2_2_1 = new JLabel("Tipo: ");
		lblNewLabel_1_2_2_2_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_2_1.setBounds(289, 220, 46, 14);
		getContentPane().add(lblNewLabel_1_2_2_2_1);
		
		lblLugar = new JLabel("none");
		lblLugar.setBounds(334, 199, 145, 14);
		getContentPane().add(lblLugar);
		
		lblTipoArroz = new JLabel("none");
		lblTipoArroz.setBounds(334, 220, 46, 14);
		getContentPane().add(lblTipoArroz);
		
		lblTotalSacosAlmacen = new JLabel("10000");
		lblTotalSacosAlmacen.setForeground(new Color(0, 255, 127));
		lblTotalSacosAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblTotalSacosAlmacen.setBounds(480, 181, 191, 61);
		getContentPane().add(lblTotalSacosAlmacen);
		
		JLabel lblNewLabel_4 = new JLabel("En alamcen:");
		lblNewLabel_4.setBounds(485, 174, 86, 14);
		getContentPane().add(lblNewLabel_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 267, 646, 2);
		getContentPane().add(separator_2);
		
		JLabel lblDatosEmpleado = new JLabel("Datos empleado");
		lblDatosEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosEmpleado.setBounds(20, 253, 163, 14);
		getContentPane().add(lblDatosEmpleado);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nombre: ");
		lblNewLabel_1_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(30, 278, 58, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2_2 = new JLabel("DNI: ");
		lblNewLabel_2_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2.setBounds(321, 278, 39, 14);
		getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Telefono: ");
		lblNewLabel_2_1_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1.setBounds(504, 278, 65, 14);
		getContentPane().add(lblNewLabel_2_1_1);
		
		lblNombreEmpleado = new JLabel("none");
		lblNombreEmpleado.setBounds(89, 278, 202, 14);
		getContentPane().add(lblNombreEmpleado);
		
		lblDniEmpleado = new JLabel("none");
		lblDniEmpleado.setBounds(360, 278, 114, 14);
		getContentPane().add(lblDniEmpleado);
		
		lblTelefonEmpleado = new JLabel("none");
		lblTelefonEmpleado.setBounds(559, 278, 107, 14);
		getContentPane().add(lblTelefonEmpleado);

		this.prepareInformation(lotearroz);
	}

	public void prepareInformation(JLoteArroz lotearroz) {
		loadProducto(lotearroz.getProductor());
		this.loadLote(lotearroz);
	}

	private void loadProducto(JProductor productor) {
		if (productor != null) {
			this.lblNombreProductor.setText(productor.completeName());
			this.lblDniProductor.setText(productor.getDni());
			this.lblDireccionProductor.setText(productor.getDireccion());
			this.lblEmailProveedor.setText(productor.getEmail());
			this.lblTelefonoProductor.setText(productor.getTelefon());
		}
	}
	private void loadLote(JLoteArroz l) {
		if(l!=null) {
			this.lblTitulo.setText("Detalle del lote numero: " + l.getId());
			this.lblTotalSacosAlmacen.setText(Integer.toString(l.getNumeroSacos()));
			if(l.getProcedencia()!=null) {
				this.lblLugar.setText(l.getProcedencia().getLugar());				
			}
			if(l.getTipoArroz()!=null){
				this.lblTipoArroz.setText(l.getTipoArroz().getNombre());
			}
		}
		if(l.getIngreso()!=null) {
			JRegistroIngreso in = l.getIngreso();
			this.lblNumeroSacosIngreso.setText(Integer.toString(in.getNumeroSacos()));
			this.lblKilosxSaco.setText(Double.toString(in.getKilosPorSaco()));
			this.lblTotalKilos.setText(Double.toString(in.getTotalKilos()));
			this.lblFechaIngreso.setText(in.getFecha().toString());
			this.lblHora.setText(in.getHora().toString());			
			if(in.getTransporte()!=null) {
				JCamionProductor cp = in.getTransporte();
				this.lblPlacaTransporte.setText(cp.getPlaca());
				this.lblChofer.setText(cp.getChofer());
			}
			if(in.getEmpleado()!=null) {
				JEmpleado e = in.getEmpleado();
				this.lblNombreEmpleado.setText(e.completeName());
				this.lblDniEmpleado.setText(e.getDni());
				this.lblTelefonEmpleado.setText(e.getTelefon());
			}
		}
		
	}
}
