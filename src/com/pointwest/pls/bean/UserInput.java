package com.pointwest.pls.bean;

public class UserInput extends Employee {
	private int loginTries = 3;
	private String homePageChoice;
	private String subPageChoice;
	private String searchByEmployeeIdInput;
	private String searchByEmployeeNameInput;
	private String searchByEmployeeProjectInput;
	private String viewByLocationInput;
	private String viewByFloorInput;
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

	public String getSubPageChoice() {
		return subPageChoice;
	}

	public void setSubPageChoice(String subPageChoice) {
		this.subPageChoice = subPageChoice;
	}

	public String getSearchByEmployeeIdInput() {
		return searchByEmployeeIdInput;
	}

	public void setSearchByEmployeeIdInput(String searchByEmployeeIdInput) {
		this.searchByEmployeeIdInput = searchByEmployeeIdInput;
	}

	public String getSearchByEmployeeNameInput() {
		return searchByEmployeeNameInput;
	}

	public void setSearchByEmployeeNameInput(String searchByEmployeeNameInput) {
		this.searchByEmployeeNameInput = searchByEmployeeNameInput;
	}

	public String getSearchByEmployeeProjectInput() {
		return searchByEmployeeProjectInput;
	}

	public void setSearchByEmployeeProjectInput(String searchByEmployeeProjectInput) {
		this.searchByEmployeeProjectInput = searchByEmployeeProjectInput;
	}

	public String getViewByLocationInput() {
		return viewByLocationInput;
	}

	public void setViewByLocationInput(String viewByLocationInput) {
		this.viewByLocationInput = viewByLocationInput;
	}

	public String getViewByFloorInput() {
		return viewByFloorInput;
	}

	public void setViewByFloorInput(String viewByFloorInput) {
		this.viewByFloorInput = viewByFloorInput;
	}

	public String getViewByQuadrantInput() {
		return viewByQuadrantInput;
	}

	public void setViewByQuadrantInput(String viewByQuadrantInput) {
		this.viewByQuadrantInput = viewByQuadrantInput;
	}
}