package com.practica.instituto.excepciones;


public class ConnectException extends RuntimeException{

	private String code;

	public ConnectException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}


	
	
}
