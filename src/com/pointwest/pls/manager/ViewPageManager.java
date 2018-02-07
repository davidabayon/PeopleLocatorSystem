package com.pointwest.pls.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.dao.ViewPageDao;
import com.pointwest.pls.util.CustomException;
import com.pointwest.pls.util.CustomRuntimeException;

public class ViewPageManager {
	Logger logger = Logger.getLogger(ViewPageManager.class);
	ViewPageDao viewPageDao = null;
	User user = null;

	public ViewPageManager(User user) {
		this.user = user;
		this.viewPageDao = new ViewPageDao(user);
	}

	// Validate Location input
	public boolean validateLocationInput(String location) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;

		switch (location.trim().toUpperCase()) {
		case GenericConstants.PIC:
		case GenericConstants.PTC:
		case GenericConstants.PLC:
			user.setViewByLocationInput(location.trim().toUpperCase());
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
	public boolean validateFloorInput(String floor) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;

		switch (floor.trim()) {
		case "2":
		case "3":
		case "4":
		case "9":
		case "12":
			user.setViewByFloorInput(floor.trim());
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
	public boolean validateQuadrantInput(String quadrant) {
		logger.info(GenericConstants.START);

		boolean askAgain = false;

		switch (quadrant.trim().toUpperCase()) {
		case "A":
		case "B":
		case "C":
		case "D":
			user.setViewByQuadrantInput(quadrant.trim().toUpperCase());
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

	// Get the list of employees
	public List<Employee> getEmployeeList(String subPageChoice) throws CustomException, CustomRuntimeException {
		logger.info(GenericConstants.START);

		List<Employee> employees = viewPageDao.retrieveEmployeeList(subPageChoice);

		logger.debug("employees list: " + employees);
		logger.info(GenericConstants.END);
		return employees;
	}
}