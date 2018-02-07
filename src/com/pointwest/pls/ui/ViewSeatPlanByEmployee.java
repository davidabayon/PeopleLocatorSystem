package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;

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
	// Display list of searched seats
	public void displayList(String subPageChoice) {
		logger.info(GenericConstants.START);
		super.displayList((subPageChoice));

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

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
	}
}