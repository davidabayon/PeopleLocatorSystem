package com.pointwest.pls.main;

import java.util.Scanner;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.ui.LoginPageUI;

public class PeopleLocatorSystem {
	public static void main(String[] args) {
		// Instantiate objects needed
		Scanner scanner = new Scanner(System.in);

		// Declare and instantiate variables
		String homePageChoice;
		Boolean isLoggedOut = true;
		Boolean isTerminate = true;

		// Start the application
		// GenericConstants.displayMainHeader();

		// Login Page
		do {
			User user = new User();
			LoginPageUI loginPageUI = new LoginPageUI(user);
			loginPageUI.displayPageHeader();
			loginPageUI.displayPageContent();
			user = loginPageUI.askUserInput();
			// homePageChoice = user.getHomePageChoice();
			// switch (homePageChoice) {
			//
			// }
		} while (!(isLoggedOut && isTerminate));

		// Terminate the application
		// GenericConstants.displayMainFooter();
		scanner.close();
	}
}