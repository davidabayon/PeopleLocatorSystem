package com.pointwest.pls.ui;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.Project;
import com.pointwest.pls.bean.User;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.manager.SearchPageManager;
import com.pointwest.pls.util.CustomException;

public class SearchPageUI extends SubPageUI {
	Logger logger = Logger.getLogger(SearchPageUI.class);
	SearchPageManager searchPageManager = null;
	List<Project> projectChoices = null;

	public SearchPageUI(User user, UserInput userInput) {
		this.user = user;
		this.userInput = userInput;
		this.searchPageManager = new SearchPageManager(userInput);
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
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.SEARCH_EMPLOYEE_BY_ID);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", searchPageChoice.trim());
				break;
			case "2":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.SEARCH_EMPLOYEE_BY_NAME);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", searchPageChoice.trim());
				break;
			case "3":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.SEARCH_EMPLOYEE_BY_PROJECT);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", searchPageChoice.trim());
				break;
			case "4":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.GO_BACK);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", searchPageChoice.trim());
				break;
			case "":
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_CHOICE_NULL + "\n");
			default:
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
			}
		} while (askAgain);

		logger.debug("searchPageChoice: [" + searchPageChoice.trim() + "] " + userInput.getSubPageChoice()
				+ ", askAgain: " + askAgain);
		logger.info(GenericConstants.END);
	}

	@Override
	// Display list of searched employees
	public void displayList(String subPageChoice) {
		logger.info(GenericConstants.START);

		try {
			employees = searchPageManager.getEmployeeList(subPageChoice);

			int recordNumber = 1;
			System.out.println();
			System.out.format("%155s",
					"==============================================================================================================================================\n");
			System.out.format("%13s", "");
			System.out.format("%-10s %-13s %-21s %-21s %-17s %-10s %-13s %-28s", " RESULT #", "| EMPLOYEE ID",
					"| FIRST NAME", "| LAST NAME", "| SEAT LOCATION", "| LOCAL #", "| WORK SHIFT", "| PROJECT/S");
			System.out.println();
			System.out.format("%154s",
					"==============================================================================================================================================");
			System.out.println();
			if (employees.size() > 0) {
				for (Employee employee : employees) {
					if (employee.getEmployeeProject().getProjectName() == null) {
						employee.getEmployeeProject().setProjectName("No Current Project");
					}

					System.out.format("%13s", "");
					System.out.format("%-10s %-13s %-21s %-21s %-17s %-10s %-13s %-28s", " " + recordNumber,
							"| " + employee.getEmployeeId(), "| " + employee.getEmployeeFirstName(),
							"| " + employee.getEmployeeLastName(), "| " + employee.getEmployeeSeat().getSeatName(),
							"| " + employee.getEmployeeSeat().getSeatLocalNumber(), "| " + employee.getEmployeeShift(),
							"| " + employee.getEmployeeProject().getProjectName());
					System.out.println();
					recordNumber++;
				}
			} else {
				System.out.format("%13s", "");
				System.out.format("%-10s %-13s %-21s %-21s %-17s %-10s %-13s %-28s", " " + "N/A", "| " + "N/A",
						"| " + "N/A", "| " + "N/A", "| " + "N/A", "| " + "N/A", "| " + "N/A", "| " + "N/A");
				System.out.println();
			}

			System.out.format("%154s",
					"==============================================================================================================================================");
			System.out.println();

			if (employees.size() == 0) {
				System.out.format("%95s", "");
				System.out.format(GenericConstants.NO_RECORDS_FOUND + "\n", employees.size());
			} else {
				System.out.format("%95s", "");
				System.out.format(GenericConstants.RECORDS_FOUND + "\n", employees.size());
			}
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
			System.out.format("%117s", GenericConstants.CONTACT_SYSTEM_ADMIN + "\n");
			System.out.format("%117s", GenericConstants.EMAIL + "\n");
		}

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
	}

	// Validate Employee ID input
	protected boolean validateEmployeeIdInput(String employeeId) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_NUMBER.matcher(employeeId.trim());
		boolean askAgain = false;

		if (employeeId.trim().length() > 0 && matcher.find()) {
			userInput.setSearchByEmployeeIdInput(employeeId.trim());
			askAgain = false;
		} else if (employeeId.trim().length() == 0) {
			askAgain = true;
			logger.error(GenericConstants.INPUT_NULL);
			System.out.format("%117s", GenericConstants.INPUT_NULL + "\n");
		} else if (!matcher.find()) {
			askAgain = true;
			logger.error(GenericConstants.INPUT_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
		}

		logger.debug("askAgain: " + askAgain);
		logger.info(GenericConstants.END);
		return askAgain;
	}

	// Validate Employee Name input
	protected boolean validateEmployeeNameInput(String name) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_ALPHABET_CHARS.matcher(name.trim());
		boolean askAgain = false;

		if (name.trim().length() > 0 && matcher.find()) {
			userInput.setSearchByEmployeeNameInput(name.trim());
			askAgain = false;
		} else if (name.trim().length() == 0) {
			askAgain = true;
			logger.error(GenericConstants.INPUT_NULL);
			System.out.format("%117s", GenericConstants.INPUT_NULL + "\n");
		} else if (!matcher.find()) {
			askAgain = true;
			logger.error(GenericConstants.INPUT_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
		}

		logger.debug("askAgain: " + askAgain);
		logger.info(GenericConstants.END);
		return askAgain;
	}

	// Validate Employee Project input
	protected boolean validateEmployeeProjectInput(String project) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		project = project.trim();

		try {
			projectChoices = searchPageManager.getProjectChoices();
			for (int index = 0; index < projectChoices.size(); index++) {
				if (projectChoices.get(index).getProjectName().equalsIgnoreCase(project) && project.length() > 0) {
					userInput.setSearchByEmployeeProjectInput(project);
					askAgain = false;
					break;
				} else if (project.length() == 0) {
					askAgain = true;
					if (index == projectChoices.size() - 1) {
						logger.error(GenericConstants.INPUT_NULL);
						System.out.format("%117s", GenericConstants.INPUT_NULL + "\n");
					}
				} else {
					askAgain = true;
					if (index == projectChoices.size() - 1) {
						logger.error(GenericConstants.INPUT_INVALID);
						System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
					}
				}
			}
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
		}

		logger.debug("askAgain: " + askAgain);
		logger.info(GenericConstants.END);
		return askAgain;
	}
}