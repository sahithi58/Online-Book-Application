package com.sapestore.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.User;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class to fetch user's login details
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */
@Repository
public class AccountDao {

	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(AccountDao.class.getName());
	
@Autowired
	private SessionFactory factory;
	
	
	
	public SessionFactory getFactory() {
		return factory;
	}


	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}


	
	/**
	 * Method to fetch user login details based on the parameters provided.
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public User getUserDetails(String userId, String password) throws SapeStoreException{		
		LOGGER.debug("");
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
	
		String queryString = " from User u where u.userId= :uid and u.password=:pswd";
		Query q = session.createQuery(queryString);
		q.setString("uid", userId);
		q.setParameter("pswd", password);
		User temp1 = (User) q.uniqueResult();
		session.close();
		return temp1;
		
	
		
	}}

