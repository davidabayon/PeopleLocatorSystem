package com.pointwest.pls.util;

public class CustomRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;
	private Exception exception;

	public CustomRuntimeException(String message, Exception exception) {
		super(message, exception);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public Exception getException() {
		return exception;
	}
}