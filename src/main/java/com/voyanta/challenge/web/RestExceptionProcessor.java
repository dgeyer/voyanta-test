package com.voyanta.challenge.web;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionProcessor extends ResponseEntityExceptionHandler {

	private static final String BLANK = " ";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		List<ObjectError> globalErrors = ex.getBindingResult()
				.getGlobalErrors();
		StringBuilder error = new StringBuilder();
		for (FieldError fieldError : fieldErrors) {
			error.append(fieldError.getField()).append(BLANK)
					.append(fieldError.getDefaultMessage());
		}
		for (ObjectError objectError : globalErrors) {
			error.append(objectError.getObjectName()).append(BLANK)
					.append(objectError.getDefaultMessage());
		}
		return new ResponseEntity<Object>(error.toString(), headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		String error;
		if (mostSpecificCause != null) {
			error = mostSpecificCause.getMessage();
		} else {
			error = ex.getMessage();
		}
		return new ResponseEntity<Object>(error, headers, status);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		String error = ex.getMessage();
		if (ex instanceof IllegalArgumentException) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Object>(error, headers, status);
	}
}