package com.voyanta.challenge.dto.coretoclient;

public class ErrorResponse {

	private String ErrorMsg;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(String errorMsg) {
		super();
		ErrorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return ErrorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		ErrorMsg = errorMsg;
	}

}
