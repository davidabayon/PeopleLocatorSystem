package com.pointwest.pls.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.Project;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.dao.SearchPageDao;
import com.pointwest.pls.util.CustomException;

public class SearchPageManager {
	Logger logger = Logger.getLogger(SearchPageManager.class);
	SearchPageDao searchPageDao = null;
	UserInput userInput = null;

	public SearchPageManager(UserInput userInput) {
		this.userInput = userInput;
		this.searchPageDao = new SearchPageDao(userInput);
	}

	// Get the available projects
	public List<Project> getProjectChoices() throws CustomException {
		logger.info(GenericConstants.START);

		List<Project> projectChoices = searchPageDao.retrieveProjectChoices();

		logger.debug("projectChoices list size: " + projectChoices.size());
		logger.info(GenericConstants.END);
		return projectChoices;
	}

	// Get the list of employees for chosen option
	public List<Employee> getEmployeeList(String subPageChoice) throws CustomException {
		logger.info(GenericConstants.START);

		List<Employee> employees = searchPageDao.retrieveEmployeeList(subPageChoice);

		logger.debug("employees list size: " + employees.size());
		logger.info(GenericConstants.END);
		return employees;
	}
}