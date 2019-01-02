package com.sapestore.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Bean class for Book Dispatch Slip. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class DispatchSlip implements Serializable{

	private static final long serialVersionUID = 1L;
	private BigDecimal orderNumber;
	private String customerName;
	private String shippingAddress;
	
	/**
	 * @return orderID
	 */
	public BigDecimal getOrderNumber() {
		return orderNumber;
	}
	/**
	 * @param orderNumber sets orderID
	 */
	public void setOrderNumber(BigDecimal orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName sets customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return shipping address for the order
	 */
	public String getShippingAddress() {
		return shippingAddress;
	}
	/**
	 * @param shippingAddress sets shipping address for the order
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	@Override
	public String toString() {
		return "DispatchSlip [orderNumber=" + orderNumber + ", customerName=" + customerName + ", shippingAddress="
				+ shippingAddress + "]";
	}
/*	public String get(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}