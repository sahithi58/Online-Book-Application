package com.sapestore.hibernate.entity;

import java.math.BigDecimal;

public class TransactionHistory {
private String bookTitle;
private String bookAuthor;
private String publisherName;
private String date;
private String dueDate;
private String type;
private BigDecimal bookPrice;
private BigDecimal lateFee;
public String getBookTitle() {
	return bookTitle;
}
public void setBookTitle(String bookTitle) {
	this.bookTitle = bookTitle;
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
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getDueDate() {
	return dueDate;
}
public void setDueDate(String dueDate) {
	this.dueDate = dueDate;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public BigDecimal getBookPrice() {
	return bookPrice;
}
public void setBookPrice(BigDecimal bookPrice) {
	this.bookPrice = bookPrice;
}
public BigDecimal getLateFee() {
	return lateFee;
}
public void setLateFee(BigDecimal lateFee) {
	this.lateFee = lateFee;
}



}
