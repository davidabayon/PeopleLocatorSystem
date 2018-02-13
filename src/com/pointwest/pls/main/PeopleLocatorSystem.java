package com.pointwest.pls.main;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.bean.UserInput;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.ui.HomePageUI;
import com.pointwest.pls.ui.LoginPageUI;
import com.pointwest.pls.ui.SearchByEmployeeIdUI;
import com.pointwest.pls.ui.SearchByEmployeeNameUI;
import com.pointwest.pls.ui.SearchByEmployeeProjectUI;
import com.pointwest.pls.ui.SearchPageUI;
import com.pointwest.pls.ui.SubPageUI;
import com.pointwest.pls.ui.ViewPageUI;
import com.pointwest.pls.ui.ViewSeatPlanByLocationFloor;
import com.pointwest.pls.ui.ViewSeatPlanByQuadrant;
import com.pointwest.pls.util.DisplayHelper;

/*
* Author : David Abayon
*   Date : February 2, 208
*/

public class PeopleLocatorSystem {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(PeopleLocatorSystem.class);
		logger.info(GenericConstants.MAIN_START);

		// Instantiate objects needed
		Scanner scanner = new Scanner(System.in);

		// Declare and instantiate variables
		boolean isLoggedIn = false;
		boolean askAgain = false;
		boolean isTerminate = false;
		boolean isGoBack = false;

		// Start the application
		DisplayHelper.displayMainHeader();

		do {
			logger.info(GenericConstants.START + ": Login Page");

			// Login Page
			User user = new User();
			UserInput userInput = user.getUserInput();
			LoginPageUI loginPageUI = new LoginPageUI(user, userInput);
			loginPageUI.displayPageHeader();
			loginPageUI.displayPageContent();
			do {
				loginPageUI.askUserInput();
				if (userInput.getLoginTries() > 0) {
					isLoggedIn = loginPageUI.displayLoginStatus();
					askAgain = true;
				} else
					askAgain = false;
				logger.debug("askAgain: " + askAgain + ", triesCounter: " + userInput.getLoginTries() + ", isLoggedIn: "
						+ isLoggedIn);
			} while (askAgain && !isLoggedIn);

			// Continue if user is logged in
			if (isLoggedIn) {
				do {
					logger.info(GenericConstants.START + ": Home Page");

					// Go to Home Page
					HomePageUI homePageUI = new HomePageUI(user, userInput);
					homePageUI.displayPageHeader();
					homePageUI.displayPageContent();
					homePageUI.askUserInput();
					String homePageChoice = userInput.getHomePageChoice();
					SubPageUI subPageUI = null;
					switch (homePageChoice) {
					case GenericConstants.SEARCH_EMPLOYEE:

						// Create Search Employee Page
						subPageUI = new SearchPageUI(user, userInput);
						break;
					case GenericConstants.VIEW_SEAT_PLAN:

						// Create View Seat Plan Page
						subPageUI = new ViewPageUI(user, userInput);
						break;
					case GenericConstants.LOGOUT:
						isLoggedIn = false;
						break;
					}

					// If user is still logged in, proceed with the next page
					if (isLoggedIn) {
						do {
							logger.info(GenericConstants.START + ": Sub Page");

							// Go to Sub Page - Search Employee or View Seat Plan
							subPageUI.displayPageHeader();
							subPageUI.displayPageContent();
							subPageUI.askUserInput();
							String subPageChoice = userInput.getSubPageChoice();
							switch (subPageChoice) {
							case GenericConstants.SEARCH_EMPLOYEE_BY_ID:
								isGoBack = false;
								logger.info(GenericConstants.START + ": SearchByEmployeeIdUI Page");

								SearchByEmployeeIdUI searchByEmployeeIdUI = new SearchByEmployeeIdUI(user, userInput);
								searchByEmployeeIdUI.displayPageHeader();
								searchByEmployeeIdUI.displayPageContent();
								searchByEmployeeIdUI.askUserInput();
								searchByEmployeeIdUI.displayList(subPageChoice);

								logger.info(GenericConstants.END + ": SearchByEmployeeIdUI Page");
								break;
							case GenericConstants.SEARCH_EMPLOYEE_BY_NAME:
								isGoBack = false;
								logger.info(GenericConstants.START + ": SearchByEmployeeNameUI Page");

								SearchByEmployeeNameUI searchByEmployeeNameUI = new SearchByEmployeeNameUI(user,
										userInput);
								searchByEmployeeNameUI.displayPageHeader();
								searchByEmployeeNameUI.displayPageContent();
								searchByEmployeeNameUI.askUserInput();
								searchByEmployeeNameUI.displayList(subPageChoice);

								logger.info(GenericConstants.END + ": SearchByEmployeeNameUI Page");
								break;
							case GenericConstants.SEARCH_EMPLOYEE_BY_PROJECT:
								isGoBack = false;
								logger.info(GenericConstants.START + ": SearchByEmployeeProjectUI Page");

								SearchByEmployeeProjectUI searchByEmployeeProjectUI = new SearchByEmployeeProjectUI(
										user, userInput);
								searchByEmployeeProjectUI.displayPageHeader();
								searchByEmployeeProjectUI.displayPageContent();
								searchByEmployeeProjectUI.askUserInput();
								searchByEmployeeProjectUI.displayList(subPageChoice);

								logger.info(GenericConstants.END + ": SearchByEmployeeProjectUI Page");
								break;
							case GenericConstants.VIEW_SEAT_PLAN_BY_LOC_FLOOR:
								isGoBack = false;
								logger.info(GenericConstants.START + ": ViewSeatPlanByLocationFloor Page");

								ViewSeatPlanByLocationFloor viewSeatPlanByLocationFloor = new ViewSeatPlanByLocationFloor(
										user, userInput);
								viewSeatPlanByLocationFloor.displayPageHeader();
								viewSeatPlanByLocationFloor.displayPageContent();
								viewSeatPlanByLocationFloor.askUserInput();
								viewSeatPlanByLocationFloor.displayList(subPageChoice);

								logger.info(GenericConstants.END + ": ViewSeatPlanByLocationFloor Page");
								break;
							case GenericConstants.VIEW_SEAT_PLAN_BY_QUADRANT:
								isGoBack = false;
								logger.info(GenericConstants.START + ": ViewSeatPlanByQuadrant Page");

								ViewSeatPlanByQuadrant viewSeatPlanByQuadrant = new ViewSeatPlanByQuadrant(user,
										userInput);
								viewSeatPlanByQuadrant.displayPageHeader();
								viewSeatPlanByQuadrant.displayPageContent();
								viewSeatPlanByQuadrant.askUserInput();
								viewSeatPlanByQuadrant.displayList(subPageChoice);

								logger.info(GenericConstants.END + ": ViewSeatPlanByQuadrant Page");
								break;
							case GenericConstants.GO_BACK:
								isGoBack = true;
								break;
							}

							logger.debug("isGoBack: " + isGoBack + ", subPageChoice" + subPageChoice);
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