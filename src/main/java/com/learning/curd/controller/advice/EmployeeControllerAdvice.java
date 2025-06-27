package com.learning.curd.controller.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.learning.exception.UserCreationException;

@RestControllerAdvice
public class EmployeeControllerAdvice extends ResponseEntityExceptionHandler{

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		 
		return super.handleMethodArgumentNotValid(ex, headers, status, request);
	}
	
	@ExceptionHandler(UserCreationException.class)
	public ResponseEntity<String> userCreationException(UserCreationException creationException, WebRequest request){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(creationException.getMessage());
	}

}
