package com.pointwest.pls.main;

import java.util.Scanner;

import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.ui.LoginPageUI;

public class PeopleLocatorSystem {
	public static void main(String[] args) {
		// Instantiate objects needed
		Scanner scanner = new Scanner(System.in);

		// Declare and instantiate variables
		String homePageChoice = null;
		Boolean isLoggedOut = false;
		Boolean isTerminate = false;

		// Start the application
		GenericConstants.displayMainHeader();

		// Login Page
		do {
			LoginPageUI loginPageUI = new LoginPageUI();
			loginPageUI.displayPageHeader();
			loginPageUI.displayPageContent();
			homePageChoice = loginPageUI.askUserInput();

			switch (homePageChoice) {

			}
		} while (isLoggedOut || !isTerminate);

		// Terminate the application
		GenericConstants.displayMainFooter();
		scanner.close();
	}
}