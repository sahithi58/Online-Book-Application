package com.sapestore.service;

import java.math.BigDecimal;
import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.PurchasedUpdate;
import com.sapestore.vo.RentedUpdate;
import com.sun.mail.iap.ConnectionException;

/**
 * Service interface for updating rent information.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

public interface OrderService {

	/**
	 * Updates dispatch status of books.
	 * @param rentedUpdateList
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	List<BigDecimal> updateRentedDispatch(List<RentedUpdate> rentedUpdateList) throws SapeStoreException;

	
	/**
	 * Updates return status of books.
	 * @param rentedUpdateList
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	void updateReturn(List<RentedUpdate> rentedUpdateList) throws SapeStoreException;
	
	/**
	 * Returns list of dispatched orders.
	 * @param list
	 * @return
	 * @throws SapeStoreSystemException
	 */
	List<DispatchSlip> getDispatchedOrders(List<BigDecimal> list) throws SapeStoreException;


	List<BigDecimal> updatePurchaseDispatch(List<PurchasedUpdate> purchasedUpdateList) throws SapeStoreException;


	void updatePurchasePayment(List<PurchasedUpdate> purchasedUpdateList) throws SapeStoreException;
	
	List<DispatchSlip> getRentedDispatchedOrders(List<BigDecimal> list) throws SapeStoreException;


	void updateRentedPayment(List<RentedUpdate> rentedUpdateList) throws SapeStoreException;


	List<BigDecimal> updateRentedReturn(List<RentedUpdate> rentedUpdateList) throws SapeStoreException;


	
}