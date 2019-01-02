package com.sapestore.service;

import java.util.ArrayList;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.RegisterVO;

public interface CustomerEditProfileService {

	public RegisterVO getCustomerInfo(String userId);

	public void updateCustomer(User user);

	ArrayList<City> getAllCities(Integer stateId);

	ArrayList<State> getAllStates();

	ArrayList<Country> getAllCountries();

	boolean checkUserName(String LoginName);

	Integer addAddress(Address address,String userId);

	ArrayList<Gender> getAllGenders();
	public Integer getAddressId(String userId);
}
