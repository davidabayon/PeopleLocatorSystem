package com.pointwest.pls.manager;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.dao.ViewPageDao;

public class ViewPageManager {
	Logger logger = Logger.getLogger(LoginPageManager.class);
	ViewPageDao viewPageDao = null;
	User user = null;

	public ViewPageManager(User user) {
		this.user = user;
		this.viewPageDao = new ViewPageDao(user);
	}
}