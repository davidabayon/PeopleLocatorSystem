package com.pointwest.pls.constant;

import java.util.regex.Pattern;

public class GenericConstants {
	// Input validation messages
	public static final String INPUT_LOGIN_NULL = "Username/Password is empty!";
	public static final String INPUT_LOGIN_INVALID = "Invalid username/password!";
	public static final String INPUT_LOGIN_SUCCESS = "Successful logging in!";
	public static final String INPUT_LOGIN_INCORRECT = "Incorrect username/password!";
	public static final String INPUT_CHOICE_NULL = "You have not selected an option";
	public static final String INPUT_NOT_VALID = "Invalid input!";
	public static final Pattern INPUT_REGEX_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static final String ASK_CHOICE = "Enter your choice: ";
	public static final String CONFIRM_TERMINATION = "Do you want to end? [Y or N]: ";

	// Other messages
	public static final String MAX_ATTEMPTS_REACHED = "Maximum number of attempts (3) reached!";
	public final static String ATTEMPTS_REMAINING = "No. of attempts remaining [%d]";
	public final static String SELECTED_OPTION = "You have selected option [%s]";
	public final static String CONTACT_SYSTEM_ADMIN = "Please contact the System Administrator";
	public final static String EMAIL = "Email @ david.abayon@pointwest.com.ph";
	public final static String MAIN_START = "*********************START";
	public final static String MAIN_END = "*********************END";
	public final static String START = "START";
	public final static String END = "END";

	// Exception message
	public final static String EXCEPTION = "An unknown error/issue has occured";
	public final static String IO_EXCEPTION = "An unknown error/issue has occured due to the user input";
	public final static String CLASS_NOT_FOUND_EXCEPTION = "There's an error/issue with the database driver";
	public final static String SQL_EXCEPTION_CONNECTION_ERROR = "There's an error/issue with regards to the database connection!";
	public final static String SQL_EXCEPTION_RETRIEVE_ERROR = "An error/issue has occurred while retrieving database record/s!";
	public final static String SQL_EXCEPTION_RETRIEVE_TIME_ERROR = "Database timeout! Retrieval time has already been reached!";
}