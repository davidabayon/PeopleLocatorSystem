package com.pointwest.pls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.Project;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.constant.SqlConstants;
import com.pointwest.pls.util.CustomException;
import com.pointwest.pls.util.CustomRuntimeException;

public class SearchPageDao extends BaseDao {
	Logger logger = Logger.getLogger(SearchPageDao.class);
	UserInput userInput = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	public SearchPageDao(UserInput userInput) {
		this.userInput = userInput;
	}

	// Retrieve the records for project choices
	public List<Project> retrieveProjectChoices() throws CustomException {
		logger.info(GenericConstants.START);

		ResultSet resultSet = null;
		List<Project> projectChoices = null;

		connection = getDbConnection();

		try {
			preparedStatement = connection.prepareStatement(SqlConstants.QUERY_STATEMENT_AVAILABLE_PROJECT_CHOICES);
			preparedStatement.setQueryTimeout(SqlConstants.QUERY_TIMEOUT);
			resultSet = preparedStatement.executeQuery();
			projectChoices = new ArrayList<Project>();
			while (resultSet.next()) {
				Project project = new Project();
				project.setProjectName(resultSet.getString(SqlConstants.PROJ_NAME));
				projectChoices.add(project);
			}
		} catch (NullPointerException e) {
			CustomRuntimeException customRuntimeException = new CustomRuntimeException(GenericConstants.IO_EXCEPTION,
					e);
			logger.debug(e.getMessage());
			logger.error(customRuntimeException.getMessage());
			throw customRuntimeException;
		} catch (SQLTimeoutException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_RETRIEVE_TIME_ERROR,
					e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (SQLException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_RETRIEVE_ERROR, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (Exception e) {
			CustomException customException = new CustomException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} finally {
			closeDbResources(connection, preparedStatement, resultSet);
		}

		logger.debug("projectChoices list size: " + projectChoices.size() + " records");
		logger.info(GenericConstants.END);
		return projectChoices;
	}

	// Retrieve the list of employees for chosen option
	public List<Employee> retrieveEmployeeList(String subPageChoice) throws CustomException {
		logger.info(GenericConstants.START);

		ResultSet resultSet = null;
		List<Employee> employees = null;

		connection = getDbConnection();
		String query = searchEmployeeQueryBuilder(subPageChoice);

		try {
			preparedStatementBuilder(subPageChoice, query);
			preparedStatement.setQueryTimeout(SqlConstants.QUERY_TIMEOUT);
			resultSet = preparedStatement.executeQuery();
			employees = new ArrayList<Employee>();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getString(SqlConstants.EMP_ID));
				employee.setEmployeeFirstName(resultSet.getString(SqlConstants.EMP_FIRST_NAME));
				employee.setEmployeeLastName(resultSet.getString(SqlConstants.EMP_LAST_NAME));
				employee.getEmployeeSeat().setSeatName(resultSet.getString(SqlConstants.SEAT_NAME));
				employee.getEmployeeSeat().setSeatLocalNumber(resultSet.getString(SqlConstants.SEAT_LOCAL_NUMBER));
				employee.setEmployeeShift(resultSet.getString(SqlConstants.EMP_SHIFT));
				employee.getEmployeeProject().setProjectName((resultSet.getString(SqlConstants.PROJ_NAME)));
				employees.add(employee);
			}
		} catch (IndexOutOfBoundsException e) {
			CustomRuntimeException customRuntimeException = new CustomRuntimeException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customRuntimeException.getMessage());
			throw customRuntimeException;
		} catch (NullPointerException e) {
			CustomException customException = new CustomException(GenericConstants.IO_EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (SQLTimeoutException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_RETRIEVE_TIME_ERROR,
					e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (SQLException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_RETRIEVE_ERROR, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (Exception e) {
			CustomException customException = new CustomException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} finally {
			closeDbResources(connection, preparedStatement, resultSet);
		}

		logger.debug("employees: " + employees.size() + " records");
		logger.info(GenericConstants.END);
		return employees;
	}

	// Build the query for chosen option
	private String searchEmployeeQueryBuilder(String subPageChoice) {
		logger.info(GenericConstants.START);

		String query = null;

		switch (subPageChoice) {
		case GenericConstants.SEARCH_EMPLOYEE_BY_ID:
			query = SqlConstants.SELECT_STATEMENT_SEARCH_EMPLOYEE + SqlConstants.FROM_STATEMENT_SEARCH_EMPLOYEE
					+ SqlConstants.WHERE_STATEMENT_SEARCH_BY_ID_PARTIAL + SqlConstants.GROUP_BY_ORDER_BY_EMPLOYEE_ID;
			break;
		case GenericConstants.SEARCH_EMPLOYEE_BY_NAME:
			query = SqlConstants.SELECT_STATEMENT_SEARCH_EMPLOYEE + SqlConstants.FROM_STATEMENT_SEARCH_EMPLOYEE
					+ SqlConstants.WHERE_STATEMENT_SEARCH_BY_NAME_PARTIAL + SqlConstants.GROUP_BY_ORDER_BY_EMPLOYEE_ID;
			break;
		case GenericConstants.SEARCH_EMPLOYEE_BY_PROJECT:
			query = SqlConstants.SELECT_STATEMENT_SEARCH_EMPLOYEE + SqlConstants.FROM_STATEMENT_SEARCH_EMPLOYEE
					+ SqlConstants.WHERE_STATEMENT_SEARCH_BY_PROJECT_EXACT + SqlConstants.GROUP_BY_ORDER_BY_EMPLOYEE_ID;
			break;
		}

		logger.debug("query: " + query);
		logger.info(GenericConstants.END);
		return query;
	}

	// Prepare the statement for the chose option
	private void preparedStatementBuilder(String subPageChoice, String query) throws CustomException {
		logger.info(GenericConstants.START);

		try {
			switch (subPageChoice) {
			case GenericConstants.SEARCH_EMPLOYEE_BY_ID:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + userInput.getSearchByEmployeeIdInput() + "%");
				break;
			case GenericConstants.SEARCH_EMPLOYEE_BY_NAME:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + userInput.getSearchByEmployeeNameInput() + "%");
				break;
			case GenericConstants.SEARCH_EMPLOYEE_BY_PROJECT:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, userInput.getSearchByEmployeeProjectInput());
				break;
			}
		} catch (SQLException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_RETRIEVE_ERROR, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (Exception e) {
			CustomException customException = new CustomException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		}

		logger.info(GenericConstants.END);
	}
}