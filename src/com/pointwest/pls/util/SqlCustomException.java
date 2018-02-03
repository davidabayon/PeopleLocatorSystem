package com.pointwest.pls.util;

public class SqlCustomException extends Exception {
	private static final long serialVersionUID = 1L;
	private String displayMessage;

	public SqlCustomException(String displayMessage) {
		this.displayMessage = displayMessage;
	}

	public String getDisplayMessage() {
		return displayMessage;
	}
}