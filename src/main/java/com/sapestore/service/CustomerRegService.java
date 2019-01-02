package com.sapestore.service;

import java.util.ArrayList;

import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.RegisterVO;

public interface CustomerRegService {
	void addCustomer(User user );
	ArrayList<City >getAllCities(int sId);
	ArrayList<State> getAllStates();
	ArrayList<Country> getAllCountries();
	boolean checkUserName(String LoginName);
Integer addAddress(Address address);
ArrayList<Gender> getAllGenders();
	

}
