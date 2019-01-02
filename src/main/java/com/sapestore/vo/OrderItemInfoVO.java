package com.sapestore.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.sapestore.hibernate.entity.Book;

public class OrderItemInfoVO {
	
	private Long orderId;
	private Integer orderItemId;
	private String isbn;
	private String bookPrice;	
	private Integer orderQuantity;

	private String isActive;
	
	private String purchaseType;
	private String returnStatus;
	private String orderStatus;
		
	private String paymentStatus;
	private Book book;	
	private String categoryName;
	private String bookAuthor;	
	private String publisherName;	
	private String bookTitle;
	private BigDecimal rentPrice;
	private BigDecimal lateFee;
	private String isRented;
	private String returnDate;

	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String isRented() {
		return isRented;
	}
	public void setRented(String isRented) {
		this.isRented = isRented;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Integer orderItemId) {
		this.orderItemId = orderItemId;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String string) {
		this.bookPrice = string;
	}
	public Integer getOrderQuantity() {
		return orderQuantity;
	}
	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	public String getReturnStatus() {
		return returnStatus;
	}
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public BigDecimal getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(BigDecimal rentPrice) {
		this.rentPrice = rentPrice;
	}
	public BigDecimal getLateFee() {
		return lateFee;
	}
	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}
	@Override
	public String toString() {
		return "OrderItemInfoVO [orderId=" + orderId + ", orderItemId=" + orderItemId + ", isbn=" + isbn
				+ ", bookPrice=" + bookPrice + ", orderQuantity=" + orderQuantity + ", isActive=" + isActive
				+ ", purchaseType=" + purchaseType + ", returnStatus=" + returnStatus + ", orderStatus=" + orderStatus
				+ ", paymentStatus=" + paymentStatus + ", book=" + book + ", categoryName=" + categoryName
				+ ", bookAuthor=" + bookAuthor + ", publisherName=" + publisherName + ", bookTitle=" + bookTitle
				+ ", rentPrice=" + rentPrice + ", lateFee=" + lateFee + ", getOrderId()=" + getOrderId()
				+ ", getOrderItemId()=" + getOrderItemId() + ", getIsbn()=" + getIsbn() + ", getBookPrice()="
				+ getBookPrice() + ", getOrderQuantity()=" + getOrderQuantity() + ", getIsActive()=" + getIsActive()
				+ ", getPurchaseType()=" + getPurchaseType() + ", getReturnStatus()=" + getReturnStatus()
				+ ", getOrderStatus()=" + getOrderStatus() + ", getPaymentStatus()=" + getPaymentStatus()
				+ ", getBook()=" + getBook() + ", getCategoryName()=" + getCategoryName() + ", getBookAuthor()="
				+ getBookAuthor() + ", getPublisherName()=" + getPublisherName() + ", getBookTitle()=" + getBookTitle()
				+ ", getRentPrice()=" + getRentPrice() + ", getLateFee()=" + getLateFee() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}