package com.sapestore.vo;




public class MemberAddress  {

	
    

	
	private int stateId;
	private String name;
	private String addressLine1;
	private String addressLine2;
	private int cityId;
	private String postalCode;
	private String cityName;
	private String stateName;
	private String mobileNumber;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
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
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "MemberAddress [stateId=" + stateId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
				+ ", cityId=" + cityId + ", postalCode=" + postalCode + ", cityName=" + cityName + ", stateName="
				+ stateName + ", mobileNumber=" + mobileNumber + "]";
	}
	
	

}