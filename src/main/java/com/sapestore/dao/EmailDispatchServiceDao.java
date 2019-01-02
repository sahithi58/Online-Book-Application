package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class EmailDispatchServiceDao {
	@Autowired
	private SessionFactory factory;

	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public List<Long> getAllOrderId(List<BigDecimal> orderItemId) {
		// TODO Auto-generated method stub
		
		ArrayList<Long> orderId=new ArrayList<Long>();
		HashSet<Long> orderIdSet=new HashSet<Long>();
		
		for(BigDecimal id:orderItemId){
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String queryString = "select o.orderId from OrderItemInfo o where o.orderItemId like:orderItemId";
		Query q = session.createQuery(queryString);
		q.setBigDecimal("orderItemId",id);
		
		@SuppressWarnings("unchecked")
		Long retrieveId= (Long) q.uniqueResult();
	    System.out.println(retrieveId);
	    
	    if(!orderIdSet.contains(retrieveId)){
	    	orderId.add(retrieveId);
	    	orderIdSet.add(retrieveId);
	    }
	
		t.commit();
		session.close();
		
		}
		
		System.out.println("orderId list="+orderId);
		
		return orderId;

	}

	public List<String> getAllEmailId(List<String> userId) {
		// TODO Auto-generated method stub
		
		ArrayList<String> emailId=new ArrayList<String>();
		HashSet<String> emailIdSet=new HashSet<String>();
		for(String id:userId){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String query = "select u.emailAddress from User u where u.userId like :userId";
		Query q = session.createQuery(query);
		q.setString("userId", id);

		String emailAddress = (String) q.uniqueResult();
		
		  
	    if(!emailIdSet.contains(emailAddress)){
	    	emailId.add(emailAddress);
	    	emailIdSet.add(emailAddress);
	    }
		
		t.commit();
		session.close();
		
		

		}
		
		System.out.println("emailidSet="+emailId);
		
		return emailId;
	}

	public List<String> getAllUserId(List<Long> orderId) {
		// TODO Auto-generated method stub
		
		ArrayList<String> userId=new ArrayList<String>();
		HashSet<String> userIdSet=new HashSet<String>();
		
		for(Long id:orderId){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String queryString = "select o.userId from OrderInfo o where o.orderId like:orderId";
		Query q = session.createQuery(queryString);
        q.setLong("orderId", id);
		@SuppressWarnings("unchecked")
		String userName = (String) q.uniqueResult();
	    System.out.println(userName);
	    
	    if(!userIdSet.contains(userName)){
	    	userId.add(userName);
	    	userIdSet.add(userName);
	    }
	    
	    
		t.commit();

		 session.close();
		 
		}
		
		System.out.println("UserIdlist="+userId);
		
		return userId;
	}

}
