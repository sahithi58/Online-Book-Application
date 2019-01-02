package com.sapestore.service;



import java.util.ArrayList;


import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.vo.MemberAddress;
import com.sapestore.vo.ShippingAddressVO;


public interface ShippingAddressService {
	public MemberAddress retriveShippingAddress(String userid);
	public void addShippingAddress(ShippingAddressVO shippingaddress);
	
	
    public ArrayList<City> getAllCityId();
    public ArrayList<State> getAllStateId();
    public String getUserName(String userId);
    public String getCityName(Integer cityId);
    public String getStateName(Integer stateId);
    //public List<Integer> getOrderId(String userId);
}
