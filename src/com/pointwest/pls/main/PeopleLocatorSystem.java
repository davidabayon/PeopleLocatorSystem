package com.pointwest.pls.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.ui.HomePageUI;
import com.pointwest.pls.ui.LoginPageUI;
import com.pointwest.pls.ui.SearchPageUI;
import com.pointwest.pls.util.DisplayHelper;

public class PeopleLocatorSystem {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(LoginPageUI.class);
		logger.info(GenericConstants.MAIN_START);

		// Instantiate objects needed
		Scanner scanner = new Scanner(System.in);

		// Declare and instantiate variables
		boolean isLoggedIn = false;
		boolean isLoggedOut = false;
		boolean askAgain = false;
		boolean isTerminate = false;
		boolean isGoBack = false;
		String homePageChoice = null;
		String searchPageChoice = null;

		// Start the application
		DisplayHelper.displayMainHeader();

		do {
			// Login Page
			User user = new User();
			LoginPageUI loginPageUI = new LoginPageUI(user);
			loginPageUI.displayPageHeader();
			loginPageUI.displayPageContent();
			do {
				loginPageUI.askUserInput();
				if (user.getLoginTries() > 0) {
					isLoggedIn = loginPageUI.displayLoginStatus();
					askAgain = true;
				} else
					askAgain = false;
				logger.debug("askAgain: " + askAgain + ", triesCounter: " + user.getLoginTries() + ", isLoggedIn: "
						+ isLoggedIn);
			} while (askAgain && !isLoggedIn);

			// Continue if user is logged in
			if (isLoggedIn) {
				do {
					logger.info(GenericConstants.START);

					// Home Page
					HomePageUI homePageUI = new HomePageUI(user);
					homePageUI.displayPageHeader();
					homePageUI.displayPageContent();
					homePageUI.askUserInput();
					homePageChoice = user.getHomePageChoice();
					switch (homePageChoice) {
					case "1":
						isLoggedOut = false;

						// Search Employee Page
						SearchPageUI searchEmployeePageUI = new SearchPageUI(user);
						searchEmployeePageUI.displayPageHeader();
						searchEmployeePageUI.displayPageContent();
						searchEmployeePageUI.askUserInput();
						searchPageChoice = user.getSearchPageChoice();

						// Go to next page
						switch (searchPageChoice) {
						case "1":
							isGoBack = false;

							// Search Employee by Employee ID

							break;
						case "2":
							isGoBack = false;

							break;
						case "3":
							isGoBack = false;

							break;
						case "4":
							isGoBack = true;
							break;
						}

						break;
					case "2":
						isLoggedOut = false;

						break;
					case "3":
						isLoggedOut = true;
						break;
					}

					logger.debug("isGoBack: " + isGoBack);
					logger.info(GenericConstants.END);
				} while (isGoBack);
			}

			// Terminate the program
			isTerminate = loginPageUI.confirmTermination();
		} while (!(isLoggedOut || isTerminate));

		// Terminate the application
		DisplayHelper.displayMainFooter();
		scanner.close();

		logger.info(GenericConstants.MAIN_END);
	}
}