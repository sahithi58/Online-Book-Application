package com.sapestore.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.OrderItemInfoVO;
@Repository
public class OrderItemInfoDao {

	
	@Autowired
	private SessionFactory factory;
	
	public SessionFactory getFactory() {
			return factory;
		}
	public void setFactory(SessionFactory factory) {
			this.factory = factory;
	} 
	
	public  BigDecimal addOrderItemInfo(OrderItemInfoVO orderItemInfoVO){
		//System.out.println("inside add order");
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		OrderItemInfo orderitem = new OrderItemInfo();
		//dummy value,manually increment each time app runs
		System.out.println("inside addorder item info");
		//order.setOrderId(orderId);
		orderitem.setOrderId(orderItemInfoVO.getOrderId());
		orderitem.setIsbn(orderItemInfoVO.getIsbn());
		orderitem.setBookPrice(Integer.parseInt(orderItemInfoVO.getBookPrice()));
		orderitem.setOrderQuantity(orderItemInfoVO.getOrderQuantity());
		//orderitem.setIsActive(orderItemInfoVO.getIsActive());
		//orderitem.setOrderStatus(orderItemInfoVO.getOrderStatus());
		//orderitem.setPaymentStatus(orderItemInfoVO.getPaymentStatus());
		
		
           
           // Date actual=fmt.parse(orderItemInfo.getActualReturnDate());
           // long diffInMilis=actual.getTime()-expected.getTime();
          //  long diff=diffInMilis/(24*60*60*1000);
            //int f=(int) (diff*orderItemInfo.getLateFee().doubleValue());
          //  report.setLateFee(f);
    
        //  System.out.println("parsed "+expected);
		
		
		System.out.println("return date"+orderItemInfoVO.getReturnDate());
		//orderitem.setIsActive(orderItemInfoVO.getIsActive());
		//orderitem.setOrderStatus(orderItemInfoVO.getOrderStatus());
		//orderitem.setPaymentStatus(orderItemInfoVO.getPaymentStatus());
		if(orderItemInfoVO.isRented()=="y"){
			/*orderitem.setExpectedReturnDate(orderItemInfoVO.getReturnDate());*/
			orderitem.setPurchaseType("RENTED");
			 final DateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
	            Date expected = null;
				try {
					expected = fmt.parse(orderItemInfoVO.getReturnDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				orderitem.setExpectedReturnDate(expected);
				
		}else
		{
			orderitem.setPurchaseType("PURCHASED");
		} 
		
		
		
		BigDecimal orderItemId=(BigDecimal)session.save(orderitem);
		System.out.println("order id added to order table"+orderItemId);
		t.commit();
		session.close();
		return orderItemId;
}
}
