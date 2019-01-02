package com.sapestore.dao;




import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.ShippingAddress;
import com.sapestore.hibernate.entity.State;

import com.sapestore.vo.MemberAddress;
import com.sapestore.vo.ShippingAddressVO;

@Repository
public class ShippingAddressDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory factory;

	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	

	public MemberAddress retriveShippingAddress(String userid) {
	
		
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		String queryString = "from Address a where a.userId like:userId";
		Query q = session.createQuery(queryString);
		q.setString("userId",userid);
		List<Address> addressList = q.list();
		Address address=addressList.get(0);
		  MemberAddress memberaddress = new MemberAddress();
		  memberaddress.setAddressLine1(address.getAddressLine1());
		  memberaddress.setAddressLine2(address.getAddressLine2());
		  memberaddress.setMobileNumber(Long.toString(address.getMobileNumber()));
		  memberaddress.setCityId(address.getCityId());
		  memberaddress.setStateId(address.getStateId());
		  memberaddress.setPostalCode(address.getPostalCode());
		  
		  t.commit();
		  session.close();
		return memberaddress;
		  
		
	}
	
/*	public void addOrder(String userId){
		//System.out.println("inside add order");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		OrderInfo order = new OrderInfo();
		//dummy value,manually increment each time app runs
		System.out.println("inside addorder");
		//order.setOrderId(orderId);
		order.setUserId(userId);
		oid=(Integer)session.save(order);
		System.out.println("order id added to order table");
		t.commit();
		session.close();
		//System.out.println("exiting");
	}
*/
	public void addShippingAddress(ShippingAddressVO shippingaddress) {
		// TODO Auto-generated method stub
		System.out.println("inside addshipping address");
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		ShippingAddress shipAddress = new ShippingAddress();
		shipAddress.setCustomerName(shippingaddress.getName());
		shipAddress.setAddressLine1(shippingaddress.getAddressLine1());
		shipAddress.setAddressLine2(shippingaddress.getAddressLine2());
		shipAddress.setCityId(shippingaddress.getCityId());
		shipAddress.setStateId(shippingaddress.getStateId());
		shipAddress.setZipCode(shippingaddress.getZipCode());
		shipAddress.setOrderId(shippingaddress.getOrderId());
		shipAddress.setIsActive("y");
		/*shipAddress.set*/
		
		Random rn=new Random();
		int range= 55555-11111+1;
		int randomnum=rn.nextInt(range)+11111;
		
		shipAddress.setAddressId(randomnum);
		
        /*shipAddress.setOrderId(oid);*/
      
      
        System.out.println(shipAddress);
        
		session.save(shipAddress);
		transaction.commit();
		session.close();
		System.out.println("exiting addshipping address");
	}
	public ArrayList<City> getAllCityId() {

		
		@SuppressWarnings("unchecked")
		ArrayList<City> cities = (ArrayList<City>) hibernateTemplate.find("from City");
		System.out.println(cities);
		return cities;

		
	}
	public ArrayList<State> getAllStateId() {
	
		
		@SuppressWarnings("unchecked")
		ArrayList<State> states = (ArrayList<State>) hibernateTemplate.find("from State");
		System.out.println(states);
		return states;
	}

	
	
	public String getCityName(Integer cityId) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String query = "select c.cityName from City c where c.cityId like:cityId";
		Query q=session.createQuery(query);
		q.setInteger("cityId",cityId);

		String cityName = (String) q.uniqueResult();
		t.commit();
		session.close();
		return cityName;
	}

	public String getStateName(Integer stateId) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String query = "select s.stateName from State s where s.stateId like:stateId";
		Query q=session.createQuery(query);
		q.setInteger("stateId",stateId);

		String stateName = (String) q.uniqueResult();
		t.commit();
		session.close();
		return stateName;
	}

	/*public List<Integer> getOrderId(String userId) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		String queryString = "select o.orderId from OrderInfo o where o.userId like:userId";
		Query q = session.createQuery(queryString);
		q.setString("userId",userId);
		@SuppressWarnings("unchecked")
		List<Integer> orderidList = q.list();
		//System.out.println(orderidList);
		t.commit();
		//System.out.println("after commit");
		return orderidList;
	}*/

	public String getUserName(String userId) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String query = "select u.name from User u where u.userId like:userId";
		Query q = session.createQuery(query);
		q.setString("userId",userId);
		String userName = (String) q.uniqueResult();
		//System.out.println(userName);
		t.commit();
		session.close();
		return userName;
	}



}
