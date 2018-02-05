package com.pointwest.pls.constant;

public class SqlConstants {
	public final static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public final static String NETWORK_ADDRESS = "localhost";
	public final static String DATABASE_SCHEMA = "plsdb";
	public final static String DATABASE_NETWORK = "jdbc:mysql://" + NETWORK_ADDRESS + ":3306/" + DATABASE_SCHEMA;
	public final static String DATABASE_USERNAME = "david.abayon";
	public final static String DATABASE_PASSWORD = "password123";

	public final static int QUERY_TIMEOUT = 180;
	public final static String SELECT_STATEMENT_LOGIN = "SELECT emp_username, emp_password ";
	public final static String FROM_STATEMENT_LOGIN = "FROM employee ";
	public final static String WHERE_STATEMENT_LOGIN = "WHERE emp_username = ? AND emp_password = ?";
}