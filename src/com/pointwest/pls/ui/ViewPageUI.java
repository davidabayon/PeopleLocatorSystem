package com.pointwest.pls.ui;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.manager.ViewPageManager;

public class ViewPageUI extends SubPageUI {
	Logger logger = Logger.getLogger(SearchPageUI.class);
	ViewPageManager viewPageManager = null;

	public ViewPageUI(User user) {
		this.user = user;
		this.viewPageManager = new ViewPageManager(this.user);
	}

	@Override
	public void displayPageHeader() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayPageContent() {
		// TODO Auto-generated method stub

	}

	@Override
	public void askUserInput() {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayList(String subPageChoice) {
		// TODO Auto-generated method stub

	}
}