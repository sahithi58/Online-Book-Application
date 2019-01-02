package com.sapestore.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.AccountDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class AccountDaoTest {
	@Autowired
	AccountDao account;
	
	
	@Transactional
	@Test
	public void testGetUserDetails() throws SapeStoreException {
		
		
		
		
		User user=account.getUserDetails("PhanindraPvs","phani555");
		System.out.println(user);
		assertNotNull(user);
		
		
		
	}
	
	

	

}
