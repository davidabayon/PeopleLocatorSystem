package com.pointwest.pls.bean;

public class User extends Employee {
	private int loginTries = 3;
	private String homePageChoice;
	private String searchPageChoice;
	private String searchByEmployeeIdInput;
	private String searchByEmployeeFirstNameInput;
	private String searchByEmployeeLastNameInput;
	private String searchByEmployeeProjectInput;
	private String viewPageChoice;
	private String viewByLocationFloorInput;
	private String viewByQuadrantInput;

	public int getLoginTries() {
		return loginTries;
	}

	public void setLoginTries(int loginTries) {
		this.loginTries = loginTries;
	}

	public String getHomePageChoice() {
		return homePageChoice;
	}

	public void setHomePageChoice(String homePageChoice) {
		this.homePageChoice = homePageChoice;
	}

	public String getSearchPageChoice() {
		return searchPageChoice;
	}

	public void setSearchPageChoice(String searchPageChoice) {
		this.searchPageChoice = searchPageChoice;
	}

	public String getSearchByEmployeeIdInput() {
		return searchByEmployeeIdInput;
	}

	public void setSearchByEmployeeIdInput(String searchByEmployeeIdInput) {
		this.searchByEmployeeIdInput = searchByEmployeeIdInput;
	}

	public String getSearchByEmployeeFirstNameInput() {
		return searchByEmployeeFirstNameInput;
	}

	public void setSearchByEmployeeFirstNameInput(String searchByEmployeeFirstNameInput) {
		this.searchByEmployeeFirstNameInput = searchByEmployeeFirstNameInput;
	}

	public String getSearchByEmployeeLastNameInput() {
		return searchByEmployeeLastNameInput;
	}

	public void setSearchByEmployeeLastNameInput(String searchByEmployeeLastNameInput) {
		this.searchByEmployeeLastNameInput = searchByEmployeeLastNameInput;
	}

	public String getSearchByEmployeeProjectInput() {
		return searchByEmployeeProjectInput;
	}

	public void setSearchByEmployeeProjectInput(String searchByEmployeeProjectInput) {
		this.searchByEmployeeProjectInput = searchByEmployeeProjectInput;
	}

	public String getViewPageChoice() {
		return viewPageChoice;
	}

	public void setViewPageChoice(String viewPageChoice) {
		this.viewPageChoice = viewPageChoice;
	}

	public String getViewByLocationFloorInput() {
		return viewByLocationFloorInput;
	}

	public void setViewByLocationFloorInput(String viewByLocationFloorInput) {
		this.viewByLocationFloorInput = viewByLocationFloorInput;
	}

	public String getViewByQuadrantInput() {
		return viewByQuadrantInput;
	}

	public void setViewByQuadrantInput(String viewByQuadrantInput) {
		this.viewByQuadrantInput = viewByQuadrantInput;
	}
}