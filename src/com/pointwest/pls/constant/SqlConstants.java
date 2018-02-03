package com.pointwest.pls.constant;

public class SqlConstants {
	public final static String FORNAME = "com.mysql.jdbc.Driver";
	public final static String DATABASE = "jdbc:mysql://localhost:3306/plsdb";
	public final static String DATABASE_USER = "newuser";
	public final static String DATABASE_PASSWORD = "password123";

	public final static String SYSTEM_ADMIN = "System Administrator";
	public final static String EMAIL = "Email @ david.abayon@pointwest.com.ph";
	public final static String SQL_CUSTOM_EXCEPTION = "There's an error/issue with regards to the application's database!\n"
			+ "Please contact the system administrator\n" + EMAIL;
	public final static String CLASS_NOT_FOUND_EXCEPTION = "There's an error/issue with the database's driver\n"
			+ "Please contact the " + SYSTEM_ADMIN + "\n" + EMAIL + "\n";
}