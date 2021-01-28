package com.saucedo.molinoapp.services.parseimplements;
@SuppressWarnings("rawtypes")
public class FactoryParse {
	public IParse parse;
	public static final String PRODUCTOR="prarse.productor.model"; 
	public static final String USUARIO="prarse.usuario.model"; 
	public FactoryParse() {
		
	}
	
	
	public IParse getParse(String typeparse) {
		switch(typeparse) {
		case PRODUCTOR:
			return new ProductorParse();			
		case USUARIO:
			return new UsuarioParse();
		}
		return null;
	}
	public static ProductorParse getProductorParse() {
		return new ProductorParse();	
	}
	public static UsuarioParse getUsurioParse() {
		return new UsuarioParse();	
	}
	
}
