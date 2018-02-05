package com.pointwest.pls.constant;

public class GenericConstants {
	// Input validation messages
	public static final String INPUT_LOGIN_NULL = "Username/Password is empty. Please try again";
	public static final String INPUT_CHOICE_NULL = "You have not selected an option. Please try again";
	public static final String INPUT_CHOICE_INVALID = "Invalid input! Please try again";

	public final static String SYSTEM_ADMIN = "System Administrator";
	public final static String EMAIL = "Email @ david.abayon@pointwest.com.ph";
	public final static String CONTACT_MESSAGE = "\nPlease contact the " + SYSTEM_ADMIN + "\n" + EMAIL;
	public final static String START = "START";
	public final static String END = "END";

	public final static String EXCEPTION = "An unknown error/issue has occured";
	public final static String IO_EXCEPTION = "An unknown error/issue has occured due to the user input";
	public final static String CLASS_NOT_FOUND_EXCEPTION = "There's an error/issue with the database driver";
	public final static String SQL_EXCEPTION_CONNECTION_ERROR = "There's an error/issue with regards to the database connection!";
	public final static String SQL_EXCEPTION_RETRIEVE_ERROR = "An error/issue has occurred while retrieving the database records!";
	public final static String SQL_EXCEPTION_RETRIEVE_TIME_ERROR = "Database timeout! Retrieval time has already been reached!";

	// Displays the Main Header
	public final static void displayMainHeader() {
		System.out.format("%140s",
				"=================================================================================================================\n");
		System.out.format("%140s",
				"=   PPPPPPPP    OOOOOOOO    IIIIIIII    NN    NN    TTTTTTTT    WW  W  WW    EEEEEEEE    SSSSSSSS    TTTTTTTT   =\n");
		System.out.format("%140s",
				"=   PPPPPPPP    OOOOOOOO    IIIIIIII    NN    NN    TTTTTTTT    WW  W  WW    EEEEEEEE    SSSSSSSS    TTTTTTTT   =\n");
		System.out.format("%140s",
				"=   PP    PP    OO    OO       II       NNN   NN       TT       WW  W  WW    EE          SS             TT      =\n");
		System.out.format("%140s",
				"=   PP    PP    OO    OO       II       NNNN  NN       TT       WW  W  WW    EEEEEEEE    SSSSSSSS       TT      =\n");
		System.out.format("%140s",
				"=   PPPPPPPP    OO    OO       II       NN NNNNN       TT       WW WWW WW    EEEEEEEE    SSSSSSSS       II      =\n");
		System.out.format("%140s",
				"=   PPPPPPPP    OO    OO       II       NN   NNN       TT       WW WWW WW    EE                SS       II      =\n");
		System.out.format("%140s",
				"=   PP          OOOOOOOO    IIIIIIII    NN    NN       TT        WWW WWW     EEEEEEEE    SSSSSSSS       TT      =\n");
		System.out.format("%140s",
				"=   PP          OOOOOOOO    IIIIIIII    NN    NN       TT         W   W      EEEEEEEE    SSSSSSSS       TT      =\n");
		System.out.format("%140s",
				"=================================================================================================================\n\n");
	}

	// Displays the Main Footer
	public final static void displayMainFooter() {
		System.out.println("\n");
		System.out.format("%140s",
				"=================================================================================================================\n");
		System.out.format("%140s",
				"=                          GGGGG    OOOOO    OOOOO    DDDD     BBBB     Y   Y    EEEEE                          =\n");
		System.out.format("%140s",
				"=                          G        O   O    O   O    D   D    B   B    Y   Y    E                              =\n");
		System.out.format("%140s",
				"=                          G GGG    O   O    O   O    D   D    BBBB      YYY     EEEEE                          =\n");
		System.out.format("%140s",
				"=                          G   G    O   O    O   O    D   D    B   B      Y      E                              =\n");
		System.out.format("%140s",
				"=                          GGGGG    OOOOO    OOOOO    DDDD     BBBB       Y      EEEEE                          =\n");
		System.out.format("%140s",
				"=================================================================================================================\n");
	}
}