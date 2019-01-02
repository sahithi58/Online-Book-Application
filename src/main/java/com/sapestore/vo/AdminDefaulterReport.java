package com.sapestore.vo;

import java.io.Serializable;

/**
 * Bean class for Defaulter report. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class AdminDefaulterReport implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String customerName;
	private String bookTitle;
	private double rentPrice;
	private String categoryName;
	private String expectedReturnDate;
	private String actualReturnDate;
	private String returnStatus;
	private double lateFee;
	private long orderID;
	private String email;
	

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the emailId to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customer's name to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	/**
	 * @return rentPrice
	 */
	public double getRentPrice() {
		return rentPrice;
	}
	/**
	 * @param rentPrice the rentPrice to set
	 */
	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}
	/**
	 * @return categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	/**
	 * @return expectedReturnDate
	 */
	public String getExpectedReturnDate() {
		return expectedReturnDate;
	}
	/**
	 * @param expectedReturnDate the expectedReturnDate to set
	 */
	public void setExpectedReturnDate(String expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	/**
	 * @return actualReturnDate
	 */
	public String getActualReturnDate() {
		return actualReturnDate;
	}
	/**
	 * @param actualReturnDate the actualReturnDate to set
	 */
	public void setActualReturnDate(String actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	/**
	 * @return returnStatus
	 */
	public String getReturnStatus() {
		return returnStatus;
	}
	/**
	 * @param returnStatus the returnStatus to set
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	/**
	 * @return lateFee
	 */
	public double getLateFee() {
		return lateFee;
	}
	/**
	 * @param lateFee the lateFee to set
	 */
	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}
	/**
	 * @return orderID
	 */
	public long getOrderID() {
		return orderID;
	}
	/**
	 * @param orderID the orderID to set
	 */
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
}