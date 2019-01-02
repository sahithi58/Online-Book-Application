package com.sapestore.vo;

public class CurrentShippingAddress {

	String customerName;
	String addressLine1;
	String addressLine2;
	String city;
	String state;
	String zipcode;
	Long orderId;
	String mobilenumber;
	
	
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long oid) {
		this.orderId = oid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	@Override
	public String toString() {
		return "CurrentShippingAddress [customerName=" + customerName + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", mobilenumber=" + mobilenumber + "]";
	}

	
	
}
