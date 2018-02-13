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

public class ViewSeatPlanByQuadrant extends ViewPageUI {
	Logger logger = Logger.getLogger(ViewSeatPlanByQuadrant.class);

	public ViewSeatPlanByQuadrant(User user, UserInput userInput) {
		super(user, userInput);
		this.user = user;
		this.userInput = userInput;
	}

	@Override
	// Display View Seat Plan by Quadrant header
	public void displayPageHeader() {
		System.out.println();
		System.out.format("%118s", "-------- V I E W   S E A T   P L A N   B Y   Q U A D R A N T --------\n");
	}

	@Override
	// Display View Seat Plan by Quadrant content
	public void displayPageContent() {
		System.out.format("%104s", "You can now view seat plan by Quadrant" + "\n\n");
		System.out.format("%117s", "Currently logged in:" + "\n");
		System.out.format("%117s", user.getEmployeeFirstName() + " " + user.getEmployeeLastName() + "\n");
		System.out.format("%118s", "[" + user.getEmployeeRole() + "]\n\n");
		System.out.format("%49s", "");
		System.out.format("%-10s %-10s %-11s", "Locations", " - Floors", " - Quadrants");
		System.out.println();
		try {
			HashMap<Building, Seat> locationFloorChoices = viewPageManager.getLocationFloorQuadrantChoices();
			for (Entry<Building, Seat> locationFloorChoice : locationFloorChoices.entrySet()) {
				System.out.format("%49s", "");
				System.out.format("%-10s %-10s %-11s", "  [" + locationFloorChoice.getKey().getBuildingId() + "]",
						" - " + locationFloorChoice.getValue().getSeatFloorNumber(),
						" - " + locationFloorChoice.getValue().getSeatQuadrant());
				System.out.println();
			}
			System.out.println();
		} catch (CustomException e) {
			System.out.format("%117s", e.getMessage() + "\n");
		}
	}

	@Override
	// Ask for user input
	public void askUserInput() {
		logger.info(GenericConstants.START);

		boolean askAgain = false;
		String location = null;
		String floorNumber = null;
		String quadrant = null;

		do {
			System.out.format("%65s", GenericConstants.ASK_LOCATION);
			location = scanner.nextLine();
			askAgain = validateLocationInput(location);
		} while (askAgain);
		do {
			System.out.format("%66s", GenericConstants.ASK_FLOOR);
			floorNumber = scanner.nextLine();
			askAgain = validateFloorInput(floorNumber);
		} while (askAgain);
		do {
			System.out.format("%65s", GenericConstants.ASK_QUADRANT);
			quadrant = scanner.nextLine();
			askAgain = validateQuadrantInput(quadrant);
		} while (askAgain);

		logger.debug("location: " + userInput.getViewByLocationInput() + ", floorNumber: "
				+ userInput.getViewByFloorInput() + ", quadrant: " + userInput.getViewByQuadrantInput());
		logger.info(GenericConstants.END);
	}

	@Override
	public void displayList(String subPageChoice) {
		logger.info(GenericConstants.START);
		super.displayList(subPageChoice);

		if (employees.size() == 0) {
			System.out.format("%95s", "");
			System.out.format(GenericConstants.NO_RECORDS_FOUND + "\n", employees.size());
		} else {
			System.out.format("%117s", "Location: " + userInput.getViewByLocationInput() + "\n");
			System.out.format("%117s", "Floor: " + userInput.getViewByFloorInput() + "\n");
			System.out.format("%117s", "Quadant: " + userInput.getViewByQuadrantInput() + "\n");
			System.out.format("%96s", "");
			System.out.format(GenericConstants.RECORDS_FOUND + "\n", employees.size() / 4);
		}

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
	}
}