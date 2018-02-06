package com.pointwest.pls.ui;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.manager.SearchPageManager;
import com.pointwest.pls.util.CustomException;

public class SearchPageUI implements PageUI {
	Logger logger = Logger.getLogger(SearchPageUI.class);
	Scanner scanner = new Scanner(System.in);
	SearchPageManager searchPageManager = null;
	User user = null;
	List<Employee> employees = null;

	public SearchPageUI(User user) {
		this.user = user;
		this.searchPageManager = new SearchPageManager(user);
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

		SearchPageUI searchPageUI = null;
		boolean askAgain = false;
		String searchPageChoice = null;

		do {
			System.out.format("%68s", GenericConstants.ASK_CHOICE);
			searchPageChoice = scanner.nextLine();

			// Validate if choice is within the list
			switch (searchPageChoice.trim()) {
			case "1":
				askAgain = false;
				user.setSearchPageChoice(searchPageChoice.trim());
				searchPageUI = new SearchByEmployeeIdUI(user);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", user.getSearchPageChoice());
				break;
			case "2":
				askAgain = false;
				user.setSearchPageChoice(searchPageChoice.trim());
				searchPageUI = new SearchByEmployeeNameUI(user);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", user.getSearchPageChoice());
				break;
			case "3":
				askAgain = false;
				user.setSearchPageChoice(searchPageChoice.trim());
				searchPageUI = new SearchByEmployeeProjectUI(user);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", user.getSearchPageChoice());
				break;
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

		logger.debug("searchPageChoice: " + user.getSearchPageChoice() + ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}

	// Display list of employees
	public void displayEmployeeList() {
		try {
			employees = searchPageManager.getEmployeeList();
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
		}
	}
}