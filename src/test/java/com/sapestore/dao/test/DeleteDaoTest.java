package com.sapestore.dao.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.vo.BookVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class DeleteDaoTest {

	@Autowired
	private ProductDao productDao;

	BookVO book = new BookVO();


	@Test
	@Transactional
	public void testDeleteBook() {
		
		try {
			productDao.deleteBook("sa21");
			book = productDao.getBookDetails("sa21");
			System.out.println(book);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(book);

	}

	@Test
	public void testDetailsDeleteBook() {
		
		try {
			book = productDao.getBookDetails("7989");
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNull(book);

	}
	
}
