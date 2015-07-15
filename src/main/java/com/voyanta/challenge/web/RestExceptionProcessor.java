package com.voyanta.challenge.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voyanta.challenge.dto.coretoclient.ErrorResponse;

@ControllerAdvice(basePackages = { "com.voyanta.challenge.web" })
public class RestExceptionProcessor {

	private static final String BLANK = " ";
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected void handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpServletResponse response)
			throws IOException {
		Throwable mostSpecificCause = ex.getMostSpecificCause();
		String error;
		if (mostSpecificCause != null) {
			error = mostSpecificCause.getMessage();
		} else {
			error = ex.getMessage();
		}
		this.writeInHttpResponse(error, response);
		response.setStatus(HttpStatus.BAD_REQUEST.value());
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected void handleExceptionInternal(IllegalArgumentException ex,
			HttpServletResponse response) throws IOException {
		StringWriter writer = new StringWriter();
		ex.printStackTrace(new PrintWriter(writer));
		logger.debug("{}", writer.toString());
		String error = ex.getMessage();
		int status = HttpStatus.BAD_REQUEST.value();
		this.writeInHttpResponse(error, response);
		response.setStatus(status);
	}

	private void writeInHttpResponse(String text, HttpServletResponse response)
			throws IOException {
		ErrorResponse error = new ErrorResponse(text);
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		response.getWriter().write(mapper.writeValueAsString(error));
	}
}