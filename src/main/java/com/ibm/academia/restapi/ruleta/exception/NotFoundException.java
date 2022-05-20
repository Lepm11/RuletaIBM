package com.ibm.academia.restapi.ruleta.exception;

public class NotFoundException extends RuntimeException{

	public NotFoundException(String mensaje) {
		super(mensaje);
	}
	private static final long serialVersionUID = 8606243733533831637L;

}