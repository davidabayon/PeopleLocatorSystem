package com.pointwest.pls.manager;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.dao.LoginPageDao;
import com.pointwest.pls.util.CustomException;

public class LoginPageManager {
	Logger logger = Logger.getLogger(LoginPageManager.class);
	User user = null;

	public LoginPageManager(User user) {
		this.user = user;
	}

	public boolean validateLoginCredentials() throws CustomException {
		boolean isValid = false;
		LoginPageDao loginPageDao = new LoginPageDao(user);
		loginPageDao.retrieveLoginCredentials();
		return isValid;
	}
}