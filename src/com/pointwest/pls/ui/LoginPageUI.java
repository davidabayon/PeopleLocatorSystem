package com.pointwest.pls.ui;

import java.util.Scanner;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.manager.LoginPageManager;
import com.pointwest.pls.util.CustomException;

public class LoginPageUI implements PageUI {
	Logger logger = Logger.getLogger(LoginPageUI.class);
	Scanner scanner = new Scanner(System.in);
	LoginPageManager loginPageManager = null;
	User user = null;
	UserInput userInput = null;
	String password = null;

	public LoginPageUI(User user, UserInput userInput) {
		this.user = user;
		this.userInput = userInput;
		this.loginPageManager = new LoginPageManager(user);
	}

	@Override
	// Display Login Page header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "=====================================================================\n");
		System.out.format("%118s", "                        PEOPLE LOCATOR SYSTEM                        \n");
		System.out.format("%119s", "=====================================================================\n\n");
		System.out.format("%118s", "---------------------------------------------------------------------\n");
		System.out.format("%118s", "                         L O G I N   P A G E                         \n");
		System.out.format("%118s", "---------------------------------------------------------------------\n");
	}

	@Override
	// Display Login Page content
	public void displayPageContent() {
		System.out.format("%96s", "Welcome to Pointwest PLS!\n");
		System.out.format("%97s", "Press [Enter] to continue...");
		scanner.nextLine();
		System.out.println();
		System.out.format("%86s", "Login using your user credentials...\n");
	}

	@Override
	// Ask for user's username and password
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String username = null;
		String password = null;

		do {
			if (userInput.getLoginTries() > 0) {
				try {
					System.out.format("%59s", "Username: ");
					username = scanner.nextLine();
					System.out.format("%59s", "Password: ");
					password = loginPageManager.encryptUserPassword(scanner.nextLine());
				} catch (CustomException e) {
					System.out.format("%117s", e.getMessage() + "\n");
				}

				askAgain = validateUserInput(username, password);
			} else {
				askAgain = false;
				System.out.format("%117s", GenericConstants.MAX_ATTEMPTS_REACHED + "\n");
			}
		} while (askAgain);

		logger.debug("Username: " + user.getEmployeeUsername());
		logger.info(GenericConstants.END);
	}

	// Validate format for username and password
	private boolean validateUserInput(String username, String password) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_EMAIL.matcher(username.trim());
		boolean askAgain = false;

		if (username.trim().length() > 0 && password.length() > 0 && matcher.find()) {
			String[] usernameArray = username.split("@");
			username = usernameArray[0].trim();
			user.setEmployeeUsername(username.trim());
			this.password = password;
			askAgain = false;
		} else if (username.trim().length() == 0 || password.length() == 0) {
			askAgain = true;
			userInput.setLoginTries(userInput.getLoginTries() - 1);
			logger.error(GenericConstants.INPUT_LOGIN_NULL);
			System.out.format("%117s", GenericConstants.INPUT_LOGIN_NULL + "\n");
			System.out.format("%87s", "");
			System.out.format(GenericConstants.ATTEMPTS_REMAINING + "\n", userInput.getLoginTries());
		} else if (!matcher.find()) {
			askAgain = true;
			userInput.setLoginTries(userInput.getLoginTries() - 1);
			logger.error(GenericConstants.INPUT_LOGIN_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_LOGIN_INVALID + "\n");
			System.out.format("%87s", "");
			System.out.format(GenericConstants.ATTEMPTS_REMAINING + "\n", userInput.getLoginTries());
		}

		logger.debug("askAgain: " + askAgain + ", triesCounter: " + userInput.getLoginTries());
		logger.info(GenericConstants.END);
		return askAgain;
	}

	// Display status for login
	public boolean displayLoginStatus() {
		logger.info(GenericConstants.START);

		boolean hasMatched = false;

		try {
			hasMatched = loginPageManager.checkMatchingLoginCredentials(password);

			if (hasMatched) {
				System.out.format("%117s", GenericConstants.INPUT_LOGIN_SUCCESS + "\n");
			} else {
				userInput.setLoginTries(userInput.getLoginTries() - 1);
				System.out.format("%117s", GenericConstants.INPUT_LOGIN_INCORRECT + "\n");
				System.out.format("%87s", "");
				System.out.format(GenericConstants.ATTEMPTS_REMAINING + "\n", userInput.getLoginTries());
			}
		} catch (CustomException e) {
			hasMatched = false;
			userInput.setLoginTries(userInput.getLoginTries() - 1);
			System.out.format("%117s", e.getMessage() + "\n");
			System.out.format("%117s", GenericConstants.CONTACT_SYSTEM_ADMIN + "\n");
			System.out.format("%117s", GenericConstants.EMAIL + "\n");
			System.out.format("%87s", "");
			System.out.format(GenericConstants.ATTEMPTS_REMAINING + "\n", userInput.getLoginTries());
		}

		logger.debug("hasMatched: " + hasMatched);
		logger.info(GenericConstants.END);
		return hasMatched;
	}

	// Display termination confirmation
	public boolean confirmTermination() {
		logger.info(GenericConstants.START);

		String inputDecision = null;
		boolean askAgain = false;
		boolean isTerminate = false;

		do {
			System.out.println();
			System.out.format("%79s", GenericConstants.CONFIRM_TERMINATION);
			inputDecision = scanner.nextLine();
			if ("Y".equalsIgnoreCase(inputDecision.trim()) || "Yes".equalsIgnoreCase(inputDecision.trim())) {
				isTerminate = true;
				askAgain = false;
			} else if ("N".equalsIgnoreCase(inputDecision.trim()) || "No".equalsIgnoreCase(inputDecision.trim())) {
				isTerminate = false;
				askAgain = false;
			} else {
				System.out.format("%116s", GenericConstants.INPUT_INVALID);
				askAgain = true;
			}
		} while (askAgain);

		System.out.println();
		logger.debug("isTerminate: " + isTerminate);
		logger.info(GenericConstants.END);
		return isTerminate;
	}
}