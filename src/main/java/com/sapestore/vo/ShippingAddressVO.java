package com.sapestore.vo;


public class ShippingAddressVO {
	

	
	private String name;
	private String addressLine1;
	private String addressLine2;
	private int cityId;
	private int stateId;
	private String zipCode;
	private long mobileNumber;
	private Long orderId;
	private int addressId;

	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long oid) {
		this.orderId = oid;
	}
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "ShippingAddressVO [name=" + name + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", cityId=" + cityId + ", stateId=" + stateId + ", zipCode=" + zipCode + ", mobileNumber="
				+ mobileNumber + ", orderId=" + orderId + "]";
	}
	
	
	
	
}
