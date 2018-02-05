package com.pointwest.pls.bean;

import java.util.Objects;

import com.pointwest.pls.constant.GenericConstants;

public class User {
	private String username;
	private String password;
	private String homePageChoice;
	private String searchPageChoice;
	private String searchByEmployeeIdInput;
	private String searchByEmployeeFirstNameInput;
	private String searchByEmployeeLastNameInput;
	private String searchByEmployeeProjectInput;
	private String viewPageChoice;
	private String viewByLocationFloorInput;
	private String viewByQuadrantInput;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws NullPointerException {
		this.username = Objects.requireNonNull(username, GenericConstants.INPUT_LOGIN_NULL);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws NullPointerException {
		this.password = Objects.requireNonNull(password, GenericConstants.INPUT_LOGIN_NULL);
	}

	public String getHomePageChoice() {
		return homePageChoice;
	}

	public void setHomePageChoice(String homePageChoice) throws NullPointerException {
		this.homePageChoice = Objects.requireNonNull(homePageChoice, GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getSearchPageChoice() {
		return searchPageChoice;
	}

	public void setSearchPageChoice(String searchPageChoice) throws NullPointerException {
		this.searchPageChoice = Objects.requireNonNull(searchPageChoice, GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getSearchByEmployeeIdInput() {
		return searchByEmployeeIdInput;
	}

	public void setSearchByEmployeeIdInput(String searchByEmployeeIdInput) throws NullPointerException {
		this.searchByEmployeeIdInput = Objects.requireNonNull(searchByEmployeeIdInput,
				GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getSearchByEmployeeFirstNameInput() {
		return searchByEmployeeFirstNameInput;
	}

	public void setSearchByEmployeeFirstNameInput(String searchByEmployeeFirstNameInput) throws NullPointerException {
		this.searchByEmployeeFirstNameInput = Objects.requireNonNull(searchByEmployeeFirstNameInput,
				GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getSearchByEmployeeLastNameInput() {
		return searchByEmployeeLastNameInput;
	}

	public void setSearchByEmployeeLastNameInput(String searchByEmployeeLastNameInput) throws NullPointerException {
		this.searchByEmployeeLastNameInput = Objects.requireNonNull(searchByEmployeeLastNameInput,
				GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getSearchByEmployeeProjectInput() {
		return searchByEmployeeProjectInput;
	}

	public void setSearchByEmployeeProjectInput(String searchByEmployeeProjectInput) throws NullPointerException {
		this.searchByEmployeeProjectInput = Objects.requireNonNull(searchByEmployeeProjectInput,
				GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getViewPageChoice() {
		return viewPageChoice;
	}

	public void setViewPageChoice(String viewPageChoice) throws NullPointerException {
		this.viewPageChoice = Objects.requireNonNull(viewPageChoice, GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getViewByLocationFloorInput() {
		return viewByLocationFloorInput;
	}

	public void setViewByLocationFloorInput(String viewByLocationFloorInput) throws NullPointerException {
		this.viewByLocationFloorInput = Objects.requireNonNull(viewByLocationFloorInput,
				GenericConstants.INPUT_CHOICE_NULL);
	}

	public String getViewByQuadrantInput() {
		return viewByQuadrantInput;
	}

	public void setViewByQuadrantInput(String viewByQuadrantInput) throws NullPointerException {
		this.viewByQuadrantInput = Objects.requireNonNull(viewByQuadrantInput, GenericConstants.INPUT_CHOICE_NULL);
	}
}