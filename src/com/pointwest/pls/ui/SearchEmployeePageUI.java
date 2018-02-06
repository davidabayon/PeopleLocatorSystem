package com.pointwest.pls.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

public class SearchEmployeePageUI implements PageUI {
	Logger logger = Logger.getLogger(LoginPageUI.class);
	Scanner scanner = new Scanner(System.in);
	User user = null;

	public SearchEmployeePageUI(User user) {
		this.user = user;
	}

	@Override
	// Display Search Employee Page header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "---------------------------------------------------------------------\n");
		System.out.format("%118s", "               S E A R C H   E M P L O Y E E   P A G E               \n");
		System.out.format("%118s", "---------------------------------------------------------------------\n");
	}

	@Override
	// Display Search Employee Page content
	public void displayPageContent() {
		System.out.format("%104s", "You're now at the Search Employee Page!" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%117s", "[" + user.getEmployeeRole() + "]\n");

		System.out.format("%84s", "Please select an option:          \n");
		System.out.format("%84s", "[1] Search Employee by Employee ID\n");
		System.out.format("%84s", "[2] Search Employee by Name       \n");
		System.out.format("%84s", "[3] Search Employee by Project    \n");
		System.out.format("%85s", "[4] Go back to Home Page          \n\n");
	}

	@Override
	// Ask for user option choice
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String searchPageChoice = null;

		do {
			System.out.format("%68s", GenericConstants.ASK_CHOICE);
			searchPageChoice = scanner.nextLine();

			// Validate if choice is within the list
			switch (searchPageChoice.trim()) {
			case "1":
			case "2":
			case "3":
			case "4":
				askAgain = false;
				user.setSearchPageChoice(searchPageChoice.trim());
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", user.getSearchPageChoice());
				break;
			default:
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_NOT_VALID + "\n");
			}

		} while (askAgain);

		logger.debug("homePageChoice: " + user.getSearchPageChoice() + ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}

	// Display list of employees
	public void displayEmployeeList() {

	}
}