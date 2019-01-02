package com.sapestore.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.BookService;
import com.sapestore.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class BookServiceImplTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void testGetBookList() throws SapeStoreException {
		List<BookVO> bookList = bookService.getBookList(0, null);
		assertNotNull(bookList);
	}
	
	@Test
	public void testGetBookListCategory() throws SapeStoreException {
		List<BookVO> bookList = bookService.getBookList(2, null);
		assertNotNull(bookList);
	}
	
	@Test
	public void testGetBookListSort() throws SapeStoreException {
		List<BookVO> bookList = bookService.getBookList(2, null);
		assertNotNull(bookList);
	}


}
