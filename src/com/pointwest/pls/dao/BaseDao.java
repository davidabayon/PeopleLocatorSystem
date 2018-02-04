package com.pointwest.pls.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.pointwest.pls.constant.SqlConstants;
import com.pointwest.pls.util.SqlCustomException;

public abstract class BaseDao {
	Logger logger = Logger.getLogger(BaseDao.class);

	public Connection openDBConnection() throws ClassNotFoundException, SqlCustomException, Exception {
		Connection connection = null;

		try {
			Class.forName(SqlConstants.JDBC_DRIVER_CLASS);
			connection = DriverManager.getConnection(SqlConstants.DATABASE_NETWORK, SqlConstants.DATABASE_USERNAME,
					SqlConstants.DATABASE_PASSWORD);
		} catch (ClassNotFoundException e) {
			ClassNotFoundException classNotFoundException = new ClassNotFoundException(
					SqlConstants.CLASS_NOT_FOUND_EXCEPTION);
			logger.error(e.getMessage());
			throw classNotFoundException;
		} catch (SQLException e) {
			SqlCustomException sqlCustomException = new SqlCustomException(
					SqlConstants.SQL_CUSTOM_EXCEPTION_CONNECTION_ERROR);
			logger.error(e.getMessage());
			throw sqlCustomException;
		} catch (Exception e) {
			Exception exception = new Exception(SqlConstants.CUSTOM_EXCEPTION);
			logger.error(e.getMessage());
			throw exception;
		}
		return connection;
	}

	public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws SqlCustomException, Exception {
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
			SqlCustomException sqlCustomException = new SqlCustomException(
					SqlConstants.SQL_CUSTOM_EXCEPTION_CONNECTION_ERROR);
			logger.error(e.getMessage());
			throw sqlCustomException;
		} catch (Exception e) {
			Exception exception = new Exception(SqlConstants.CUSTOM_EXCEPTION);
			logger.error(e.getMessage());
			throw exception;
		}
	}
}