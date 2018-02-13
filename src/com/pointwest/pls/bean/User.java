package com.pointwest.pls.bean;

public class User extends Employee {
	private UserInput userInput = new UserInput();

	public UserInput getUserInput() {
		return userInput;
	}
}