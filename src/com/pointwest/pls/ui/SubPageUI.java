package com.pointwest.pls.ui;

import java.util.List;
import java.util.Scanner;

import com.pointwest.pls.bean.Employee;
import com.pointwest.pls.bean.User;

public abstract class SubPageUI implements PageUI {
	protected Scanner scanner = new Scanner(System.in);
	protected User user = null;
	protected List<Employee> employees = null;

	public abstract void displayList(String subPageChoice);
}