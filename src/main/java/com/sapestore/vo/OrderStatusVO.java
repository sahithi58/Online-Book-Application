package com.sapestore.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderStatusVO {
	private Long orderId;
	private BigDecimal orderItemId;
	private String isbn;
	private Integer orderQuantity;

	private String orderStatus;
	
	public String getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}



	private String purchaseType;

private String bookTitle;
private Date expectedReturnDate;	

	public Date getExpectedReturnDate() {
	return expectedReturnDate;
}



public void setExpectedReturnDate(Date expectedReturnDate) {
	this.expectedReturnDate = expectedReturnDate;
}



	public String getBookTitle() {
	return bookTitle;
}



public void setBookTitle(String bookTitle) {
	this.bookTitle = bookTitle;
}



	public Long getOrderId() {
		return orderId;
	}



	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}



	public BigDecimal getOrderItemId() {
		return orderItemId;
	}



	public void setOrderItemId(BigDecimal orderitemid) {
		this.orderItemId = orderitemid;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}



	public Integer getOrderQuantity() {
		return orderQuantity;
	}



	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}



	public String getPurchaseType() {
		return purchaseType;
	}



	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}



	@Override
	public String toString() {
		return "OrderStatusVO [orderId=" + orderId + ", orderItemId=" + orderItemId + ", isbn=" + isbn
				+ ", orderQuantity=" + orderQuantity + ", purchaseType=" + purchaseType + ", bookTitle=" + bookTitle
				+ "]";
	}



	
}
