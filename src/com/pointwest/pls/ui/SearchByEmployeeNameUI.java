package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;

public class SearchByEmployeeNameUI extends SearchPageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeNameUI.class);

	public SearchByEmployeeNameUI(User user) {
		super(user);
	}
}