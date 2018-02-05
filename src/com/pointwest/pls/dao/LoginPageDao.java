package com.pointwest.pls.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

	public boolean retrieveLoginCredentials() throws CustomException {
		logger.info(GenericConstants.START);

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isValid = false;

		connection = openDBConnection();
		try {
			preparedStatement = connection.prepareStatement(SqlConstants.SELECT_STATEMENT_LOGIN
					+ SqlConstants.FROM_STATEMENT_LOGIN + SqlConstants.WHERE_STATEMENT_LOGIN);
			preparedStatement.setQueryTimeout(SqlConstants.QUERY_TIMEOUT);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			isValid = preparedStatement.execute();
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
			closeConnection(connection, preparedStatement);
		}

		logger.info(GenericConstants.END);
		return isValid;
	}
}