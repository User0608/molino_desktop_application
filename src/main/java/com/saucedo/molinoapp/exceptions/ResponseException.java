package com.saucedo.molinoapp.exceptions;

public class ResponseException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 436577349841L;
	private String status;
	private String message;
	public ResponseException( String message,String status) {
		super(message);
		this.status = status;
		this.message = message;
	}
	@Override
	public String toString() {
		return "Mensaje: "+this.message+" , status: "+this.status!= null?this.status:"";
	}
		
}
