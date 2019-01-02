package com.sapestore.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sapestore.dao.EmailAddressRetrieveDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class EmailAddressRetrieveDaoTest {

	
	@Autowired
	private EmailAddressRetrieveDao emailAddressRetrieve;
	
	@Test
	public void testRetrieveMailId() {
		assertEquals("ksingh@gmail.com", emailAddressRetrieve.retrieveMailId("ksingh"));
	}

}