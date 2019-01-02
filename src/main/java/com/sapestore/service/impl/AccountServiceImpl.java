package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.AccountDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;

/**
 * Service class for user login functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(AccountServiceImpl.class.getName());
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public User authenticate(User userLogin) throws SapeStoreException {
		LOGGER.debug("authenticate method: START");
	    User user = accountDao.getUserDetails(userLogin.getUserId(), userLogin.getPassword());
		LOGGER.debug("authenticate method: END");
		return user;
	}

}
