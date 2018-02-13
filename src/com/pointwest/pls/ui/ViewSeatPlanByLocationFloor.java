package com.pointwest.pls.ui;

import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Building;
import com.pointwest.pls.bean.Seat;
import com.pointwest.pls.bean.User;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.util.CustomException;

public class ViewSeatPlanByLocationFloor extends ViewPageUI {
	Logger logger = Logger.getLogger(ViewSeatPlanByLocationFloor.class);

	public ViewSeatPlanByLocationFloor(User user, UserInput userInput) {
		super(user, userInput);
		this.user = user;
		this.userInput = userInput;
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
		logger.info(GenericConstants.START);

		System.out.format("%104s", "You can now view seat plan by Location-Floor" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
		System.out.format("%49s", "");
		System.out.format("%-10s %-10s", "Locations", " - Floors");
		System.out.println();
		try {
			HashMap<Building, Seat> locationFloorQuadrantChoices = viewPageManager.getLocationFloorQuadrantChoices();
			for (Entry<Building, Seat> locationFloorQuadrantChoice : locationFloorQuadrantChoices.entrySet()) {
				System.out.format("%49s", "");
				System.out.format("%-10s %-10s", "  [" + locationFloorQuadrantChoice.getKey().getBuildingId() + "]",
						" - " + locationFloorQuadrantChoice.getValue().getSeatFloorNumber());
				System.out.println();
			}
			System.out.println();
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
		}

		logger.info(GenericConstants.END);
	}

	@Override
	// Ask for user input
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String location = null;
		String floorNumber = null;

		do {// Ask for Location input
			System.out.format("%65s", GenericConstants.ASK_LOCATION);
			location = scanner.nextLine();
			askAgain = validateLocationInput(location);

		} while (askAgain);

		do {// Ask for Floor number input
			System.out.format("%66s", GenericConstants.ASK_FLOOR);
			floorNumber = scanner.nextLine();
			askAgain = validateFloorInput(floorNumber);
		} while (askAgain);

		logger.debug("location: " + userInput.getViewByLocationInput() + ", floorNumber: "
				+ userInput.getViewByFloorInput());
		logger.info(GenericConstants.END);
	}

	@Override
	// Display list of searched seats
	public void displayList(String subPageChoice) {
		logger.info(GenericConstants.START);
		super.displayList(subPageChoice);

		if (employees.size() == 0) {
			System.out.format("%95s", "");
			System.out.format(GenericConstants.NO_RECORDS_FOUND + "\n", employees.size());
		} else {
			System.out.format("%117s", "Location: " + userInput.getViewByLocationInput() + "\n");
			System.out.format("%117s", "Floor: " + userInput.getViewByFloorInput() + "\n");
			System.out.format("%96s", "");
			System.out.format(GenericConstants.RECORDS_FOUND + "\n", employees.size());
		}

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
	}
}