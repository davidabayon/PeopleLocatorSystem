package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

public class SearchByEmployeeNameUI extends SearchPageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeNameUI.class);

	public SearchByEmployeeNameUI(User user) {
		super(user);
		this.user = user;
	}

	@Override
	// Display Search Employee by Name header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "----------- S E A R C H   B Y   E M P L O Y E E   N A M E -----------\n");
	}

	@Override
	// Display Search Employee by Name content
	public void displayPageContent() {
		System.out.format("%101s", "You can now search for employee by Name" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
	}

	@Override
	// Ask for user input
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String employeeName = null;

		do {
			System.out.format("%70s", GenericConstants.ASK_EMPLOYEE_FIRST_NAME);
			employeeName = scanner.nextLine();
			askAgain = searchPageManager.validateEmployeeNameInput(employeeName);
		} while (askAgain);

		logger.debug("employeeName: " + user.getSearchByEmployeeNameInput() + ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}
}