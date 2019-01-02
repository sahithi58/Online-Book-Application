package com.sapestore.hibernate.entity;

public class Search {

	int categoryId;
	String bookAuthor;
	String bookTitle;
	String publisher;
	boolean partnerStore;
	
	
	
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public boolean isPartnerStore() {
		return partnerStore;
	}
	public void setPartnerStore(boolean partnerstore) {
		this.partnerStore = partnerstore;
	}
	
	

}
