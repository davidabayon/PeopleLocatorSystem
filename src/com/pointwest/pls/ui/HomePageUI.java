package com.pointwest.pls.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

public class HomePageUI implements PageUI {
	Logger logger = Logger.getLogger(LoginPageUI.class);
	Scanner scanner = new Scanner(System.in);
	User user = null;

	public HomePageUI(User user) {
		this.user = user;
	}

	@Override
	// Display Home Page header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "---------------------------------------------------------------------\n");
		System.out.format("%118s", "                          H O M E   P A G E                          \n");
		System.out.format("%118s", "---------------------------------------------------------------------\n");
	}

	@Override
	// Display Home Page content
	public void displayPageContent() {
		System.out.format("%99s", "You are now at the Home Page!" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");

		System.out.format("%74s", "Please select an option:\n");
		System.out.format("%74s", "[1] Search Employee     \n");
		System.out.format("%74s", "[2] View Seat Plan      \n");
		System.out.format("%75s", "[3] Logout              \n\n");
	}

	@Override
	// Ask for user option choice
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String homePageChoice = null;

		do {
			System.out.format("%68s", GenericConstants.ASK_CHOICE);
			homePageChoice = scanner.nextLine();

			// Validate if choice is within the list
			switch (homePageChoice.trim()) {
			case "1":
				askAgain = false;
				user.setHomePageChoice(GenericConstants.SEARCH_EMPLOYEE);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", homePageChoice.trim());
				break;
			case "2":
				askAgain = false;
				user.setHomePageChoice(GenericConstants.VIEW_SEAT_PLAN);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", homePageChoice.trim());
				break;
			case "3":
				askAgain = false;
				user.setHomePageChoice(GenericConstants.LOGOUT);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", homePageChoice.trim());
				break;
			case "":
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_CHOICE_NULL + "\n");
			default:
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
			}
		} while (askAgain);

		logger.debug("homePageChoice: [" + homePageChoice.trim() + "]" + user.getHomePageChoice() + ", askAgain: "
				+ askAgain);
		logger.info(GenericConstants.END);
	}
}