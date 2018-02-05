package com.pointwest.pls.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.constant.SqlConstants;
import com.pointwest.pls.util.CustomException;

public abstract class BaseDao {
	Logger logger = Logger.getLogger(BaseDao.class);

	public Connection openDBConnection() throws CustomException {
		logger.info(GenericConstants.START);
		Connection connection = null;

		try {
			Class.forName(SqlConstants.JDBC_DRIVER_CLASS);
			connection = DriverManager.getConnection(SqlConstants.DATABASE_NETWORK, SqlConstants.DATABASE_USERNAME,
					SqlConstants.DATABASE_PASSWORD);
		} catch (ClassNotFoundException e) {
			CustomException customException = new CustomException(GenericConstants.CLASS_NOT_FOUND_EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (SQLException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_CONNECTION_ERROR, e);
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
		return connection;
	}

	public void closeConnection(Connection connection, PreparedStatement preparedStatement) throws CustomException {
		logger.info(GenericConstants.START);

		try {
			if (connection != null) {
				connection.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_CONNECTION_ERROR, e);
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

	public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws CustomException {
		logger.info(GenericConstants.START);

		try {
			if (connection != null) {
				connection.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			CustomException customException = new CustomException(GenericConstants.SQL_EXCEPTION_CONNECTION_ERROR, e);
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