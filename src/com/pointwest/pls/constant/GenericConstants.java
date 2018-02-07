package com.pointwest.pls.constant;

import java.util.regex.Pattern;

public interface GenericConstants {
	// Choices
	String SEARCH_EMPLOYEE = "Search Employee";
	String SEARCH_EMPLOYEE_BY_ID = "Search Employee by ID";
	String SEARCH_EMPLOYEE_BY_NAME = "Search Employee by Name";
	String SEARCH_EMPLOYEE_BY_PROJECT = "Search Employee by Project";
	String VIEW_SEAT_PLAN = "View Seat Plan";
	String VIEW_SEAT_PLAN_BY_LOC_FLOOR = "View Seat Plan by Loc-Floor";
	String VIEW_SEAT_PLAN_BY_QUADRANT = "View Seat Plan by Quadrant";
	String VIEW_SEAT_PLAN_BY_EMPLOYEE = "View Seat Plan by Employee";
	String GO_BACK = "Go Back";
	String LOGOUT = "Logout";
	String PIC = "PIC";
	String PTC = "PTC";
	String PLC = "PLC";

	// Input validation messages
	String INPUT_LOGIN_NULL = "Username/Password is empty!";
	String INPUT_LOGIN_INVALID = "Invalid username/password!";
	String INPUT_LOGIN_SUCCESS = "Successful logging in!";
	String INPUT_LOGIN_INCORRECT = "Incorrect username/password!";
	String INPUT_CHOICE_NULL = "You have not selected an option";
	String INPUT_NULL = "Input is empty!";
	String INPUT_INVALID = "Invalid input!";
	Pattern INPUT_REGEX_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	Pattern INPUT_REGEX_NUMBER = Pattern.compile("^([0-9])+$");
	Pattern INPUT_REGEX_ALPHABET_CHARS = Pattern.compile("^([A-Za-z ])+$");

	String ASK_CHOICE = "Enter your choice: ";
	String ASK_EMPLOYEE_ID = "Enter Employee ID: ";
	String ASK_EMPLOYEE_FIRST_NAME = "Enter Employee Name: ";
	String ASK_EMPLOYEE_PROJECT = "Enter Employee Project: ";
	String ASK_LOCATION = "Enter Location: ";
	String ASK_FLOOR = "Enter Floor No.: ";
	String ASK_QUADRANT = "Enter Quadrant: ";
	String CONFIRM_TERMINATION = "Do you want to end? [Y or N]: ";

	// Other messages
	String MAX_ATTEMPTS_REACHED = "Maximum number of attempts (3) reached!";
	String ATTEMPTS_REMAINING = "No. of attempts remaining [%d]";
	String NO_RECORDS_FOUND = "No record found : [%d]";
	String RECORDS_FOUND = "Record/s found : [%d]";
	String SELECTED_OPTION = "You have selected option [%s]";
	String CONTACT_SYSTEM_ADMIN = "Please contact the System Administrator";
	String EMAIL = "Email @ david.abayon@pointwest.com.ph";
	String MAIN_START = "*********************START";
	String MAIN_END = "*********************END";
	String START = "START";
	String END = "END";

	// Exception message
	String EXCEPTION = "An unknown error/issue has occured";
	String IO_EXCEPTION = "An unknown error/issue has occured due to the user input";
	String CLASS_NOT_FOUND_EXCEPTION = "There's an error/issue with the database driver";
	String SQL_EXCEPTION_CONNECTION_ERROR = "There's an error/issue with regards to the database connection!";
	String SQL_EXCEPTION_RETRIEVE_ERROR = "An error/issue has occurred while retrieving database record/s!";
	String SQL_EXCEPTION_RETRIEVE_TIME_ERROR = "Database timeout! Retrieval time has already been reached!";
}