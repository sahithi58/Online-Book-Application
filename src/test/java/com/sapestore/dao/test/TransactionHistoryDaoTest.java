package com.sapestore.dao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sapestore.dao.ProductDao;
import com.sapestore.dao.TransactionHistoryDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Search;
import com.sapestore.service.BookService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.TransactionHistoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class TransactionHistoryDaoTest {
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;
	
	@Autowired
	private BookService bookService;

	 
	@Test 
public void testGetBookSearchList() throws Exception {
		
	List<TransactionHistoryVO> books = transactionHistoryDao.getTransactionHistory("sudip002");
	assertEquals(1, books.size());
	/*for (TransactionHistoryVO bookVo : books) {
		assertEquals(search.getBookAuthor(), bookVo.getBookAuthor());
		assertEquals(search.getBookTitle(), bookVo.getBookTitle());
		//assertEquals(search.getCategoryId(), Integer.parseInt(bookVo.getCategoryId()));
		
		}*/
		} 
	
	
			} 
		
