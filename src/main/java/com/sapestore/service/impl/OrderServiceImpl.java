package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderService;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.OrderVO;
import com.sapestore.vo.PurchasedUpdate;
import com.sapestore.vo.RentedUpdate;

/**
 * Service class for updating rent information.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderServiceImpl.class.getName());

	@Autowired
	private OrderDao orderDao;

	@Override
	public List<BigDecimal> updateRentedDispatch(List<RentedUpdate> rentedUpdateList) throws SapeStoreException {
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateDispatch method: START");
		}
		List<OrderItemInfo> orderItemInfoList = orderDao.getRentedOrders();
		List<BigDecimal> dispatchedOrders= new ArrayList<>();
		if (orderItemInfoList != null && orderItemInfoList.size() > 0) {
			Iterator<OrderItemInfo> iterator = orderItemInfoList.iterator();
			Iterator<RentedUpdate> iterator1 = rentedUpdateList.iterator();
			for (; iterator.hasNext() && iterator1.hasNext();) {
				OrderItemInfo orderItemInfo = iterator.next();
				RentedUpdate rentedUpdate = iterator1.next();
				System.out.println(orderItemInfo.getOrderItemId()+" " +rentedUpdate.getDispatchStatus()+" "+orderItemInfo.getOrderStatus());
				if(rentedUpdate.getDispatchStatus()==true && orderItemInfo.getOrderStatus().equals("false")){
					dispatchedOrders.add(orderItemInfo.getOrderItemId());
					System.out.println("in if"+orderItemInfo.getOrderItemId());
				}
			}
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateDispatch method: END");
		}
		return dispatchedOrders;
		
			}


	@Override
	public void updateReturn(List<RentedUpdate> rentedUpdateList) throws SapeStoreException {
		List<OrderItemInfo> orderItemInfoList = orderDao.getRentedOrders();
		List<OrderVO> rentedOrderBeans = setRentedOrders(orderItemInfoList);

		List<Integer> orderNums = new ArrayList<Integer>();
		List<Boolean> orgListDispatch = new ArrayList<Boolean>();
		List<Boolean> orgListReturn = new ArrayList<Boolean>();
		List<Boolean> newListDispatch = new ArrayList<Boolean>();
		List<Boolean> newListReturn = new ArrayList<Boolean>();

		for (OrderVO r : rentedOrderBeans) {
			orgListDispatch.add(r.isOrderStatus());
			orgListReturn.add(r.isReturnReceived());
			orderNums.add(r.getOrderNumber());
		}
		for (RentedUpdate r : rentedUpdateList) {
			newListDispatch.add(r.getDispatchStatus());
			newListReturn.add(r.getReturnStatus());
		}
		orderDao.updateReturn(orgListDispatch, orgListReturn, newListDispatch, newListReturn, orderNums);
	}

	/**
	 * set the status of rented books from the admin console
	 * 
	 * @param list
	 * @return beans
	 */
	private List<OrderVO> setRentedOrders(List<OrderItemInfo> orderItemInfoList) {
		List<OrderVO> beans = null;

		if (orderItemInfoList != null && !orderItemInfoList.isEmpty()) {
			beans = new ArrayList<OrderVO>();
			for (int i = 0; i < orderItemInfoList.size(); i++) {
				OrderVO tempList = new OrderVO();
				tempList.setOrderNumber((int) (long) orderItemInfoList.get(i).getOrderId());
				tempList.setItemName(orderItemInfoList.get(i).getBookTitle());
				tempList.setRentAmount(orderItemInfoList.get(i).getRentPrice());

				String sd = orderItemInfoList.get(i).getOrderStatus();
				if (sd.equalsIgnoreCase("Dispatched")) {
					tempList.setOrderStatus(true);
				} else {
					tempList.setOrderStatus(false);
				}
				String sr = orderItemInfoList.get(i).getReturnStatus();
				if (sr.equalsIgnoreCase("Returned")) {
					tempList.setReturnReceived(true);
				} else {
					tempList.setReturnReceived(false);
				}
				if (orderItemInfoList.get(i).getExpectedReturnDate() == null) {

				} else {
					tempList.setExpectedReturnDate(orderItemInfoList.get(i).getExpectedReturnDate());
				}
				if (orderItemInfoList.get(i).getActualReturnDate() == null) {

				} else {
					tempList.setActualReturnDate(orderItemInfoList.get(i).getExpectedReturnDate());
				}
				tempList.setLateFee(orderItemInfoList.get(i).getLateFee());
				beans.add(tempList);
			}
		}
		return beans;
	}

	@Override
	public List<DispatchSlip> getDispatchedOrders(List<BigDecimal> list) throws SapeStoreException {
		LOGGER.debug("getDispatchedOrders method: START");
		List<DispatchSlip> dispatchList = orderDao.returnDispatchedSlips(list);
		LOGGER.debug("getDispatchedOrders method: END");
		return dispatchList;
	}

	@Override
	public List<BigDecimal> updatePurchaseDispatch(List<PurchasedUpdate> purchasedUpdateList) throws SapeStoreException {
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateDispatch method: START");
		}
		List<OrderItemInfo> orderItemInfoList = orderDao.getPurchasedOrders();
		List<BigDecimal> dispatchedOrders= new ArrayList<>();
		if (orderItemInfoList != null && orderItemInfoList.size() > 0) {
			Iterator<OrderItemInfo> iterator = orderItemInfoList.iterator();
			Iterator<PurchasedUpdate> iterator1 = purchasedUpdateList.iterator();
			for (; iterator.hasNext() && iterator1.hasNext();) {
				OrderItemInfo orderItemInfo = iterator.next();
				PurchasedUpdate purchasedUpdate = iterator1.next();
				//System.out.println(orderItemInfo.getOrderItemId()+" " +orderItemInfo.getOrderStatus());
				if(purchasedUpdate.getDispatchStatus()==true && orderItemInfo.getOrderStatus().equals("false")){
					dispatchedOrders.add(orderItemInfo.getOrderItemId());
					//System.out.println("in if"+orderItemInfo.getOrderItemId());
				}
			}
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateDispatch method: END");
		}
		return dispatchedOrders;
	}

	private List<OrderVO> setPurchasedOrders(List<OrderItemInfo> orderItemInfoList) {
		List<OrderVO> beans = null;

		if (orderItemInfoList != null && !orderItemInfoList.isEmpty()) {
			beans = new ArrayList<OrderVO>();
			for (int i = 0; i < orderItemInfoList.size(); i++) {
				OrderVO tempList = new OrderVO();
				tempList.setOrderNumber((int) (long) orderItemInfoList.get(i).getOrderId());
				tempList.setItemName(orderItemInfoList.get(i).getBookTitle());
				// tempList.setRentAmount(orderItemInfoList.get(i).getRentPrice());

				String sd = orderItemInfoList.get(i).getOrderStatus();
				if (sd.equalsIgnoreCase("true")) {
					tempList.setOrderStatus(true);
				} else {
					tempList.setOrderStatus(false);
				}
				String sr = orderItemInfoList.get(i).getPaymentStatus();
				if (sr.equalsIgnoreCase("true")) {
					tempList.setReturnReceived(true);
				} else {
					tempList.setReturnReceived(false);
				}
				beans.add(tempList);
			}
		}
		return beans;
	}

	@SuppressWarnings("null")
	@Override
	public void updatePurchasePayment(List<PurchasedUpdate> purchasedUpdateList) throws SapeStoreException {
		// TODO Auto-generated method stub
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateDispatch method: START");
		}
		List<OrderItemInfo> orderItemInfoList = orderDao.getPurchasedOrders();		
		List<OrderItemInfo> orderItemUpdatedList = new ArrayList<>();				
		if (orderItemInfoList != null && orderItemInfoList.size() > 0) {
			Iterator<OrderItemInfo> iterator = orderItemInfoList.iterator();
			Iterator<PurchasedUpdate> iterator1 = purchasedUpdateList.iterator();
			
			for (; iterator.hasNext() && iterator1.hasNext();) {
				OrderItemInfo orderItemInfo = iterator.next();
				PurchasedUpdate purchasedUpdate = iterator1.next();
				if(purchasedUpdate.getPaymentStatus()==true){
					orderItemUpdatedList.add(orderItemInfo);
				}
				
				
			}
		}
		
		orderDao.updatePayment(orderItemUpdatedList);
			
	}
	
	@Override
	public List<DispatchSlip> getRentedDispatchedOrders(List<BigDecimal> list) throws SapeStoreException {
		// TODO Auto-generated method stub
		LOGGER.debug("getDispatchedOrders method: START");
		List<DispatchSlip> dispatchList = orderDao.returnRentedDispatchedSlips(list);
		LOGGER.debug("getDispatchedOrders method: END");
		return dispatchList;
	}


	@Override
	public void updateRentedPayment(List<RentedUpdate> rentedUpdateList) throws SapeStoreException {
		// TODO Auto-generated method stub
		System.out.println("payment service impl method: START");
		List<OrderItemInfo> orderItemInfoList = orderDao.getRentedOrders();
		List<OrderItemInfo> dispatchedOrders= new ArrayList<>();
		if (orderItemInfoList != null && orderItemInfoList.size() > 0) {
			Iterator<OrderItemInfo> iterator = orderItemInfoList.iterator();
			Iterator<RentedUpdate> iterator1 = rentedUpdateList.iterator();
			for (; iterator.hasNext() && iterator1.hasNext();) {
				OrderItemInfo orderItemInfo = iterator.next();
				RentedUpdate rentedUpdate = iterator1.next();
				System.out.println(orderItemInfo.getOrderItemId()+" " +rentedUpdate.getPaymentStatus()+" "+orderItemInfo.getPaymentStatus());
				if(rentedUpdate.getPaymentStatus()==true && orderItemInfo.getPaymentStatus().equals("false")){
					dispatchedOrders.add(orderItemInfo);
					System.out.println("in if"+orderItemInfo.getOrderItemId());
				}
			}
		}
		
		
		
		orderDao.updatePayment(dispatchedOrders);
		
	}


	@Override
	public List<BigDecimal> updateRentedReturn(List<RentedUpdate> rentedUpdateList) throws SapeStoreException {
		// TODO Auto-generated method stub
		List<BigDecimal> updatedItem =new ArrayList<>();
		List<OrderItemInfo> orderItemInfoList = orderDao.getRentedOrders();
		List<OrderItemInfo> dispatchedOrders= new ArrayList<>();
		if (orderItemInfoList != null && orderItemInfoList.size() > 0) {
			Iterator<OrderItemInfo> iterator = orderItemInfoList.iterator();
			Iterator<RentedUpdate> iterator1 = rentedUpdateList.iterator();
			for (; iterator.hasNext() && iterator1.hasNext();) {
				OrderItemInfo orderItemInfo = iterator.next();
				RentedUpdate rentedUpdate = iterator1.next();
				System.out.println(orderItemInfo.getOrderItemId()+" " +rentedUpdate.getReturnStatus()+" "+orderItemInfo.getReturnStatus());
				if(rentedUpdate.getReturnStatus()==true && orderItemInfo.getReturnStatus().equals("false")){
					dispatchedOrders.add(orderItemInfo);
					updatedItem.add(orderItemInfo.getOrderItemId());
					System.out.println("in if"+orderItemInfo.getOrderItemId());
				}
			}
		}
		
		orderDao.updateRentedReturn(dispatchedOrders);
		return updatedItem;
		
	}

}