package com.sapestore.vo;

import java.io.Serializable;

public class PurchasedUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean dispatchStatus;
	private boolean paymentStatus;
	
	public PurchasedUpdate() {
		super();
	}
	
	public PurchasedUpdate(boolean dispatchStatus, boolean paymentStatus) {
		super();
		this.dispatchStatus = dispatchStatus;
		this.paymentStatus = paymentStatus;
	}
	
	public boolean getDispatchStatus() {
		return dispatchStatus;
	}
	public void setDispatchStatus(boolean dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
	public boolean getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	
	
}
