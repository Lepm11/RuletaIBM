package com.ibm.academia.restapi.ruleta.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ibm.academia.restapi.ruleta.exception.BadRequestException;
import com.ibm.academia.restapi.ruleta.exception.NotFoundException;
import com.ibm.academia.restapi.ruleta.exception.RuletaNotOpenedException;

@ControllerAdvice
public class ProyectoRuletaException {
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> formatoInvalidoException(BadRequestException excepcion) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", excepcion.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> noExisteException(NotFoundException excepcion) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", excepcion.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = RuletaNotOpenedException.class)
	public ResponseEntity<Object> ruletaCerradaException(RuletaNotOpenedException excepcion) {
		Map<String, Object> respuesta = new HashMap<String, Object>();
		respuesta.put("Error", excepcion.getMessage());
		return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
	}
}
