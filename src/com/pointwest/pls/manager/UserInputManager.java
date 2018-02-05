package com.pointwest.pls.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.util.CustomException;
import com.pointwest.pls.util.EraserThread;

public class UserInputManager {
	static Logger logger = Logger.getLogger(UserInputManager.class);
	User user = null;

	public UserInputManager(User user) {
		this.user = user;
	}

	public boolean validateInputIfNull(String input, String message) {
		logger.info(GenericConstants.START);
		if ("".trim().equalsIgnoreCase(input.trim())) {
			logger.debug(message);
			logger.info(GenericConstants.END);
			return true;
		} else {
			logger.info(GenericConstants.END);
			return false;
		}
	}

	public String readPassword(String prompt) throws CustomException {
		EraserThread eraserThread = new EraserThread(prompt);
		Thread mask = new Thread(eraserThread);
		mask.start();

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String password = null;

		try {
			password = in.readLine();
		} catch (IOException e) {
			CustomException customException = new CustomException(GenericConstants.IO_EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (Exception e) {
			CustomException customException = new CustomException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		}

		eraserThread.stopMasking();
		return password;
	}
}