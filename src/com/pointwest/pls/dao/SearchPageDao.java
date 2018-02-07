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
import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.constant.SqlConstants;
import com.pointwest.pls.util.CustomException;

public class SearchPageDao extends BaseDao {
	Logger logger = Logger.getLogger(SearchPageDao.class);
	User user = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	public SearchPageDao(User user) {
		this.user = user;
	}

	// Retrieve details of the current user
	public List<Employee> retrieveEmployeeList(String subPageChoice) throws CustomException {
		logger.info(GenericConstants.START);

		ResultSet resultSet = null;
		List<Employee> employees = null;

		connection = openDBConnection();
		String query = searchEmployeeQueryBuilder(subPageChoice);

		try {
			preparedStatementBuilder(subPageChoice, query);
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
			closeConnection(connection, preparedStatement, resultSet);
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
					+ SqlConstants.WHERE_STATEMENT_SEARCH_BY_PROJECT_PARTIAL
					+ SqlConstants.GROUP_BY_ORDER_BY_EMPLOYEE_ID;
			break;
		}

		logger.debug("query: " + query);
		logger.info(GenericConstants.END);
		return query;
	}

	private void preparedStatementBuilder(String subPageChoice, String query) throws CustomException {
		logger.info(GenericConstants.START);

		try {
			switch (subPageChoice) {
			case GenericConstants.SEARCH_EMPLOYEE_BY_ID:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + user.getSearchByEmployeeIdInput() + "%");
				break;
			case GenericConstants.SEARCH_EMPLOYEE_BY_NAME:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + user.getSearchByEmployeeNameInput() + "%");
				break;
			case GenericConstants.SEARCH_EMPLOYEE_BY_PROJECT:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, "%" + user.getSearchByEmployeeProjectInput() + "%");
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