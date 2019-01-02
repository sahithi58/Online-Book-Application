package com.sapestore.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("emailAddressRetrieve")
public class EmailAddressRetrieveDao {

	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public String retrieveMailId(String userId) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String query = "select u.emailAddress from User u where u.userId like :userId";
		Query q = session.createQuery(query);
		q.setString("userId", userId);

		String emailAddress = (String) q.uniqueResult();
		t.commit();
		session.close();

		return emailAddress;
	}

}
