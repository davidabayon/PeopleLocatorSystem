package com.pointwest.pls.bean;

public class Employee {
	private String employeeId;
	private String employeeUsername;
	private String employeePassword;
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeRole;
	private String employeeShift;
	private Project employeeProject;
	private Seat employeeSeat;

	public Employee() {
		this.employeeProject = new Project();
		this.employeeSeat = new Seat();
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

	public Project getEmployeeProject() {
		return employeeProject;
	}

	public void setEmployeeProject(Project employeeProject) {
		this.employeeProject = employeeProject;
	}

	public Seat getEmployeeSeat() {
		return employeeSeat;
	}

	public void setEmployeeSeat(Seat employeeSeat) {
		this.employeeSeat = employeeSeat;
	}
}