package com.fonaziero.crud.controller.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fonaziero.crud.service.execption.EntityNotFoundExecption;

@ControllerAdvice
public class EntityExcptionHandler {
		
	@ExceptionHandler(EntityNotFoundExecption.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundExecption e , HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Entity not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}
}
