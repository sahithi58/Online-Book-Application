package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;

/**
 * Service interface for editing static content like Contacts Us and Privacy Policy.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

public interface PageService {

	/**
	 * Returns Contact Us text
	 * @return
	 * @throws SapeStoreSystemException
	 */
	String getContactUs() throws SapeStoreException;

	/**
	 * Sets new Contact Us text
	 * @param contactText
	 * @throws SapeStoreSystemException
	 */
	void setContactUs(String contactText) throws SapeStoreException;

	
	/**
	 * Returns Privacy policy text
	 * @return
	 * @throws SapeStoreSystemException
	 */
	String getPolicy() throws SapeStoreException;

	
	/**
	 * Sets new Privacy Policy text
	 * @param policyText
	 * @throws SapeStoreSystemException
	 */
	void setPolicy(String policyText) throws SapeStoreException;

}
