package com.sapestore.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.CustomerRegDao;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.CustomerRegService;
import com.sapestore.vo.RegisterVO;
@Service
public class CustomerRegServiceImpl implements CustomerRegService {
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(CustomerRegServiceImpl.class.getName());
	
	@Autowired
	private CustomerRegDao customerRegDao;

	@Override
	public void addCustomer(User user) {
		LOGGER.debug("ADDING THE CUSTOMER TO DB:START");
		customerRegDao.addCustomer(user);
        LOGGER.debug("CUSTOMER ADDED");
		//return user;
	}

	@Override
	public ArrayList<City> getAllCities(int sId) {
		ArrayList<City> cities=customerRegDao.getAllCities(sId);
		 		return cities;
	}

	@Override
	public ArrayList<State> getAllStates() {
        System.out.println("in service");
		ArrayList<State> states=customerRegDao.getAllStates();
 		return states;
	}

	@Override
	public ArrayList<Country> getAllCountries() {
		ArrayList<Country> countries=customerRegDao.getAllCountries();
 		return countries;	}

	@Override
	public boolean checkUserName(String LoginName) {
		boolean checkUser=customerRegDao.checkUserName(LoginName);
		return checkUser;
	}



	@Override
	public Integer addAddress(Address address) {
		Integer addressId=customerRegDao.addAddress(address);
		return addressId;
		
	
	}

	@Override
	public ArrayList<Gender> getAllGenders() {
		// TODO Auto-generated method stub
		ArrayList<Gender> gender=customerRegDao.getAllGenders();
 		return gender;
	}

}
