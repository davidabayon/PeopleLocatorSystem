package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

public class ViewSeatPlanByLocationFloor extends ViewPageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeIdUI.class);

	public ViewSeatPlanByLocationFloor(User user) {
		super(user);
		this.user = user;
	}

	@Override
	// Display View Seat Plan by Location-Floor header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "-- V I E W   S E A T   P L A N   B Y   L O C A T I O N - F L O O R --\n");
	}

	@Override
	// Display View Seat Plan by Location-Floor content
	public void displayPageContent() {
		System.out.format("%98s", "You can now view seat plan by Location-Floor" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
	}

	@Override
	// Ask for user input
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String location = null;
		String floorNumber = null;

		do {
			System.out.format("%65s", GenericConstants.ASK_LOCATION);
			location = scanner.nextLine();
			askAgain = viewPageManager.validateLocationInput(location);
			System.out.format("%66s", GenericConstants.ASK_FLOOR);
			floorNumber = scanner.nextLine();
			askAgain = viewPageManager.validateFloorInput(floorNumber) && askAgain;
		} while (askAgain);

		logger.debug("location: " + user.getViewByLocationInput() + ", floorNumber: " + user.getViewByFloorInput());
		logger.info(GenericConstants.END);
	}

	@Override
	public void displayList(String subPageChoice) {
		super.displayList(subPageChoice);

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
	}
}