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

	public Connection openDBConnection() throws ClassNotFoundException, SqlCustomException {
		Connection connection = null;

		try {
			Class.forName(SqlConstants.FORNAME);
			connection = DriverManager.getConnection(SqlConstants.DATABASE, SqlConstants.DATABASE_USER,
					SqlConstants.DATABASE_PASSWORD);
		} catch (ClassNotFoundException e) {
			ClassNotFoundException classNotFoundException = new ClassNotFoundException(
					SqlConstants.CLASS_NOT_FOUND_EXCEPTION);
			logger.error(e.getMessage());
			throw classNotFoundException;
		} catch (SQLException e) {
			SqlCustomException sqlCustomException = new SqlCustomException(SqlConstants.SQL_CUSTOM_EXCEPTION);
			logger.error(e.getMessage());
			throw sqlCustomException;
		} finally {

		}

		return connection;
	}

	public void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet)
			throws SqlCustomException {
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
			SqlCustomException sqlCustomException = new SqlCustomException(SqlConstants.SQL_CUSTOM_EXCEPTION);
			logger.error(e.getMessage());
			throw sqlCustomException;
		} finally {

		}
	}
}