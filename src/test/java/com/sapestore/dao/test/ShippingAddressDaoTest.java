package com.sapestore.dao.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.ShippingAddressDao;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.vo.MemberAddress;
import com.sapestore.vo.ShippingAddressVO;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class ShippingAddressDaoTest {

	@Autowired
	ShippingAddressDao shippingAddressDao;
	
	@Test
	public void testRetriveShippingAddress() {
		MemberAddress address = shippingAddressDao.retriveShippingAddress("salluhms");
		assertNotNull(address);
		
	}
	
	
	

	@Test
	public void testGetAllCityId() {
	   List<City> city = shippingAddressDao.getAllCityId();
	   assertNotNull(city);
	}

	@Test
	public void testGetAllStateId() {
		List<State> state = shippingAddressDao.getAllStateId();
		assertNotNull(state);
	}

	@Test
	public void testGetCityName() {
		assertEquals("Los Angeles",shippingAddressDao.getCityName(4));
	}

	@Test
	public void testGetStateName() {
		assertEquals("Alaska",shippingAddressDao.getStateName(6));
	}
/*
	@Test
	public void testGetOrderId() {
		List<Integer> orderid =shippingAddressDao.getOrderId("admin");
		assertNotNull(orderid);
	}*/

}
