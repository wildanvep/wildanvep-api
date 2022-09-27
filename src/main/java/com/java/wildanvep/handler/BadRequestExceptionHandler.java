package com.java.wildanvep.handler;

import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.java.wildanvep.exception.BadRequestException;

@ControllerAdvice
public class BadRequestExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> resolveException(BadRequestException exception) {
		String message = exception.getMessage();
		HttpStatus status = exception.getStatus();

		LinkedHashMap<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", message);
		response.put("status", status);
		response.put("data", null);

		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

}
