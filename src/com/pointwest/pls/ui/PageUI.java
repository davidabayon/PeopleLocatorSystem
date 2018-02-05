package com.pointwest.pls.ui;

import com.pointwest.pls.bean.User;

public interface PageUI {
	public void displayPageHeader();

	public void displayPageContent();

	public User askUserInput();
}