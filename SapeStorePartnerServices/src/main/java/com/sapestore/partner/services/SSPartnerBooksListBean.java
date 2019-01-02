package com.sapestore.partner.services;

import java.io.Serializable;

public class SSPartnerBooksListBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2870499573954042174L;
	private String isbn;
	private String publisherName;
	private int categoryIdpr;
	private String bookTitle;
	private int quantity;
	private String bookAuthor;
	private String thumbImageUrl;
	private String fullImageUrl;
	private int bookPrice;
	private String bookShortDesc;
	private String bookDetailDesc;
	private String active;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	
	public int getCategoryIdpr() {
		return categoryIdpr;
	}
	public void setCategoryIdpr(int categoryIdpr) {
		this.categoryIdpr = categoryIdpr;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getThumbImageUrl() {
		return thumbImageUrl;
	}
	public void setThumbImageUrl(String thumbImageUrl) {
		this.thumbImageUrl = thumbImageUrl;
	}
	public String getFullImageUrl() {
		return fullImageUrl;
	}
	public void setFullImageUrl(String fullImageUrl) {
		this.fullImageUrl = fullImageUrl;
	}
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookShortDesc() {
		return bookShortDesc;
	}
	public void setBookShortDesc(String bookShortDesc) {
		this.bookShortDesc = bookShortDesc;
	}
	public String getBookDetailDesc() {
		return bookDetailDesc;
	}
	public void setBookDetailDesc(String bookDetailDesc) {
		this.bookDetailDesc = bookDetailDesc;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	
}
