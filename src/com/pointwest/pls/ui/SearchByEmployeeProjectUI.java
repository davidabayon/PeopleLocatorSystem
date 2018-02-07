package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

public class SearchByEmployeeProjectUI extends SearchPageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeProjectUI.class);

	public SearchByEmployeeProjectUI(User user) {
		super(user);
		this.user = user;
	}

	@Override
	// Display Search Employee by Name header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "-------- S E A R C H   B Y   E M P L O Y E E   P R O J E C T --------\n");
	}

	@Override
	// Display Search Employee by Name content
	public void displayPageContent() {
		System.out.format("%98s", "You can now search for employee by Project" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
	}

	@Override
	// Ask for user input
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String employeeProject = null;

		do {
			System.out.format("%62s", GenericConstants.ASK_EMPLOYEE_PROJECT);
			employeeProject = scanner.nextLine();
			askAgain = searchPageManager.validateEmployeeProjectInput(employeeProject);
		} while (askAgain);

		logger.debug("employeeProject: " + user.getSearchByEmployeeProjectInput() + ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}
}