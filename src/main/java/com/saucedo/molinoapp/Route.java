package com.saucedo.molinoapp;

public class Route {
	public static final String ROUTE_USUARIO="/usuario";
	public static final String ROUTE_PRODUCTOR="/productor";
	public static final String ROUTE_EMPLEADO="/empleado";

	public static final String ROUTE_ARROZ_PROCEDENCIA="/arroz/procedencia";
	public static final String ROUTE_ARROZ_TIPO="/arroz/tipo";
	public static final String ROUTE_ARROZ_LOTE="/arroz/ingreso_lote";
	public static final String ROUTE_ARROZ_INGRESO_SECADO="/arroz/ingreso_secado";
	public static final String ROUTE_ARROZ_INGRESO_SECADO_TENDIDO="/arroz/detalle_tendido";
	public static final String ROUTE_ARROZ_INGRESO_SECADO_RECOJO="/arroz/detalle_recojo";
	
	public static final String ROUTE_AREA_SECADO="/area/secado";
	
	private String host;
	private String rootpath;
	
	private String getall;
	private String get;	
	private String put;
	private String post;
	private String delete;
	
	public Route(String rootpath) {
		this.host=Global.SERVER_PATH;
		this.rootpath=rootpath;
		this.getall="/all";
		this.get="/";
		this.put="/update";
		this.post="/insert";
		this.delete="/";
		
	}

	public String Host() {
		return host;
	}
	public String Rootpath() {
		return rootpath;
	}
	public String Getall() {
		return prepare(getall);
	}
	public String Get() {
		return prepare(get);
	}
	public String Put() {
		return prepare(put);
	}
	public String Post() {
		return prepare(post);
	}
	public String Delete() {
		return prepare(delete);
	}	
	private String prepare(String verbo) {
		return this.host+this.rootpath+verbo;
	}
}
