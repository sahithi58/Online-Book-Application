package com.sapestore.dao.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.CustomerRegDao;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class CustomerRegDaoTest {
static Integer id;
	@Autowired
	CustomerRegDao customerRegDao;

	@Transactional
	@Rollback(true)
	@Test
	public void testAddAddress() {
		Address address = new Address();
		Date date = new Date();

		address.setUserId("durga");
		address.setAddressLine1("hno:12,rtcx");
		address.setAddressLine2("hyd,tlg");
		address.setCityId(1);
		address.setCountryId(1);
		address.setPostalCode("123456");
		address.setStateId(2);
		address.setCreatedDate(date);
		address.setUpdatedDate(date);
		address.setIsActive("Y");
		

		 id = customerRegDao.addAddress(address);
		System.out.println("addid" + id);
		// address.setAddressId(id);

		boolean check = customerRegDao.checkUserName("durga");
		Assert.assertEquals(check, true);
		

	}

	@Transactional
	@Rollback(true)
	@Test
	public void testAddCustomer() {
	
		Date date = new Date();
		Address address = new Address();

		User user = new User();

		address.setUserId("durga");
		address.setAddressLine1("hno:12,rtcx");
		address.setAddressLine2("hyd,tlg");
		address.setCityId(1);
		address.setCountryId(1);
		address.setPostalCode("123456");
		address.setStateId(2);
		address.setCreatedDate(date);
		address.setUpdatedDate(date);
		address.setIsActive("Y");
	
		 id = customerRegDao.addAddress(address);

		user.setAddressId(id);
		user.setUserId("durga");
		user.setPassword("durga01");
		user.setName("durga");
		user.setGenderId(1);
		user.setEmailAddress("durga0110@gmail.com");
		user.setMobileNumber((long) 1234567891);
		user.setUserStatus("Y");
		user.setCreatedDate(date);
		user.setUpdatedDate(date);
		user.setIsAdmin("N");
		user.setIsActive("Y");
		user.setPhone((long) 1234567);

		customerRegDao.addCustomer(user);

		boolean check = customerRegDao.checkUserName("durga");
		Assert.assertEquals(check, false);

	}

	

	@Test
	public void testGetAllCities() {
	
		ArrayList<City> city2 = customerRegDao.getAllCities(2);
		
		assertNotNull(city2);

	}

	@Test
	public void testGetAllStates() {

		ArrayList<State> state = customerRegDao.getAllStates();
	
		assertNotNull(state);
	}

	@Test
	public void testGetAllCountries() {


		ArrayList<Country> country = customerRegDao.getAllCountries();
		assertNotNull(country);
	}

	@Test
	public void testCheckUserName() {
		boolean user=customerRegDao.checkUserName("durga");
		assertEquals(user,true);
	
	}

	@Test
	public void testGetAllGenders() {

		ArrayList<Gender> gender = customerRegDao.getAllGenders();
		assertNotNull(gender);
	}

}