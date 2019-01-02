package com.sapestore.vo;

import java.io.Serializable;

/**
 * Bean class for Rented books status. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class RentedUpdate implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean dispatchStatus;
	private boolean returnStatus;
	private boolean paymentStatus;
	
	public boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public RentedUpdate()
	{
	}
	
	public RentedUpdate(boolean dispatchStatus,boolean returnStatus)
	{
		this.dispatchStatus = dispatchStatus;
		this.returnStatus = returnStatus;
	}
	
	/**
	 * @return book dispatch status(dispatched/pending)
	 */
	public boolean getDispatchStatus() {
		return dispatchStatus;
	}
	/**
	 * @param dispatchStatus sets book dispatch status(dispatched/pending)
	 */
	public void setDispatchStatus(boolean dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
	/**
	 * @return book return status(returned/pending)
	 */
	public boolean getReturnStatus() {
		return returnStatus;
	}
	/**
	 * @param returnStatus sets book return status(returned/pending)
	 */
	public void setReturnStatus(boolean returnStatus) {
		this.returnStatus = returnStatus;
	}
}