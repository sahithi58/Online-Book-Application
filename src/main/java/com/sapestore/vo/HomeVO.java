package com.sapestore.vo;

public class HomeVO {
	
	private boolean checkMe;
	private String categoryName;
	private int categoryId;
	/**
	 * @return the checkMe
	 */
	public boolean isCheckMe() {
		return checkMe;
	}
	/**
	 * @param checkMe the checkMe to set
	 */
	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}
	/**
	 * @return the categoryName
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
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
