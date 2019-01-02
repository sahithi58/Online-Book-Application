package com.sapestore.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Miscelleneous;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for fetching ContactUs and Policy static content. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
@Repository
public class PagesDao {

	/**
	 * Logger for log messages
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(PagesDao.class.getName());
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * Method to fetch ContactUs content from database
	 * @return contactUsText
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public String getContactUs() throws SapeStoreException {
		LOGGER.debug(" PagesDao.getContactUs method: START ");
		List<Miscelleneous> miscelleneousList = null;
		String contactUsText = "";
		try{		
			miscelleneousList = (List<Miscelleneous>) hibernateTemplate.findByNamedQuery("Miscelleneous.findAll");	
			if (!miscelleneousList.isEmpty()){
				contactUsText =  miscelleneousList.get(0).getContactUs();
			}			
		} 
		catch (SapeStoreSystemException dbe) {
			LOGGER.fatal("A DB exception occured while getting the contact us details", dbe);
			throw dbe;
		}
		LOGGER.debug(" PagesDao.getContactUs method: END");
		return contactUsText;
	}

	/**
	 * Method to fetch Policy content from database
	 * @return policyText
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public String getPolicy() throws SapeStoreException {		
		LOGGER.debug(" PagesDao.getPolicy method: START ");
		List<Miscelleneous> miscelleneousList = null;
		String policyText = "";
		try{		
			miscelleneousList = (List<Miscelleneous>) hibernateTemplate.findByNamedQuery("Miscelleneous.findAll");	
			if (!miscelleneousList.isEmpty()){
				policyText =  miscelleneousList.get(0).getPolicy();
			}			
		} 
		catch (SapeStoreSystemException dbe) {
			LOGGER.fatal("A DB exception occured while getting the policy details", dbe);
			throw dbe;
		}
		LOGGER.debug(" PagesDao.getPolicy method: END");
		return policyText;
	}
	
	/**
	 * Method to set ContactUs content in database in admin.
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public void setContactUs(String text) throws SapeStoreException {
		LOGGER.debug(" PagesDao.setContactUs method: START ");
		
		try{
			List<Miscelleneous> miscelleneousList = null;
			Miscelleneous miscelleneous = null;
			miscelleneousList = (List<Miscelleneous>) hibernateTemplate.findByNamedQuery("Miscelleneous.findAll");	
			
			if(miscelleneousList.isEmpty()){
				miscelleneous = new Miscelleneous();
				miscelleneous.setContactUs(text);
				hibernateTemplate.save(miscelleneous);
			} 
			else{
				miscelleneous = miscelleneousList.get(0);
				miscelleneous.setContactUs(text);
				hibernateTemplate.update(miscelleneous);
			}			
		}
		catch (SapeStoreSystemException dbe) {
			LOGGER.fatal("A DB exception occured while saving the contact us details", dbe);
			throw dbe;
		}
		LOGGER.debug(" PagesDao.setContactUs method: END ");
	}

	/**
	 * Method to set Policy content in database in admin.
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public void setPolicy(String text) throws SapeStoreException {		
		LOGGER.debug(" PagesDao.setPolicy method: START ");		
		try{
			List<Miscelleneous> miscelleneousList = null;
			Miscelleneous miscelleneous = null;
			miscelleneousList = (List<Miscelleneous>) hibernateTemplate.findByNamedQuery("Miscelleneous.findAll");	
			
			if(miscelleneousList.isEmpty()){
				miscelleneous = new Miscelleneous();
				miscelleneous.setPolicy(text);
				hibernateTemplate.save(miscelleneous);
			} 
			else{
				miscelleneous = miscelleneousList.get(0);
				miscelleneous.setPolicy(text);
				hibernateTemplate.update(miscelleneous);
			}			
		}
		catch (SapeStoreSystemException dbe) {
			LOGGER.fatal("A DB exception occured while saving the contact us details", dbe);
			throw dbe;
		}
		LOGGER.debug(" PagesDao.setContactUs method: END");
	}
	
}
