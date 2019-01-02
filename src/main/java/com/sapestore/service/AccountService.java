package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.User;

/**
 * Service interface for user login functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

public interface AccountService {

	/**
	 * Performs user login authentication.
	 * @param userLogin
	 * @return
	 * @throws SapeStoreSystemException
	 */
	User authenticate(User user) throws SapeStoreException;
	

}
