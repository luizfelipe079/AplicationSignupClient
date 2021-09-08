package com.trilhaluxfacta.cadastrocliente.controller.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.trilhaluxfacta.cadastrocliente.service.exception.ObjectNotFoundException;


public class ControllerExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(System.currentTimeMillis(), 
							status.value(), "Não encontrado", e.getMessage(), 
							request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
