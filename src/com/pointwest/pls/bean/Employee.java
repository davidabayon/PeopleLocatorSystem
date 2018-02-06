package com.pointwest.pls.bean;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String employeeId;
	private String employeeUsername;
	private String employeePassword;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeRole;
	private String employeeShift;
	private List<Project> employeeProject;
	private List<Seat> employeeSeat;

	public Employee() {
		this.employeeProject = new ArrayList<Project>();
		this.employeeSeat = new ArrayList<Seat>();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeUsername() {
		return employeeUsername;
	}

	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public String getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmployeeShift() {
		return employeeShift;
	}

	public void setEmployeeShift(String employeeShift) {
		this.employeeShift = employeeShift;
	}

	public List<Project> getEmployeeProject() {
		return employeeProject;
	}

	public List<Seat> getEmployeeSeat() {
		return employeeSeat;
	}
}