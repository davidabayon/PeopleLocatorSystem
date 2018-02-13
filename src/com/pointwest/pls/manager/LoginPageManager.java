package com.pointwest.pls.manager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.pointwest.pls.bean.User;
import com.pointwest.pls.constant.GenericConstants;
import com.pointwest.pls.dao.LoginPageDao;
import com.pointwest.pls.util.CustomException;

public class LoginPageManager {
	Logger logger = Logger.getLogger(LoginPageManager.class);
	LoginPageDao loginPageDao = null;
	User user = null;

	public LoginPageManager(User user) {
		this.user = user;
		this.loginPageDao = new LoginPageDao(user);
	}

	// Encrpyt user's password
	public String encryptUserPassword(String password) throws CustomException {
		logger.info(GenericConstants.START);

		boolean isEncrpyted = false;

		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(password.getBytes());
			byte[] bytes = messageDigest.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for (byte b : bytes) {
				stringBuffer.append(String.format("%02x", b & 0xff));
			}

			isEncrpyted = true;
			password = stringBuffer.toString();
		} catch (NoSuchAlgorithmException e) {
			isEncrpyted = false;

			CustomException customException = new CustomException(GenericConstants.IO_EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		} catch (Exception e) {
			isEncrpyted = false;

			CustomException customException = new CustomException(GenericConstants.EXCEPTION, e);
			logger.debug(e.getMessage());
			logger.error(customException.getMessage());
			throw customException;
		}

		logger.debug("isEncrpyted: " + isEncrpyted);
		logger.info(GenericConstants.END);
		return password;
	}

	// Check if login credentials has a match
	public boolean checkMatchingLoginCredentials(String password) throws CustomException {
		logger.info(GenericConstants.START);

		boolean hasMatched = false;
		hasMatched = loginPageDao.retrieveUserDetails(password);

		logger.debug("hasMatched: " + hasMatched);
		logger.info(GenericConstants.END);
		return hasMatched;
	}
}