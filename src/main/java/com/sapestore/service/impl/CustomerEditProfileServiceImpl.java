package com.sapestore.service.impl;



import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.CustomerEditProfileDao;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.CustomerEditProfileService;

import com.sapestore.vo.RegisterVO;

@Service
@Transactional
public class CustomerEditProfileServiceImpl implements CustomerEditProfileService {

	@Autowired
	private CustomerEditProfileDao customerEditProfileDao;
	
	@Override
	public RegisterVO getCustomerInfo(String userId) {
		System.out.println("Getting Customer Informations");
		return customerEditProfileDao.getCustomerInfo(userId);
	}

	@Override
	public void updateCustomer(User user) {
		// TODO Auto-generated method stub
		 customerEditProfileDao.updateCustomer(user);;
	}

	@Override
	public ArrayList<City> getAllCities(Integer stateId) {
		ArrayList<City> cities=customerEditProfileDao.getAllCities(stateId);
		 		return cities;
	}

	@Override
	public ArrayList<State> getAllStates() {
        System.out.println("in service");
		ArrayList<State> states=customerEditProfileDao.getAllStates();
 		return states;
	}

	@Override
	public ArrayList<Country> getAllCountries() {
		ArrayList<Country> countries=customerEditProfileDao.getAllCountries();
 		return countries;	}

	@Override
	public boolean checkUserName(String LoginName) {
		boolean checkUser=customerEditProfileDao.checkUserName(LoginName);
		return checkUser;
	}



	@Override
	public Integer addAddress(Address address,String userId) {
		Integer addressId=customerEditProfileDao.addAddress(address, userId);
		return addressId;
		
	
	}

	@Override
	public ArrayList<Gender> getAllGenders() {
		// TODO Auto-generated method stub
		ArrayList<Gender> gender=customerEditProfileDao.getAllGenders();
 		return gender;
	}
	public Integer getAddressId(String userId){
		return customerEditProfileDao.getAddressId(userId);
	}

}
