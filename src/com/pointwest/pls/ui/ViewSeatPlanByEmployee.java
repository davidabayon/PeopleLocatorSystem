package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.util.CustomException;
import com.pointwest.pls.util.CustomRuntimeException;

public class ViewSeatPlanByEmployee extends ViewPageUI {
	Logger logger = Logger.getLogger(ViewSeatPlanByEmployee.class);

	public ViewSeatPlanByEmployee(User user) {
		super(user);
		this.user = user;
	}

	@Override
	// Display View Seat Plan by Employee header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "-------- V I E W   S E A T   P L A N   B Y   E M P L O Y E E --------\n");
	}

	@Override
	// Display View Seat Plan by Employee content
	public void displayPageContent() {
	}

	@Override
	// Ask for user input
	public void askUserInput() {
	}

	@Override
	// Display seat plan list
	public void displayList(String subPageChoice) {
		try {
			employees = viewPageManager.getEmployeeList(subPageChoice);

			int maxNumberPerQuadrant = 9;
			int maxNumberOfRow = 3;
			int maxNumberOfColumn = 3;
			int index = 0;
			if (employees.size() > 0) {
				// Quadrant A and B
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
				// Quadrant C and D
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

		if (employees.size() == 0) {
			System.out.format("%95s", "");
			System.out.format(GenericConstants.NO_RECORDS_FOUND + "\n", employees.size());
		} else {
			System.out.format("%117s", "Location: " + user.getViewByLocationInput() + "\n");
			System.out.format("%117s", "Floor: " + user.getViewByFloorInput() + "\n");
			System.out.format("%117s", "Quadant: " + user.getViewByQuadrantInput() + "\n");
			System.out.format("%96s", "");
			System.out.format(GenericConstants.RECORDS_FOUND + "\n", employees.size());
		}

		logger.debug("employees: " + employees);
		logger.info(GenericConstants.END);
	}
}