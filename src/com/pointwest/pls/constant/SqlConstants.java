package com.pointwest.pls.constant;

public class SqlConstants {
	public final static String JDBC_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public final static String NETWORK_ADDRESS = "localhost";
	public final static String DATABASE_SCHEMA = "plsdb";
	public final static String DATABASE_NETWORK = "jdbc:mysql://" + NETWORK_ADDRESS + ":3306/" + DATABASE_SCHEMA;
	public final static String DATABASE_USERNAME = "newuser";
	public final static String DATABASE_PASSWORD = "password123";

	public final static String SYSTEM_ADMIN = "System Administrator";
	public final static String EMAIL = "Email @ david.abayon@pointwest.com.ph";
	public final static String SQL_CUSTOM_EXCEPTION_CONNECTION_ERROR = "There's an error/issue with regards to the database connection!\n"
			+ "Please contact " + SYSTEM_ADMIN + "\n" + EMAIL + "\n";

	public final static String SQL_CUSTOM_EXCEPTION_GENERAL_ERROR = "An error/issue has occurred while retrieving the database records!\n"
			+ "Please contact " + SYSTEM_ADMIN + "\n" + EMAIL + "\n";

	public final static String CLASS_NOT_FOUND_EXCEPTION = "There's an error/issue with the database driver\n"
			+ "Please contact the " + SYSTEM_ADMIN + "\n" + EMAIL + "\n";

	public final static String CUSTOM_EXCEPTION = "An unknown error/issue has occured\n" + "Please contact the "
			+ SYSTEM_ADMIN + "\n" + EMAIL + "\n";
}