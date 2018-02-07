package com.pointwest.pls.manager;

import java.util.List;
import java.util.regex.Matcher;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.dao.SearchPageDao;
import com.pointwest.pls.util.CustomException;

public class SearchPageManager {
	Logger logger = Logger.getLogger(SearchPageManager.class);
	SearchPageDao searchPageDao = null;
	User user = null;

	public SearchPageManager(User user) {
		this.user = user;
		this.searchPageDao = new SearchPageDao(user);
	}

	// Validate Employee ID input
	public boolean validateEmployeeIdInput(String employeeId) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_NUMBER.matcher(employeeId.trim());
		boolean askAgain = false;

		if (employeeId.trim().length() > 0 && matcher.find()) {
			user.setSearchByEmployeeIdInput(employeeId.trim());
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
	public boolean validateEmployeeNameInput(String name) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_ALPHABET_CHARS.matcher(name.trim());
		boolean askAgain = false;

		if (name.trim().length() > 0 && matcher.find()) {
			user.setSearchByEmployeeNameInput(name.trim());
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
	public boolean validateEmployeeProjectInput(String project) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_ALPHABET_CHARS.matcher(project.trim());
		boolean askAgain = false;

		if (project.trim().length() > 0 && matcher.find()) {
			user.setSearchByEmployeeProjectInput(project.trim());
			askAgain = false;
		} else if (project.trim().length() == 0) {
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

	// Get the list of employees
	public List<Employee> getEmployeeList(String subPageChoice) throws CustomException {
		logger.info(GenericConstants.START);

		List<Employee> employees = searchPageDao.retrieveEmployeeList(subPageChoice);

		logger.debug("employees list: " + employees);
		logger.info(GenericConstants.END);
		return employees;
	}
}