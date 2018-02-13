package com.pointwest.pls.manager;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Building;
import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.Seat;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.dao.ViewPageDao;
import com.pointwest.pls.util.CustomException;
import com.pointwest.pls.util.CustomRuntimeException;

public class ViewPageManager {
	Logger logger = Logger.getLogger(ViewPageManager.class);
	ViewPageDao viewPageDao = null;
	UserInput userInput = null;

	public ViewPageManager(UserInput userInput) {
		this.userInput = userInput;
		this.viewPageDao = new ViewPageDao(userInput);
	}

	// Get the available location and floor
	public HashMap<Building, Seat> getLocationFloorQuadrantChoices() throws CustomException {
		logger.info(GenericConstants.START);

		HashMap<Building, Seat> locationFloorQuadrantChoices = viewPageDao.retrieveLocationFloorQuadrantChoices();

		logger.debug("locationFloorQuadrantChoices map size: " + locationFloorQuadrantChoices.size());
		logger.info(GenericConstants.END);
		return locationFloorQuadrantChoices;
	}

	// Get the list of employees for the chosen option
	public List<Employee> getEmployeeList(String subPageChoice) throws CustomException, CustomRuntimeException {
		logger.info(GenericConstants.START);

		List<Employee> employees = viewPageDao.retrieveEmployeeList(subPageChoice);

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
		return employees;
	}
}