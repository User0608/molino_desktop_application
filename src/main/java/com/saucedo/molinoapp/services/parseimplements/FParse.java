package com.saucedo.molinoapp.services.parseimplements;

import com.saucedo.molinoapp.services.parseimplements.alamcen.LoteArrozParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.ProcedenciaParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.ProductorParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.RegistroIngresoParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.TipoArrozParse;
import com.saucedo.molinoapp.services.parseimplements.alamcen.TransporteParse;

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
	public static RegistroIngresoParse getRegistroIngresoParse() {
		return new RegistroIngresoParse();	
	}
	public static LoteArrozParse getLoteArrozParseParse() {
		return new LoteArrozParse();	
	}
}
