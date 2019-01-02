package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.sapestore.vo.OrderStatusVO;


@Repository
public class OrderStatusDao {

	
	 @Autowired private HibernateTemplate hibernateTemplate;
	 

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderDao.class.getName());

	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public String getOrderStatus(Integer orderId,String userId) throws SapeStoreException {
		LOGGER.debug("comingf inside the dao class");
		System.out.println("inside dao");
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();

		String returnStatus = null;
		System.out.println("in dao");
		System.out.println("ORDER ID IS COMINH ITLL TYHE DAO   " + orderId);
		Query query = session.createQuery("select orderStatus from OrderInfo o where o.orderId =:oid and o.userId=:uid");
		query.setString("uid", userId);
		query.setInteger("oid", orderId);
System.out.println("In getStatus " +userId);
		String status = (String) query.uniqueResult();
		
		System.out.println("in dao");
		if (status.equalsIgnoreCase("dispatched")) {
			/*returnStatus = "Your Order "+orderId+" is Dispatched ";*/
			returnStatus = " is Dispatched ";

		} else if (status.equalsIgnoreCase("partially_dispatched")) {
			returnStatus = " is partially dispatched";

		} else if (status.equalsIgnoreCase("not_dispatched")) {
			returnStatus = " dispatch is in process";

		}

		transaction.commit();
		session.close();
		return returnStatus;

	}

	public boolean checkOrderId(Integer orderId,String userId) {
		boolean flag = false;
		LOGGER.debug("CHECKING THE ISBN");
		Session sesion=factory.openSession();
		Query query=sesion.createQuery("from OrderInfo where orderId =:oId and userId=:uid");
		query.setInteger("oId", orderId);
		query.setString("uid", userId);
		System.out.println("in checkId "+userId);
		System.out.println(query.list());
		if(query.list().isEmpty()){
			System.out.println("id is not existing");
			sesion.close();
			return false;
		}else{
		
			System.out.println("id exists");
			sesion.close();
			return true;

	}
	
}

	@SuppressWarnings("unchecked")
	public List<OrderStatusVO> getMyRentedOrder(Long orderId) throws SapeStoreException {
		LOGGER.debug("OrderStatusDao.getMyRentedOrder method: START");
		List<OrderStatusVO> myorderItemInfoList = new ArrayList<>();
		List<BigDecimal> mylist = null;
		Session session=factory.openSession();
		/*String hql = "select o.orderItemId from OrderItemInfo o where o.orderId="+orderId;
		Query query = session.createQuery(hql);
		mylist = query.list();
		*/
		/*for(BigDecimal itemid:mylist){*/
			String hql1 = "select  o.orderItemId,o.isbn,o.orderQuantity,o.purchaseType,o.expectedReturnDate,o.orderStatus from OrderItemInfo o where o.orderId="+orderId;
			Query query1 = session.createQuery(hql1);
			
		     List<Object[]> myitems= (List<Object[]>)query1.list();
		     for(Object[] item: myitems){
		    	 
		    	 BigDecimal orderitemid = (BigDecimal) item[0];
		         String isbn = (String)item[1];
		         String hql2 = "select  o.bookTitle from Book o where o.isbn='"+isbn+"'";
					Query query2 = session.createQuery(hql2);
					String bookTitle = (String) query2.uniqueResult();
		       Integer orderQuantity = (Integer)item[2];
		       String purchaseType=(String)item[3];      
			Date expectedReturnDate=(Date) item[4];
			String orderStatus =(String)item[5];
			System.out.println("order stauts in dao"+ orderStatus);
		       System.out.println("for orderitem id"+orderitemid);
			System.out.println(isbn);
			System.out.println(orderQuantity);
			System.out.println(purchaseType);
			OrderStatusVO o = new OrderStatusVO();
		o.setBookTitle(bookTitle);
		o.setPurchaseType(purchaseType);
		o.setOrderItemId(orderitemid);
		o.setOrderQuantity(orderQuantity);
		o.setExpectedReturnDate(expectedReturnDate);
	o.setOrderId(orderId);
	if(orderStatus.equals("false")){
		o.setOrderStatus("Not Dispatched");
	}
	else{
		o.setOrderStatus("Dispatched");
	}
		myorderItemInfoList.add(o);
			
		}

		     System.out.println("in my rented order dao"+ myorderItemInfoList);

		session.close();
		
		return myorderItemInfoList;
	}
	
	
	 public Date getDateWithOutTime(Date targetDate) { 	
	        Calendar newDate = Calendar.getInstance();
	        newDate.setLenient(false);
	        newDate.setTime(targetDate);
	        newDate.set(Calendar.HOUR_OF_DAY, 0);
	        newDate.set(Calendar.MINUTE,0);
	        newDate.set(Calendar.SECOND,0);
	        newDate.set(Calendar.MILLISECOND,0);
	        return newDate.getTime();
	    }

	
	    

}
