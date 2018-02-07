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
import com.pointwest.pls.util.CustomRuntimeException;

public class ViewPageDao extends BaseDao {
	Logger logger = Logger.getLogger(LoginPageDao.class);
	User user = null;
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	public ViewPageDao(User user) {
		this.user = user;
	}

	// Retrieve details of the current user
	public List<Employee> retrieveEmployeeList(String subPageChoice) throws CustomException, CustomRuntimeException {
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
				employee.getEmployeeSeat().setSeatId(resultSet.getString(SqlConstants.SEAT_ID));
				employee.getEmployeeSeat().setSeatName(resultSet.getString(SqlConstants.SEAT_NAME));
				employee.setEmployeeFirstName(resultSet.getString(SqlConstants.EMP_FIRST_NAME));
				employee.setEmployeeLastName(resultSet.getString(SqlConstants.EMP_LAST_NAME));
				employee.getEmployeeSeat().setSeatLocalNumber(resultSet.getString(SqlConstants.SEAT_LOCAL_NUMBER));
				employees.add(employee);
			}
		} catch (IndexOutOfBoundsException e) {
			CustomRuntimeException customRuntimeException = new CustomRuntimeException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customRuntimeException.getMessage());
			throw customRuntimeException;
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
		case GenericConstants.VIEW_SEAT_PLAN_BY_LOC_FLOOR:
			query = SqlConstants.SELECT_STATEMENT_VIEW_SEATPLAN + SqlConstants.FROM_STATEMENT_VIEW_SEATPLAN
					+ SqlConstants.WHERE_STATEMENT_VIEW_BY_LOCATION_FLOOR + SqlConstants.GROUP_BY_ORDER_BY_SEAT_ID;
			break;
		case GenericConstants.VIEW_SEAT_PLAN_BY_QUADRANT:
			query = SqlConstants.QUERY_STATEMENT_VIEW_SEATPLAN_QUADRANT;
			break;
		case GenericConstants.VIEW_SEAT_PLAN_BY_EMPLOYEE:
			query = null;
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
			case GenericConstants.VIEW_SEAT_PLAN_BY_LOC_FLOOR:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, user.getViewByLocationInput());
				preparedStatement.setString(2, user.getViewByFloorInput());
				break;
			case GenericConstants.VIEW_SEAT_PLAN_BY_QUADRANT:
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, user.getViewByLocationInput());
				preparedStatement.setString(2, user.getViewByFloorInput());
				preparedStatement.setString(3, user.getViewByQuadrantInput());
				preparedStatement.setString(4, user.getViewByLocationInput());
				preparedStatement.setString(5, user.getViewByFloorInput());
				break;
			case GenericConstants.VIEW_SEAT_PLAN_BY_EMPLOYEE:
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