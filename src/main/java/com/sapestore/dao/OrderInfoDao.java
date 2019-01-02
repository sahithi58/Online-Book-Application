package com.sapestore.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.vo.OrderInfoVO;

@Repository
public class OrderInfoDao {
	@Autowired
	private SessionFactory factory;
	
	public SessionFactory getFactory() {
			return factory;
		}
	public void setFactory(SessionFactory factory) {
			this.factory = factory;
	} 
	public Long addOrder(OrderInfoVO orderInfoVo){
		//System.out.println("inside add order");
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		OrderInfo order = new OrderInfo();
		//dummy value,manually increment each time app runs
		System.out.println("inside addorder");
		order.setUserId(orderInfoVo.getUserId());
		
		//order.setOrderId(orderId);
		
		Long oid=(Long)session.save(order);
		System.out.println("order id added to order table"+oid);
		t.commit();
		session.close();
		return oid;
		//System.out.println("exiting");
	}

}
