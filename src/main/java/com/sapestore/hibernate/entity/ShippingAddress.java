package com.sapestore.hibernate.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="SAPESTORE_SHIPPING_ADDRESS")
public class ShippingAddress {
	
	@Id
	@Column(name="ADDRESS_ID")
	private Integer addressId;
	
	@Column(name="STATE_ID")
	private Integer stateId;
	
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	
	@Column(name="ADDRESS_LINE1")
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE2")
	private String addressLine2;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	
	
	
	@Column(name="POSTAL_CODE")
	private String zipCode;

	@Column(name="CREATED_DATE")
	private Date createdDate = new Date();
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate = new Date();
	
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="ORDER_ID")
	private Long orderId;
	

	
	
	@Column(name="NAME")
	private String customerName;




	public Integer getAddressId() {
		return addressId;
	}




	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}




	public Integer getStateId() {
		return stateId;
	}




	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}




	public Integer getCountryId() {
		return countryId;
	}




	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
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




	public Integer getCityId() {
		return cityId;
	}




	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}




	public String getZipCode() {
		return zipCode;
	}




	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}




	public Date getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	public Date getUpdatedDate() {
		return updatedDate;
	}



	@PreUpdate
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = new Date();
	}




	public String getIsActive() {
		return isActive;
	}




	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}




	public Long getOrderId() {
		return orderId;
	}




	public void setOrderId(Long long1) {
		this.orderId = long1;
	}




	public String getCustomerName() {
		return customerName;
	}




	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}




	@Override
	public String toString() {
		return "ShippingAddress [addressId=" + addressId + ", stateId=" + stateId + ", countryId=" + countryId
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", cityId=" + cityId
				+ ", zipCode=" + zipCode + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate
				+ ", isActive=" + isActive + ", orderId=" + orderId + ", customerName=" + customerName + "]";
	}



}

