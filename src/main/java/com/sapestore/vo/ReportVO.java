package com.sapestore.vo;

import java.io.Serializable;

/**
 * Bean class for Purchased/Rented report. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */
public class ReportVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String publisherName;
	private String bookTitle;
	private int quantity;
	private String bookAuthor;
	private String categoryName;
	private int bookPrice;
	private String orderType;	
	private String isbn;
	private int rentPrice;

	public int getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}
	/**
	 * @return the bookPrice
	 */
	public int getBookPrice() {
		return bookPrice;
	}
	/**
	 * @param bookPrice the bookPrice to set
	 */
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * @return book category name
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName sets book category name
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	/**
	 * @return book publisher name
	 */
	public String getPublisherName() {
		return publisherName;
	}
	/**
	 * @param publisherName sets book publisher name
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	/**
	 * @return book title/name
	 */
	public String getBookTitle() {
		return bookTitle;
	}
	/**
	 * @param bookTitle sets book title/name
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	/**
	 * @return available book quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity sets available book quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return book author name
	 */
	public String getBookAuthor() {
		return bookAuthor;
	}
	/**
	 * @param bookAuthor sets book author name
	 */
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	/**
	 * @return book order type(purchased/rented)
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType sets book order type(purchased/rented)
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}