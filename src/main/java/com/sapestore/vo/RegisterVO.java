package com.sapestore.vo;

public class RegisterVO {
       
       private String fullName;
       private String loginName;
       private String password;
       private String email;
       private String address1;
       private String address2;
       private Integer cityId;
       private Long phoneNumber;
       private Long mobileNumber;
       private Integer genderId;
       private Integer stateId;
       private String zip;
       
       public String getZip() {
              return zip;
       }
       public void setZip(String zip) {
              this.zip = zip;
       }
       public Integer getGenderId() {
              return genderId;
       }
       public void setGenderId(Integer genderId) {
              this.genderId = genderId;
       }
      
       
       public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public Integer getCityId() {
              return cityId;
       }
       public void setCityId(Integer cityId) {
              this.cityId = cityId;
       }
       public Integer getStateId() {
              return stateId;
       }
       public void setStateId(Integer stateId) {
              this.stateId = stateId;
       }
       
       
       public String getFullName() {
              return fullName;
       }
       public void setFullName(String fullName) {
              this.fullName = fullName;
       }
       public String getLoginName() {
              return loginName;
       }
       public void setLoginName(String loginName) {
              this.loginName = loginName;
       }
       public String getPassword() {
              return password;
       }
       public void setPassword(String password) {
              this.password = password;
       }
       public String getEmail() {
              return email;
       }
       public void setEmail(String email) {
              this.email = email;
       }
       public String getAddress1() {
              return address1;
       }
       public void setAddress1(String address1) {
              this.address1 = address1;
       }
       public String getAddress2() {
              return address2;
       }
       public void setAddress2(String address2) {
              this.address2 = address2;
       }
	@Override
	public String toString() {
		return "RegisterVO [fullName=" + fullName + ", loginName=" + loginName + ", password=" + password + ", email="
				+ email + ", address1=" + address1 + ", address2=" + address2 + ", cityId=" + cityId + ", phoneNumber="
				+ phoneNumber + ", mobileNumber=" + mobileNumber + ", genderId=" + genderId + ", stateId=" + stateId
				+ ", zip=" + zip + "]";
	}
       
       
       

}

