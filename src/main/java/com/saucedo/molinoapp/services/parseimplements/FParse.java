package com.saucedo.molinoapp.services.parseimplements;
@SuppressWarnings("rawtypes")
public class FParse {
	public IParse parse;
//	public static final String PRODUCTOR="prarse.productor.model"; 
//	public static final String USUARIO="prarse.usuario.model"; 
//	public static final String EMPLEADO="prarse.empleado.model"; 
	public FParse() {
		
	}	

	public static ProductorParse getProductorParse() {
		return new ProductorParse();	
	}
	public static UsuarioParse getUsurioParse() {
		return new UsuarioParse();	
	}
	public static EmpleadoParse getEmpleadoParse() {
		return new EmpleadoParse();	
	}
	
}
