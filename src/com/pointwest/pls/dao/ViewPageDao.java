package com.pointwest.pls.dao;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;

public class ViewPageDao extends BaseDao {
	Logger logger = Logger.getLogger(LoginPageDao.class);
	User user = null;

	public ViewPageDao(User user) {
		this.user = user;
	}
}