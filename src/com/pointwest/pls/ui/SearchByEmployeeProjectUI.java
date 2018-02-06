package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;

public class SearchByEmployeeProjectUI extends SearchPageUI implements PageUI {
	Logger logger = Logger.getLogger(SearchByEmployeeProjectUI.class);

	public SearchByEmployeeProjectUI(User user) {
		super(user);
	}
}