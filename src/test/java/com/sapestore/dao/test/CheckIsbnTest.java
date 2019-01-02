package com.sapestore.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.service.BookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml"})
@WebAppConfiguration
public class CheckIsbnTest {

	@Autowired
	private BookService bookService;
	
	//spublic boolean checkIsbn(String isbn);
	
	@Test
	@Transactional
	public void test() {
	assertFalse(bookService.checkIsbn("989784"));
	}

}
