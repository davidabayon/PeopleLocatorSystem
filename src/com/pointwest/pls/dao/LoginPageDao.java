package com.pointwest.pls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.constant.SqlConstants;
import com.pointwest.pls.util.CustomException;

public class LoginPageDao extends BaseDao {
	Logger logger = Logger.getLogger(LoginPageDao.class);
	User user = null;

	public LoginPageDao(User user) {
		this.user = user;
	}

	// Retrieve details of the current user
	public boolean retrieveUserDetails(String password) throws CustomException {
		logger.info(GenericConstants.START);

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean hasMatched = false;

		Connection connection = getDbConnection();

		try {
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_STATEMENT_LOGIN
					+ SqlConstants.FROM_STATEMENT_LOGIN + SqlConstants.WHERE_STATEMENT_LOGIN);
			preparedStatement.setQueryTimeout(SqlConstants.QUERY_TIMEOUT);
			preparedStatement.setString(1, user.getEmployeeUsername());
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user.setEmployeeFirstName(resultSet.getString(SqlConstants.EMP_FIRST_NAME));
				user.setEmployeeLastName(resultSet.getString(SqlConstants.EMP_LAST_NAME));
				user.setEmployeeRole(resultSet.getString(SqlConstants.EMP_ROLE));
				hasMatched = true;
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
			closeDbResources(connection, preparedStatement, resultSet);
		}

		logger.debug("First Name: " + user.getEmployeeFirstName() + ", Last Name: " + user.getEmployeeLastName());
		logger.info(GenericConstants.END);
		return hasMatched;
	}
}