package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.manager.ViewPageManager;
import com.pointwest.pls.util.CustomException;
import com.pointwest.pls.util.CustomRuntimeException;

public class ViewPageUI extends SubPageUI {
	Logger logger = Logger.getLogger(ViewPageUI.class);
	ViewPageManager viewPageManager = null;

	public ViewPageUI(User user, UserInput userInput) {
		this.user = user;
		this.userInput = userInput;
		this.viewPageManager = new ViewPageManager(userInput);
	}

	@Override
	// Display View Seat Plan Page header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "---------------------------------------------------------------------\n");
		System.out.format("%118s", "                 V I E W   S E A T   P L A N   P A G E               \n");
		System.out.format("%118s", "---------------------------------------------------------------------\n");
	}

	@Override
	// Display View Seat Plan Page content
	public void displayPageContent() {
		System.out.format("%104s", "You're now at the View Seat Plan Page!" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%117s", "[" + user.getEmployeeRole() + "]\n");

		System.out.format("%86s", "Please select an option:            \n");
		System.out.format("%86s", "[1] View Seat Plan by Location-Floor\n");
		System.out.format("%86s", "[2] View Seat Plan by Quadrant      \n");
		System.out.format("%87s", "[3] Go back to Home Page            \n\n");
	}

	@Override
	// Ask for user option choice
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String viewPageChoice = null;

		do {
			System.out.format("%68s", GenericConstants.ASK_CHOICE);
			viewPageChoice = scanner.nextLine();

			// Validate if choice is within the list
			switch (viewPageChoice.trim()) {
			case "1":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.VIEW_SEAT_PLAN_BY_LOC_FLOOR);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", viewPageChoice.trim());
				break;
			case "2":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.VIEW_SEAT_PLAN_BY_QUADRANT);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", viewPageChoice.trim());
				break;
			case "3":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.VIEW_SEAT_PLAN_BY_EMPLOYEE);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", viewPageChoice.trim());
				break;
			case "4":
				askAgain = false;
				userInput.setSubPageChoice(GenericConstants.GO_BACK);
				System.out.format("%88s", "");
				System.out.format(GenericConstants.SELECTED_OPTION + "\n", viewPageChoice.trim());
				break;
			case "":
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_CHOICE_NULL + "\n");
			default:
				askAgain = true;
				System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
			}
		} while (askAgain);

		logger.debug("viewPageChoice: [" + viewPageChoice.trim() + "] " + userInput.getSubPageChoice() + ", askAgain: "
				+ askAgain);
		logger.info(GenericConstants.END);
	}

	@Override
	// Display list of searched seats
	public void displayList(String subPageChoice) {
		logger.info(GenericConstants.START);

		try {
			employees = viewPageManager.getEmployeeList(subPageChoice);

			int maxNumberPerQuadrant = 9;
			int maxNumberOfRow = 3;
			int maxNumberOfColumn = 3;
			int index = 0;

			// Check if employee list has returned results
			if (employees.size() > 0) {

				// Display Quadrant A and B
				System.out.println();
				System.out.println(
						" ==================================================================================  ==================================================================================");

				for (int counterNumber = 0; counterNumber < maxNumberOfRow; counterNumber++) {
					System.out.format("%1s", " ");
					for (int quadrant = 0; quadrant < 2; quadrant++) {
						for (int columnNumber = maxNumberOfRow * maxNumberOfColumn
								* quadrant; columnNumber < (quadrant * maxNumberPerQuadrant)
										+ maxNumberOfColumn; columnNumber++) {
							index = (counterNumber * maxNumberOfColumn) + columnNumber;
							System.out.format("%1s %-25s", "|", employees.get(index).getEmployeeSeat().getSeatName());
						}
						System.out.print("|  ");
					}
					// System.out.format("%1s", "");
					for (int quadrant = 0; quadrant < 2; quadrant++) {
						for (int columnNumber = maxNumberOfRow * maxNumberOfColumn
								* quadrant; columnNumber < (quadrant * maxNumberPerQuadrant)
										+ maxNumberOfColumn; columnNumber++) {
							index = (counterNumber * maxNumberOfColumn) + columnNumber;
							if (employees.get(index).getEmployeeFirstName() == null) {
								employees.get(index).setEmployeeFirstName("");
							}
							if (employees.get(index).getEmployeeLastName() == null) {
								employees.get(index).setEmployeeLastName("");
							}
							System.out.format("%1s %-25s", "|", employees.get(index).getEmployeeFirstName() + " "
									+ employees.get(index).getEmployeeLastName());
						}
						System.out.print("|  ");
					}
					// System.out.format("%1s", "");
					for (int quadrant = 0; quadrant < 2; quadrant++) {
						for (int columnNumber = maxNumberOfRow * maxNumberOfColumn
								* quadrant; columnNumber < (quadrant * maxNumberPerQuadrant)
										+ maxNumberOfColumn; columnNumber++) {
							index = (counterNumber * maxNumberOfColumn) + columnNumber;
							if (employees.get(index).getEmployeeSeat().getSeatLocalNumber() == null) {
								employees.get(index).getEmployeeSeat().setSeatLocalNumber("");
							}
							System.out.format("%1s %-25s", "|",
									employees.get(index).getEmployeeSeat().getSeatLocalNumber());
						}
						System.out.print("|  ");
					}
					System.out.println(
							"==================================================================================  ==================================================================================");
				}
				System.out.println();
				System.out.println(
						" ==================================================================================  ==================================================================================");

				// Display Quadrant C and D
				for (int counterNumber = 0; counterNumber < maxNumberOfRow; counterNumber++) {
					System.out.format("%1s", "");
					for (int quadrant = 2; quadrant < 4; quadrant++) {
						for (int columnNumber = maxNumberOfRow * maxNumberOfColumn
								* quadrant; columnNumber < (quadrant * maxNumberPerQuadrant)
										+ maxNumberOfColumn; columnNumber++) {
							index = (counterNumber * maxNumberOfColumn) + columnNumber;
							System.out.format("%1s %-25s", "|", employees.get(index).getEmployeeSeat().getSeatName());
						}
						System.out.print("|  ");
					}
					// System.out.format("%1s", "");
					for (int quadrant = 2; quadrant < 4; quadrant++) {
						for (int columnNumber = maxNumberOfRow * maxNumberOfColumn
								* quadrant; columnNumber < (quadrant * maxNumberPerQuadrant)
										+ maxNumberOfColumn; columnNumber++) {
							index = (counterNumber * maxNumberOfColumn) + columnNumber;
							if (employees.get(index).getEmployeeFirstName() == null) {
								employees.get(index).setEmployeeFirstName("");
							}
							if (employees.get(index).getEmployeeLastName() == null) {
								employees.get(index).setEmployeeLastName("");
							}
							System.out.format("%1s %-25s", "|", employees.get(index).getEmployeeFirstName() + " "
									+ employees.get(index).getEmployeeLastName());
						}
						System.out.print("|  ");
					}
					// System.out.format("%1s", "");
					for (int quadrant = 2; quadrant < 4; quadrant++) {
						for (int columnNumber = maxNumberOfRow * maxNumberOfColumn
								* quadrant; columnNumber < (quadrant * maxNumberPerQuadrant)
										+ maxNumberOfColumn; columnNumber++) {
							index = (counterNumber * maxNumberOfColumn) + columnNumber;
							if (employees.get(index).getEmployeeSeat().getSeatLocalNumber() == null) {
								employees.get(index).getEmployeeSeat().setSeatLocalNumber("");
							}
							System.out.format("%1s %-25s", "|",
									employees.get(index).getEmployeeSeat().getSeatLocalNumber());
						}
						System.out.print("|  ");
					}
					System.out.println(
							"==================================================================================  ==================================================================================");
				}
			}
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
			System.out.format("%117s", GenericConstants.CONTACT_SYSTEM_ADMIN + "\n");
			System.out.format("%117s", GenericConstants.EMAIL + "\n");
		} catch (CustomRuntimeException e) {
			System.out.format("%117s", e.getMessage() + "\n");
			System.out.format("%117s", GenericConstants.CONTACT_SYSTEM_ADMIN + "\n");
			System.out.format("%117s", GenericConstants.EMAIL + "\n");
		}

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
	}

	// Validate Location input
	protected boolean validateLocationInput(String location) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;

		switch (location.trim().toUpperCase()) {
		case GenericConstants.PIC:
		case GenericConstants.PTC:
		case GenericConstants.PLC:
			userInput.setViewByLocationInput(location.trim().toUpperCase());
			askAgain = false;
			break;
		case "":
			askAgain = true;
			logger.error(GenericConstants.INPUT_NULL);
			System.out.format("%117s", GenericConstants.INPUT_NULL + "\n");
			break;
		default:
			askAgain = true;
			logger.error(GenericConstants.INPUT_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
		}

		logger.debug("askAgain: " + askAgain);
		logger.info(GenericConstants.END);
		return askAgain;
	}

	// Validate Floor input
	protected boolean validateFloorInput(String floor) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;

		switch (floor.trim()) {
		case "2":
		case "3":
		case "4":
		case "9":
		case "12":
			userInput.setViewByFloorInput(floor.trim());
			askAgain = false;
			break;
		case "":
			askAgain = true;
			logger.error(GenericConstants.INPUT_NULL);
			System.out.format("%117s", GenericConstants.INPUT_NULL + "\n");
			break;
		default:
			askAgain = true;
			logger.error(GenericConstants.INPUT_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
		}

		logger.debug("askAgain: " + askAgain);
		logger.info(GenericConstants.END);
		return askAgain;
	}

	// Validate Quadrant input
	protected boolean validateQuadrantInput(String quadrant) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;

		switch (quadrant.trim().toUpperCase()) {
		case "A":
		case "B":
		case "C":
		case "D":
			userInput.setViewByQuadrantInput(quadrant.trim().toUpperCase());
			askAgain = false;
			break;
		case "":
			askAgain = true;
			logger.error(GenericConstants.INPUT_NULL);
			System.out.format("%117s", GenericConstants.INPUT_NULL + "\n");
			break;
		default:
			askAgain = true;
			logger.error(GenericConstants.INPUT_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_INVALID + "\n");
		}

		logger.debug("askAgain: " + askAgain);
		logger.info(GenericConstants.END);
		return askAgain;
	}
}