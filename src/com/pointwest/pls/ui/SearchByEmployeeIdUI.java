package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

public class SearchByEmployeeIdUI extends SearchPageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeIdUI.class);

	public SearchByEmployeeIdUI(User user) {
		super(user);
		this.user = user;
	}

	@Override
	// Display Search Employee by ID header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "------------- S E A R C H   B Y   E M P L O Y E E   I D -------------\n");
	}

	@Override
	// Display Search Employee by ID content
	public void displayPageContent() {
		System.out.format("%103s", "You can now search for employee by ID" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
	}

	@Override
	// Ask for user input
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String employeeId = null;

		do {
			System.out.format("%68s", GenericConstants.ASK_EMPLOYEE_ID);
			employeeId = scanner.nextLine();
			askAgain = searchPageManager.validateEmployeeIdInput(employeeId);
		} while (askAgain);

		logger.debug("employeeId: " + user.getSearchByEmployeeIdInput() + ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}
}