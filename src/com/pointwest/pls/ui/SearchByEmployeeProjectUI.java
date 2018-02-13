package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Project;
import com.pointwest.pls.bean.User;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.util.CustomException;

public class SearchByEmployeeProjectUI extends SearchPageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeProjectUI.class);

	public SearchByEmployeeProjectUI(User user, UserInput userInput) {
		super(user, userInput);
		this.user = user;
		this.userInput = userInput;
	}

	@Override
	// Display Search Employee by Project header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "-------- S E A R C H   B Y   E M P L O Y E E   P R O J E C T --------\n");
	}

	@Override
	// Display Search Employee by Project content
	public void displayPageContent() {
		logger.info(GenericConstants.START);

		System.out.format("%105s", "You can now search for employee by Project" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
		System.out.format("%67s", "List of Projects:\n");
		try {
			projectChoices = searchPageManager.getProjectChoices();
			for (Project projectChoice : projectChoices) {
				System.out.format("%49s", "");
				System.out.println(projectChoice.getProjectName());
			}
			System.out.println();
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
		}

		logger.info(GenericConstants.END);
	}

	@Override
	// Ask for user input on Employee Project
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String employeeProject = null;

		do {
			System.out.format("%73s", GenericConstants.ASK_EMPLOYEE_PROJECT);
			employeeProject = scanner.nextLine();
			askAgain = validateEmployeeProjectInput(employeeProject);
		} while (askAgain);

		logger.debug("employeeProject: " + userInput.getSearchByEmployeeProjectInput() + ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}
}