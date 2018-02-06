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
	Logger logger = Logger.getLogger(LoginPageManager.class);
	SearchPageDao searchPageDao = null;
	User user = null;

	public SearchPageManager(User user) {
		this.user = user;
		this.searchPageDao = new SearchPageDao(user);
	}

	// Validate format for username and password
	public boolean validateUserInput(String username, String password) {
		logger.info(GenericConstants.START);

		Matcher matcher = GenericConstants.INPUT_REGEX_EMAIL.matcher(username);
		boolean askAgain = false;

		if (username.trim().length() > 0 && password.length() > 0 && matcher.find()) {
			String[] usernameArray = username.split("@");
			username = usernameArray[0].trim();
			user.setEmployeeUsername(username);
			askAgain = false;
		} else if (username.trim().length() == 0 || password.length() == 0) {
			askAgain = true;
			user.setLoginTries(user.getLoginTries() - 1);
			logger.error(GenericConstants.INPUT_LOGIN_NULL);
			System.out.format("%117s", GenericConstants.INPUT_LOGIN_NULL + "\n");
			System.out.format("%87s", "");
			System.out.format(GenericConstants.ATTEMPTS_REMAINING + "\n", user.getLoginTries());
		} else if (!matcher.find()) {
			askAgain = true;
			user.setLoginTries(user.getLoginTries() - 1);
			logger.error(GenericConstants.INPUT_LOGIN_INVALID);
			System.out.format("%117s", GenericConstants.INPUT_LOGIN_INVALID + "\n");
			System.out.format("%87s", "");
			System.out.format(GenericConstants.ATTEMPTS_REMAINING + "\n", user.getLoginTries());
		}

		logger.debug("askAgain: " + askAgain + ", triesCounter: " + user.getLoginTries());
		logger.info(GenericConstants.END);
		return askAgain;
	}

	// Get the list of employees
	public List<Employee> getEmployeeList() throws CustomException {
		logger.info(GenericConstants.START);

		List<Employee> employees = searchPageDao.retrieveEmployeeList();

		logger.debug("employees list: " + employees);
		logger.info(GenericConstants.END);
		return employees;
	}

	private String searchEmployeeQueryBuilder() {

		return null;
	}
}