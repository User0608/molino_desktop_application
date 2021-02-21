package com.saucedo.molinoapp.views.almacen.detalles;

import javax.swing.JDialog;

import com.saucedo.molino_json_models.almacen.JDetalleRecojo;
import com.saucedo.molino_json_models.almacen.JIngresoSecado;
import com.saucedo.molino_json_models.almacen.JLoteSecado;
import com.saucedo.molino_json_models.almacen.JProductor;
import com.saucedo.molino_json_models.personal.JEmpleado;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JPanel;

public class DetalleRegistroSecadoLote extends JDialog {

	private static final long serialVersionUID = 439893488934L;
	private JLabel lblDireccionProductor;
	private JLabel lblDniProductor;
	private JLabel lblEmailProveedor;
	private JLabel lblTelefonoProductor;
	private JLabel lblNombreProductor;
	private JLabel lblNombreEmpleado;
	private JLabel lblDniEmpleado;
	private JLabel lblTelefonEmpleado;
	private JLabel lblTitulo;
	private JPanel panelDatosTendido;
	private JLabel lblNumeroSacos;
	private JLabel lblUbicaicion;
	private JLabel lblHumedad;
	private JLabel lblFechaIngreso;
	private JLabel lblHoreIngreso;
	private JLabel lblTotlaEnalmacen;
	private JLabel lblUbicacionTendio;
	private JLabel lblFechaTendido;
	private JLabel lblHoraTendido;
	private JLabel lblFechaRecojo;
	private JLabel lblHoraRecojo;
	private JLabel lblTotalSacosRecogidos;
	private JPanel panelDatosRecojo;
	private JLabel lblHumedadRecojo;

	public DetalleRegistroSecadoLote(JLoteSecado lotesecado) {
		setModal(true);
		setBounds(100, 100, 710, 430);
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
		
		JLabel lblDatosLote = new JLabel("Datos ingreso area de secado");
		lblDatosLote.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosLote.setBounds(20, 122, 307, 14);
		getContentPane().add(lblDatosLote);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 136, 646, 2);
		getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 355, 646, 2);
		getContentPane().add(separator_2);
		
		JLabel lblDatosEmpleado = new JLabel("Datos empleado");
		lblDatosEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosEmpleado.setBounds(20, 341, 163, 14);
		getContentPane().add(lblDatosEmpleado);
		
		JLabel lblNewLabel_1_3 = new JLabel("Nombre: ");
		lblNewLabel_1_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_3.setBounds(30, 366, 58, 14);
		getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2_2 = new JLabel("DNI: ");
		lblNewLabel_2_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2.setBounds(321, 366, 39, 14);
		getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Telefono: ");
		lblNewLabel_2_1_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1.setBounds(504, 366, 65, 14);
		getContentPane().add(lblNewLabel_2_1_1);
		
		lblNombreEmpleado = new JLabel("none");
		lblNombreEmpleado.setBounds(89, 366, 202, 14);
		getContentPane().add(lblNombreEmpleado);
		
		lblDniEmpleado = new JLabel("none");
		lblDniEmpleado.setBounds(360, 366, 114, 14);
		getContentPane().add(lblDniEmpleado);
		
		lblTelefonEmpleado = new JLabel("none");
		lblTelefonEmpleado.setBounds(559, 366, 107, 14);
		getContentPane().add(lblTelefonEmpleado);
		
		JLabel lblNewLabel_1_2 = new JLabel("N. Sacos: ");
		lblNewLabel_1_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(30, 147, 58, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Humedad: ");
		lblNewLabel_1_2_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1.setBounds(169, 147, 70, 14);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Fecha Ingreso: ");
		lblNewLabel_1_2_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_1.setBounds(296, 147, 99, 14);
		getContentPane().add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Hora Ingreso:");
		lblNewLabel_1_2_1_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_1_1.setBounds(296, 169, 82, 14);
		getContentPane().add(lblNewLabel_1_2_1_1_1);
		
		lblNumeroSacos = new JLabel("none");
		lblNumeroSacos.setBounds(89, 147, 70, 14);
		getContentPane().add(lblNumeroSacos);
		
		lblHumedad = new JLabel("none");
		lblHumedad.setBounds(229, 147, 62, 14);
		getContentPane().add(lblHumedad);
		
		lblFechaIngreso = new JLabel("none");
		lblFechaIngreso.setBounds(381, 147, 111, 14);
		getContentPane().add(lblFechaIngreso);
		
		lblHoreIngreso = new JLabel("none");
		lblHoreIngreso.setBounds(379, 169, 113, 14);
		getContentPane().add(lblHoreIngreso);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Ubicacion: ");
		lblNewLabel_1_2_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2.setBounds(30, 169, 76, 14);
		getContentPane().add(lblNewLabel_1_2_2);
		
		lblTotlaEnalmacen = new JLabel("100000");
		lblTotlaEnalmacen.setForeground(new Color(0, 255, 127));
		lblTotlaEnalmacen.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTotlaEnalmacen.setBounds(504, 155, 125, 27);
		getContentPane().add(lblTotlaEnalmacen);
		
		JLabel lblNewLabel_4 = new JLabel("Numero actual en almacen:");
		lblNewLabel_4.setBounds(506, 136, 163, 14);
		getContentPane().add(lblNewLabel_4);
		
		panelDatosTendido = new JPanel();
		panelDatosTendido.setBounds(20, 190, 646, 48);
		getContentPane().add(panelDatosTendido);
		panelDatosTendido.setLayout(null);
		
		JLabel lblDatosDeTendo = new JLabel("Datos de tendido");
		lblDatosDeTendo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosDeTendo.setBounds(0, 0, 163, 14);
		panelDatosTendido.add(lblDatosDeTendo);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 14, 646, 2);
		panelDatosTendido.add(separator_3);
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("Ubicacion Tendido: ");
		lblNewLabel_1_2_2_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1.setBounds(10, 25, 126, 14);
		panelDatosTendido.add(lblNewLabel_1_2_2_1);
		
		JLabel lblNewLabel_1_2_2_1_1 = new JLabel("Fecha: ");
		lblNewLabel_1_2_2_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1_1.setBounds(322, 25, 56, 14);
		panelDatosTendido.add(lblNewLabel_1_2_2_1_1);
		
		JLabel lblNewLabel_1_2_2_1_1_1 = new JLabel("Hora: ");
		lblNewLabel_1_2_2_1_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1_1_1.setBounds(465, 25, 56, 14);
		panelDatosTendido.add(lblNewLabel_1_2_2_1_1_1);
		
		lblFechaTendido = new JLabel("none");
		lblFechaTendido.setBounds(362, 25, 95, 14);
		panelDatosTendido.add(lblFechaTendido);
		
		lblHoraTendido = new JLabel("none");
		lblHoraTendido.setBounds(503, 25, 133, 14);
		panelDatosTendido.add(lblHoraTendido);
		
		lblUbicacionTendio = new JLabel("none");
		lblUbicacionTendio.setBounds(120, 25, 192, 14);
		panelDatosTendido.add(lblUbicacionTendio);
		
		lblUbicaicion = new JLabel("none");
		lblUbicaicion.setBounds(89, 169, 197, 14);
		getContentPane().add(lblUbicaicion);
		
		panelDatosRecojo = new JPanel();
		panelDatosRecojo.setBounds(20, 249, 646, 53);
		getContentPane().add(panelDatosRecojo);
		panelDatosRecojo.setLayout(null);
		
		JLabel lblDatosDeTendo_1 = new JLabel("Datos de recojo de arroz tendido");
		lblDatosDeTendo_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDatosDeTendo_1.setBounds(0, 0, 298, 14);
		panelDatosRecojo.add(lblDatosDeTendo_1);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBounds(0, 14, 646, 2);
		panelDatosRecojo.add(separator_3_1);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("N. Sacos: ");
		lblNewLabel_1_2_3.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_3.setBounds(471, 22, 58, 14);
		panelDatosRecojo.add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_2_1_1_2 = new JLabel("Fecha: ");
		lblNewLabel_1_2_2_1_1_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1_1_2.setBounds(10, 25, 56, 14);
		panelDatosRecojo.add(lblNewLabel_1_2_2_1_1_2);
		
		JLabel lblNewLabel_1_2_2_1_1_1_1 = new JLabel("Hora: ");
		lblNewLabel_1_2_2_1_1_1_1.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1_1_1_1.setBounds(153, 25, 56, 14);
		panelDatosRecojo.add(lblNewLabel_1_2_2_1_1_1_1);
		
		lblTotalSacosRecogidos = new JLabel("100000");
		lblTotalSacosRecogidos.setForeground(new Color(139, 0, 0));
		lblTotalSacosRecogidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalSacosRecogidos.setBounds(526, 12, 141, 27);
		panelDatosRecojo.add(lblTotalSacosRecogidos);
		
		lblFechaRecojo = new JLabel("none");
		lblFechaRecojo.setBounds(48, 25, 95, 14);
		panelDatosRecojo.add(lblFechaRecojo);
		
		lblHoraRecojo = new JLabel("none");
		lblHoraRecojo.setBounds(188, 25, 125, 14);
		panelDatosRecojo.add(lblHoraRecojo);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Humedad: ");
		lblNewLabel_1_2_1_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_2.setBounds(281, 25, 70, 14);
		panelDatosRecojo.add(lblNewLabel_1_2_1_2);
		
		lblHumedadRecojo = new JLabel("none");
		lblHumedadRecojo.setBounds(346, 25, 115, 14);
		panelDatosRecojo.add(lblHumedadRecojo);

		this.prepareInformation(lotesecado);
	}

	public void prepareInformation(JLoteSecado lotesecado) {
		if(lotesecado!=null) {
			if(lotesecado.getLotearroz()!=null) {
				loadProducto(lotesecado.getLotearroz().getProductor());				
			}
			
		}
		this.loadLote(lotesecado);
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
	private void loadLote(JLoteSecado ls) {
		if(ls!=null) {
			if(ls.getIngreso()!=null) {
				JIngresoSecado ing = ls.getIngreso();
				this.lblNumeroSacos.setText(Integer.toString(ing.getNumeroSacos()));
				this.lblHumedad.setText(Double.toString(ing.getHumedad()));
				this.lblFechaIngreso.setText(ing.getFecha().toString());
				this.lblHoreIngreso.setText(ing.getHora().toString());
				this.lblTotlaEnalmacen.setText(Integer.toString(ls.getTotalSacos()));
				if(ing.getEmpleado()!=null) {
					JEmpleado e = ing.getEmpleado();
					this.lblNombreEmpleado.setText(e.completeName());
					this.lblDniEmpleado.setText(e.getDni());
					this.lblTelefonEmpleado.setText(e.getTelefon());
				}
			}
			if(ls.getUbicacion()!=null) {
				this.lblUbicaicion.setText(ls.getUbicacion().getCodigo());
			}
		}else {
			return;
		}
		
		if(ls.getTendido()!=null) {
			this.panelDatosTendido.setVisible(true);
			this.lblUbicacionTendio.setText(ls.getTendido().getUbicacion());
			this.lblFechaTendido.setText(ls.getTendido().getFecha().toString());
			this.lblHoraTendido.setText(ls.getTendido().getHora().toString());
			if(ls.getTendido().getDetalleRecojo()!=null) {
				this.panelDatosRecojo.setVisible(true);
				JDetalleRecojo dr = ls.getTendido().getDetalleRecojo();
				this.lblFechaRecojo.setText(dr.getFecha().toString());
				this.lblHoraRecojo.setText(dr.getHora().toString());
				this.lblTotalSacosRecogidos.setText(Integer.toString(dr.getNumeroSacos()));
				this.lblHumedadRecojo.setText(Double.toString(dr.getHumedad()));
			}else {
				this.panelDatosRecojo.setVisible(false);
			}
			
		}else {
			this.panelDatosTendido.setVisible(false);
			this.panelDatosRecojo.setVisible(false);
		}
		
	}
}
