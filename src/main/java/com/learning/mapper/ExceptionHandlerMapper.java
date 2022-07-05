package com.learning.mapper;

import java.util.Date;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerMapper {
	
	@ExceptionHandler(value = AccountStatusException.class)
	public ResponseEntity<Object> handleResourceNotFound(String msg){
		ErrorMapper error = new ErrorMapper(msg,HttpStatus.NOT_FOUND.value(), new Date());
		
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
	}
}
