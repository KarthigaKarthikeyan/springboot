package com.stacifysimple.springrestservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorException customErrorException = new CustomErrorException(new Date(), "From method Argument in GEH",
				ex.getMessage());
		return new ResponseEntity<>(customErrorException, HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorException customErrorException = new CustomErrorException(new Date(), "From HttpmethodNotSupported Argument in GEH",
				ex.getMessage());
		return new ResponseEntity<>(customErrorException, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex, WebRequest request){
		
		CustomErrorException customErrorException = new CustomErrorException(new Date(),
				ex.getMessage(),request.getSessionId());
		return new ResponseEntity<>(customErrorException, HttpStatus.NOT_FOUND);
		
	}

}
