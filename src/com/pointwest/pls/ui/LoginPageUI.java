package com.pointwest.pls.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.manager.LoginPageManager;
import com.pointwest.pls.util.CustomException;

public class LoginPageUI implements PageUI {
	Logger logger = Logger.getLogger(LoginPageUI.class);
	Scanner scanner = new Scanner(System.in);
	User user = null;

	public LoginPageUI(User user) {
		this.user = user;
	}

	@Override
	public void displayPageHeader() {
		System.out.format("%100s", "=============================================\n");
		System.out.format("%100s", "            PEOPLE LOCATOR SYSTEM            \n");
		System.out.format("%101s", "=============================================\n\n");
		System.out.format("%100s", "---------------------------------------------\n");
		System.out.format("%100s", "             L O G I N   P A G E             \n");
		System.out.format("%100s", "---------------------------------------------\n");

	}

	@Override
	public void displayPageContent() {
		System.out.format("%91s", "Welcome to Pointwest PLS!\n\n");
		System.out.format("%95s", "Login using your user credentials...\n");
	}

	@Override
	public User askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String username = null;
		String password = null;

		do {
			System.out.format("%70s", "Username: ");
			username = scanner.nextLine();
			if ("".trim().equalsIgnoreCase(username.trim()))
				username = null;
			else
				askAgain = false;

			try {
				user.setUsername(username);
			} catch (NullPointerException e) {
				logger.debug(e.getMessage());
				logger.error(e.getMessage());
				System.out.format("%100s", e.getMessage() + "\n\n");
				askAgain = true;
			}
		} while (askAgain);

		do {
			System.out.format("%70s", "Password: ");
			password = scanner.nextLine();
			if ("".trim().equalsIgnoreCase(password.trim()))
				password = null;
			else
				askAgain = false;

			try {
				user.setPassword(password);
			} catch (NullPointerException e) {
				logger.debug(e.getMessage());
				logger.error(e.getMessage());
				System.out.format("%110s", e.getMessage() + "\n\n");
				askAgain = true;
			}
		} while (askAgain);

		displayLoginStatus();
		logger.info(GenericConstants.END);
		return null;
	}

	private void displayLoginStatus() {
		logger.info(GenericConstants.START);

		LoginPageManager loginPageManager = new LoginPageManager(user);
		boolean isValid = false;

		try {
			isValid = loginPageManager.validateLoginCredentials();

			if (isValid) {
				System.out.println("LOGIN SUCCESSFUL");
			}
		} catch (CustomException e) {
			System.out.format("%110s", e.getMessage() + "\n\n");
		}

		logger.info(GenericConstants.END + " - isValid: " + isValid);
	}
}