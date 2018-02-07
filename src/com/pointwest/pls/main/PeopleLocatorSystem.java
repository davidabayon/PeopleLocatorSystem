package com.pointwest.pls.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.ui.HomePageUI;
import com.pointwest.pls.ui.LoginPageUI;
import com.pointwest.pls.ui.SearchByEmployeeIdUI;
import com.pointwest.pls.ui.SearchByEmployeeNameUI;
import com.pointwest.pls.ui.SearchByEmployeeProjectUI;
import com.pointwest.pls.ui.SearchPageUI;
import com.pointwest.pls.ui.SubPageUI;
import com.pointwest.pls.ui.ViewPageUI;
import com.pointwest.pls.ui.ViewSeatPlanByEmployee;
import com.pointwest.pls.ui.ViewSeatPlanByLocationFloor;
import com.pointwest.pls.ui.ViewSeatPlanByQuadrant;
import com.pointwest.pls.util.DisplayHelper;

public class PeopleLocatorSystem {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(LoginPageUI.class);
		logger.info(GenericConstants.MAIN_START);

		// Instantiate objects needed
		Scanner scanner = new Scanner(System.in);

		// Declare and instantiate variables
		boolean isLoggedIn = false;
		boolean askAgain = false;
		boolean isTerminate = false;
		boolean isGoBack = false;
		String homePageChoice = null;
		String subPageChoice = null;

		// Start the application
		DisplayHelper.displayMainHeader();

		do {
			logger.info(GenericConstants.START + ": Login Page");

			// Login Page
			User user = new User();
			LoginPageUI loginPageUI = new LoginPageUI(user);
			loginPageUI.displayPageHeader();
			loginPageUI.displayPageContent();
			do {
				loginPageUI.askUserInput();
				if (user.getLoginTries() > 0) {
					isLoggedIn = loginPageUI.displayLoginStatus();
					askAgain = true;
				} else
					askAgain = false;
				logger.debug("askAgain: " + askAgain + ", triesCounter: " + user.getLoginTries() + ", isLoggedIn: "
						+ isLoggedIn);
			} while (askAgain && !isLoggedIn);

			// Continue if user is logged in
			if (isLoggedIn) {
				do {
					logger.info(GenericConstants.START + ": Home Page");

					// Home Page
					HomePageUI homePageUI = new HomePageUI(user);
					homePageUI.displayPageHeader();
					homePageUI.displayPageContent();
					homePageUI.askUserInput();
					homePageChoice = user.getHomePageChoice();
					SubPageUI subPageUI = null;
					switch (homePageChoice) {
					case GenericConstants.SEARCH_EMPLOYEE:

						// Create Search Employee Page
						subPageUI = new SearchPageUI(user);
						break;
					case GenericConstants.VIEW_SEAT_PLAN:

						// Create View Seat Plan Page
						subPageUI = new ViewPageUI(user);
						break;
					case GenericConstants.LOGOUT:
						isLoggedIn = false;
						break;
					}
					if (isLoggedIn) {
						do {
							logger.info(GenericConstants.START + ": Sub Page");

							// Sub Page - Search Employee or View Seat Plan
							subPageUI.displayPageHeader();
							subPageUI.displayPageContent();
							subPageUI.askUserInput();
							subPageChoice = user.getSubPageChoice();
							switch (subPageChoice) {
							case GenericConstants.SEARCH_EMPLOYEE_BY_ID:
								isGoBack = false;
								SearchByEmployeeIdUI searchByEmployeeIdUI = new SearchByEmployeeIdUI(user);
								searchByEmployeeIdUI.displayPageHeader();
								searchByEmployeeIdUI.displayPageContent();
								searchByEmployeeIdUI.askUserInput();

								if (!user.isViewByEmployeeInput())
									searchByEmployeeIdUI.displayList(subPageChoice);
								else {
									isGoBack = false;
									user.setSubPageChoice(GenericConstants.VIEW_SEAT_PLAN_BY_EMPLOYEE);
								}

								break;
							case GenericConstants.SEARCH_EMPLOYEE_BY_NAME:
								isGoBack = false;
								SearchByEmployeeNameUI searchByEmployeeNameUI = new SearchByEmployeeNameUI(user);
								searchByEmployeeNameUI.displayPageHeader();
								searchByEmployeeNameUI.displayPageContent();
								searchByEmployeeNameUI.askUserInput();
								searchByEmployeeNameUI.displayList(subPageChoice);
								break;
							case GenericConstants.SEARCH_EMPLOYEE_BY_PROJECT:
								isGoBack = false;
								SearchByEmployeeProjectUI searchByEmployeeProjectUI = new SearchByEmployeeProjectUI(
										user);
								searchByEmployeeProjectUI.displayPageHeader();
								searchByEmployeeProjectUI.displayPageContent();
								searchByEmployeeProjectUI.askUserInput();
								searchByEmployeeProjectUI.displayList(subPageChoice);
								break;
							case GenericConstants.VIEW_SEAT_PLAN_BY_LOC_FLOOR:
								isGoBack = false;
								ViewSeatPlanByLocationFloor viewSeatPlanByLocationFloor = new ViewSeatPlanByLocationFloor(
										user);
								viewSeatPlanByLocationFloor.displayPageHeader();
								viewSeatPlanByLocationFloor.displayPageContent();
								viewSeatPlanByLocationFloor.askUserInput();
								viewSeatPlanByLocationFloor.displayList(subPageChoice);
								break;
							case GenericConstants.VIEW_SEAT_PLAN_BY_QUADRANT:
								isGoBack = false;
								ViewSeatPlanByQuadrant viewSeatPlanByQuadrant = new ViewSeatPlanByQuadrant(user);
								viewSeatPlanByQuadrant.displayPageHeader();
								viewSeatPlanByQuadrant.displayPageContent();
								viewSeatPlanByQuadrant.askUserInput();
								viewSeatPlanByQuadrant.displayList(subPageChoice);
								break;
							case GenericConstants.VIEW_SEAT_PLAN_BY_EMPLOYEE:
								isGoBack = false;
								ViewSeatPlanByEmployee viewSeatPlanByEmployee = new ViewSeatPlanByEmployee(user);
								viewSeatPlanByEmployee.displayList(subPageChoice);
								break;
							case GenericConstants.GO_BACK:
								isGoBack = true;
								break;
							}

							logger.debug("isGoBack: " + isGoBack);
							logger.info(GenericConstants.END + ": Sub Page");
						} while (!isGoBack);
					}
					logger.debug("isLoggedIn: " + isLoggedIn);
					logger.info(GenericConstants.END + ": Home Page");
				} while (isLoggedIn);
			}

			// Terminate the program
			isTerminate = loginPageUI.confirmTermination();
			logger.debug("isTerminate: " + isTerminate);
			logger.info(GenericConstants.END + ": Login Page / End Page");
		} while (!isTerminate);

		// Terminate the application
		DisplayHelper.displayMainFooter();
		scanner.close();

		logger.info(GenericConstants.MAIN_END);
	}
}