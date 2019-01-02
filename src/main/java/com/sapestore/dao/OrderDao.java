package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.DispatchSlip;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for order management module
 */
@Repository
@Transactional
public class OrderDao {
	@Autowired
	private SessionFactory factory;
	
	public SessionFactory getFactory() {
			return factory;
		}
	public void setFactory(SessionFactory factory) {
			this.factory = factory;
		} 
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
    
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderDao.class.getName());
	
	/**
	 * gets the rented status of the book
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<OrderItemInfo> getRentedOrders() throws SapeStoreException {
		LOGGER.debug("OrderDao getRentedOrders method : START");
		List<OrderItemInfo> orderItemInfoList = null;
		Session session=factory.openSession();
		String hql = "from OrderItemInfo o where o.purchaseType = 'RENTED' and o.returnStatus='false' order by o.orderItemId asc";
		Query query = session.createQuery(hql);
		orderItemInfoList = query.list();
		session.close();
		return orderItemInfoList;
	}	

	/**update dispatch update the status of dispatched books
	 * @param orgListDispatch
	 * @param orgListReturn
	 * @param newListDispatch
	 * @param newListReturn
	 * @param orderNums
	 * @return
	 */
	public List<Integer> updateDispatch(List<Boolean> orgListDispatch,
			List<Boolean> orgListReturn, List<Boolean> newListDispatch,
			List<Boolean> newListReturn, List<Integer> orderNums) {

		List<Integer> ordersToBeDispatched = null;
		List<Integer> ordersReturned = null;
		
		if(orderNums!=null && orderNums.size()>0){
			ordersToBeDispatched = new ArrayList<Integer>();
			ordersReturned = new ArrayList<Integer>();
			for (int i = 0; i < orderNums.size(); i++) {
				if (orgListDispatch.get(i) == false
						&& newListDispatch.get(i) == true) {
					ordersToBeDispatched.add(orderNums.get(i));
					if (newListReturn.get(i) == true) {
						ordersReturned.add(orderNums.get(i));
					}
				}
			}
		}
		return ordersToBeDispatched;
	}

	/**
	 * @param orgListDispatch
	 * @param orgListReturn
	 * @param newListDispatch
	 * @param newListReturn
	 * @param orderNums
	 */
	public void updateReturn(List<Boolean> orgListDispatch,	List<Boolean> orgListReturn, List<Boolean> newListDispatch,	
			List<Boolean> newListReturn, List<Integer> orderNums) {

		List<Integer> ordersReturned = null;		
		if(orderNums!=null && orderNums.size()>0)
		{
			ordersReturned = new ArrayList<Integer>();
			for (int i = 0; i < orderNums.size(); i++) {
				if (orgListDispatch.get(i) == true && newListReturn.get(i) == true) {
					ordersReturned.add(orderNums.get(i));
				}
			}
		}
		Date date = new Date();
		//String d = new SimpleDateFormat("dd-MMM-yy").format(date);

		/*
		 * Update return_status and actual_return_date for list of orders returned : ordersReturned
		 */
		OrderItemInfo orderItemInfo = new OrderItemInfo();
		for (Integer orderItemId : ordersReturned) {			
			orderItemInfo = hibernateTemplate.get(OrderItemInfo.class, orderItemId);
			orderItemInfo.setReturnStatus("RETURNED");		
			orderItemInfo.setActualReturnDate(date);
			hibernateTemplate.saveOrUpdate(orderItemInfo);
		}
	}
		
	/**
	 * This method sets dispatch slip.
	 * 
	 * @param list1
	 *            List of all the ordersIds to be dispatched
	 * @return List of all the orders to be dispatched
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<DispatchSlip> returnDispatchedSlips(List<BigDecimal> list1) throws SapeStoreException {
		LOGGER.debug("returnDispatchedSlips method: START");
		List<DispatchSlip> dispatchSlipBeans = new ArrayList<DispatchSlip>();

		try {
			Session session=factory.openSession();
			for (BigDecimal i : list1) {
				
				DispatchSlip dispatchSlipBean = new DispatchSlip();
				dispatchSlipBean.setOrderNumber(i);
				String hql= "select m.name from User m where m.userId=(select userId from OrderInfo where orderId= (select orderId from OrderItemInfo where orderItemId="
								+i+ ")) AND ROWNUM <= 1";
				Query query = session.createQuery(hql);
				String name=(String)query.uniqueResult();
				dispatchSlipBean.setCustomerName(name);
				String hql1 = "select a.addressLine1|| ' ' ||a.addressLine2|| ' ' ||c.cityName|| ' ' ||d.countryName as address from User m,"
						+ "Address a,City c,Country d where m.userId=(select userId from OrderInfo where orderId= (select orderId from OrderItemInfo where orderItemId="
						+i+ ")) AND a.cityId=c.cityId AND"
				+ " a.countryId=d.countryId AND ROWNUM <= 1";
				Query query1 = session.createQuery(hql1);
				String address=(String)query1.uniqueResult();
				dispatchSlipBean.setShippingAddress(address);
				dispatchSlipBeans.add(dispatchSlipBean);
				
				String hql3 = "UPDATE OrderItemInfo o set o.orderStatus = 'true' WHERE orderItemId = :orderItemId";
			Query query3 = session.createQuery(hql3);
			query3.setParameter("orderItemId",i);
			int result = query3.executeUpdate();
			

			/*String quer="UPDATE OrderInfo o set o.orderStatus='DISPATCHED' where (select count(orderItemId) where orderId= (select orderId from OrderItemInfo where orderItemId="
					+i+ "))= (select count(orderItemId) where orderId= (select orderId from OrderItemInfo where orderItemId="
					+i+ "and orderStatus='true' ) )";
			
			
			
			Query query2 = session.createQuery(quer);
			query2.executeUpdate();*/
			
		String hql4 = "select o.orderId from OrderItemInfo o where o.orderItemId="+i;
		Query query4 = session.createQuery(hql4);
		Long orderid =(Long)query4.uniqueResult();
		
		String hql5 = "select count(o.orderItemId) from OrderItemInfo o where o.orderId="+orderid;
		Query query5=session.createQuery(hql5);
		Long count = (Long)query5.uniqueResult();
		   
		String hql6 = "select count(o.orderItemId) from OrderItemInfo o where o.orderId="+orderid+" AND o.orderStatus='true'";
		Query query6=session.createQuery(hql6);
		Long count1 = (Long)query6.uniqueResult();
		
		if(count==count1){
			String quer="UPDATE OrderInfo o set o.orderStatus='DISPATCHED' where o.orderId="+orderid;
			Query query2 = session.createQuery(quer);
			query2.executeUpdate();
			
			
		}
		else{
			String quer="UPDATE OrderInfo o set o.orderStatus='PARTIALLY_DISPATCHED' where o.orderId="+orderid;
			Query query2 = session.createQuery(quer);
			query2.executeUpdate();
		}
		
		String hql7="select o.orderQuantity from OrderItemInfo o where o.orderItemId="+i;
		Query query7 = session.createQuery(hql7);
		Integer quantity =(Integer)query7.uniqueResult();
		System.out.println(quantity);
		
		String hql8="select o.isbn from OrderItemInfo o where o.orderItemId="+i;
		Query query8 = session.createQuery(hql8);
		String isbn =(String)query8.uniqueResult();
		System.out.println(isbn);
		String hql9="select o.quantity from Book o where o.isbn='"+isbn+"'";
		Query query9 = session.createQuery(hql9);
		Integer quantity1 =(Integer)query9.uniqueResult();
				System.out.println(query9.uniqueResult());
		
		Integer quantity2= quantity1-quantity;
		
		String quer="UPDATE Book b set b.quantity="+quantity2+" where b.isbn='"+isbn+"'";
		Query query2 = session.createQuery(quer);
		query2.executeUpdate(); 

		
			}
		
			session.close();
		} catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while getting the dispatch orders list", se);
		}
		LOGGER.debug("returnDispatchedSlips method: END");
		
		System.out.println("DISPATCH SLIP :"+dispatchSlipBeans);
		return dispatchSlipBeans;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrderItemInfo> getPurchasedOrders() throws SapeStoreException {
		LOGGER.debug("InventoryDao.getBookDetails method: START");
		List<OrderItemInfo> orderItemInfoList = null;
		Session session=factory.openSession();
		String hql = "from OrderItemInfo o where o.purchaseType = 'PURCHASED' and o.paymentStatus = 'false' order by o.orderId asc";
		Query query = session.createQuery(hql);
		orderItemInfoList = query.list();
		session.close();
		return orderItemInfoList;
	}
	public void updatePayment(List<OrderItemInfo> orderItemUpdatedList) {
		// TODO Auto-generated method stub
		
		Session session=factory.openSession();
		
		for (OrderItemInfo orderItemInfo : orderItemUpdatedList) {
			//System.out.println(orderItemInfo.getOrderId() +" "+orderItemInfo.getPaymentStatus());
			
				System.out.println(orderItemInfo.getOrderItemId());
		String hql = "UPDATE OrderItemInfo o set o.paymentStatus = 'true' WHERE orderItemId = :order_id and o.orderStatus = 'true'";
	Query query = session.createQuery(hql);
	query.setParameter("order_id", orderItemInfo.getOrderItemId());
	int result = query.executeUpdate();
	System.out.println("Rows affected: " + orderItemInfo.getOrderItemId());
			
		}
		session.close();
		
	}	
	
	public List<DispatchSlip> returnRentedDispatchedSlips(List<BigDecimal> list) {
		// TODO Auto-generated method stub
		LOGGER.debug("returnDispatchedSlips method: START");
		List<DispatchSlip> dispatchSlipBeans = new ArrayList<DispatchSlip>();

		try {
			Session session=factory.openSession();
			for (BigDecimal i : list) {
				System.out.println("dispatch items :"+i);
				DispatchSlip dispatchSlipBean = new DispatchSlip();
				dispatchSlipBean.setOrderNumber(i);
				String hql= "select m.name from User m where m.userId=(select userId from OrderInfo where orderId= (select orderId from OrderItemInfo where orderItemId="
								+i+ ")) AND ROWNUM <= 1";
				Query query = session.createQuery(hql);
				String name=(String)query.uniqueResult();
				dispatchSlipBean.setCustomerName(name);
				String hql1 = "select a.addressLine1|| ' ' ||a.addressLine2|| ' ' ||c.cityName|| ' ' ||d.countryName as address from User m,"
						+ "Address a,City c,Country d where m.userId=(select userId from OrderInfo where orderId= (select orderId from OrderItemInfo where orderItemId="
						+i+ ")) AND a.cityId=c.cityId AND"
				+ " a.countryId=d.countryId AND ROWNUM <= 1";
				Query query1 = session.createQuery(hql1);
				String address=(String)query1.uniqueResult();
				dispatchSlipBean.setShippingAddress(address);
				dispatchSlipBeans.add(dispatchSlipBean);
				
				String hql3 = "UPDATE OrderItemInfo o set o.orderStatus = 'true' WHERE orderItemId = :orderItemId";
			Query query3 = session.createQuery(hql3);
			query3.setParameter("orderItemId",i);
			int result = query3.executeUpdate();
			
			
		String hql4 = "select o.orderId from OrderItemInfo o where o.orderItemId="+i;
		Query query4 = session.createQuery(hql4);
		Long orderid =(Long)query4.uniqueResult();
		
		String hql5 = "select count(o.orderItemId) from OrderItemInfo o where o.orderId="+orderid;
		Query query5=session.createQuery(hql5);
		Long count = (Long)query5.uniqueResult();
		   
		String hql6 = "select count(o.orderItemId) from OrderItemInfo o where o.orderId="+orderid+" AND o.orderStatus='true'";
		Query query6=session.createQuery(hql6);
		Long count1 = (Long)query6.uniqueResult();
		if(count==count1){
			String quer="UPDATE OrderInfo o set o.orderStatus='DISPATCHED' where o.orderId="+orderid;
			Query query2 = session.createQuery(quer);
			query2.executeUpdate();
		}
		else{
			String quer="UPDATE OrderInfo o set o.orderStatus='PARTIALLY_DISPATCHED' where o.orderId="+orderid;
			Query query2 = session.createQuery(quer);
			query2.executeUpdate();
		}
		
		String hql7="select o.orderQuantity from OrderItemInfo o where o.orderItemId="+i;
		Query query7 = session.createQuery(hql7);
		Integer quantity =(Integer)query7.uniqueResult();
		
		String hql8="select o.isbn from OrderItemInfo o where o.orderItemId="+i;
		Query query8 = session.createQuery(hql8);
		String isbn =(String)query8.uniqueResult();
		
		String quer="UPDATE Book b set b.rentedQuantity=b.rentedQuantity+"+quantity+" where b.isbn='"+isbn+"'";
		Query query2 = session.createQuery(quer);
		query2.executeUpdate();
		
		
			}
		
			session.close();
		} catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while getting the dispatch orders list", se);
		}
		LOGGER.debug("returnDispatchedSlips method: END");
		
		System.out.println("DISPATCH SLIP :"+dispatchSlipBeans);
		return dispatchSlipBeans;
	}
	public void updateRentedReturn(List<OrderItemInfo> orderItemUpdatedList) {
		// TODO Auto-generated method stub
Session session=factory.openSession();
		
		for (OrderItemInfo orderItemInfo : orderItemUpdatedList) {
			//System.out.println(orderItemInfo.getOrderId() +" "+orderItemInfo.getPaymentStatus());
			
		String hql = "UPDATE OrderItemInfo o set o.returnStatus = 'true' WHERE orderItemId = :order_id and o.orderStatus = 'true' and o.paymentStatus = 'true'";
	Query query = session.createQuery(hql);
	query.setParameter("order_id", orderItemInfo.getOrderItemId());
	int result = query.executeUpdate();
	//System.out.println("Rows affected: " + orderItemInfo.getOrderItemId());
	String hql7="select o.orderQuantity from OrderItemInfo o where o.orderItemId="+orderItemInfo.getOrderItemId();
	Query query7 = session.createQuery(hql7);
	Integer quantity =(Integer)query7.uniqueResult();
	
	String hql8="select o.isbn from OrderItemInfo o where o.orderItemId="+orderItemInfo.getOrderItemId();
	Query query8 = session.createQuery(hql8);
	String isbn =(String)query8.uniqueResult();
	
	String quer="UPDATE Book b set b.rentedQuantity=b.rentedQuantity-"+quantity+" where b.isbn='"+isbn+"'";
	Query query2 = session.createQuery(quer);
	query2.executeUpdate();
	
			}
		
		session.close();
		
	}
	
	
}