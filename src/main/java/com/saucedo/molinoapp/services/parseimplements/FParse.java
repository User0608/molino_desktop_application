package com.saucedo.molinoapp.services.parseimplements;

import com.saucedo.molinoapp.services.parseimplements.alamcen.AreaSecadoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.DetalleRecojoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.DetalleTendidoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.IngresoSecadoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.LoteArrozParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.LoteSecadoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.ProcedenciaParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.ProductorParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.RegistroIngresoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.TipoArrozParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.TransporteParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.UbicacionParse;

@SuppressWarnings("rawtypes")
public class FParse {
	public IParse parse;
	public FParse() {
		
	}		
	public static UsuarioParse getUsurioParse() {
		return new UsuarioParse();	
	}
	public static EmpleadoParse getEmpleadoParse() {
		return new EmpleadoParse();	
	}
	//Parsing to almacen entities
	public static ProductorParse getProductorParse() {
		return new ProductorParse();	
	}
	public static TransporteParse getTransporteParse() {
		return new TransporteParse();	
	}
	public static TipoArrozParse getTipoArrozParse() {
		return new TipoArrozParse();	
	}
	public static ProcedenciaParse getProcedenciaParse() {
		return new ProcedenciaParse();
	}	
	public static RegistroIngresoParse getRegistroIngreso() {
		return new RegistroIngresoParse();	
	}
	public static LoteArrozParse getLoteArrozParse() {
		return new LoteArrozParse();	
	}
	///secado
	public static UbicacionParse getUbicacionParsee() {
		return new UbicacionParse();	
	}
	public static AreaSecadoParse getAreaSecadoParse() {
		return new AreaSecadoParse();	
	}
	public static IngresoSecadoParse getIngresoSecadoParse() {
		return new IngresoSecadoParse();	
	}
	public static LoteSecadoParse getLoteSecadoParse() {
		return new LoteSecadoParse();	
	}
	public static DetalleRecojoParse getDetalleRecojoParse() {
		return new DetalleRecojoParse();
	}
	public static DetalleTendidoParse getDetalleTendidoParse() {
		return new DetalleTendidoParse();
	}
}
