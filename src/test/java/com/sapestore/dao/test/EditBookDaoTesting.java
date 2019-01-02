package com.sapestore.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.ProductDao;
import com.sapestore.service.impl.InventoryServiceImpl;
import com.sapestore.vo.BookVO;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class EditBookDaoTesting {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private InventoryServiceImpl invertoryService;

	@Transactional
	@Rollback(true)
	@Test
	public void testEditBooks() {

		BookVO book = new BookVO();
		book.setBookAuthor("Malala Yousafz edited");
		book.setBookDetailDesc("very very good book");
		book.setBookPrice("123");
		book.setBookShortDesc("nice");
		book.setBookTitle("I am Malala edited4 testing");
		book.setCategoryId("7");
		book.setIsbn("3423434234");
		book.setOldIsbn("6785");
		book.setPublisherName("testingpublisher");
		book.setRentAvailable("Y");
		book.setRentPrice("23");
		book.setQuantity(12);
		book.setActive("Y");
		book.setThumbPath("D:/bookimage.jpg");
		book.setFullPath("D:/bookimage.jpg");

		try {
			productDao.updateBooks(book);
			BookVO book1 = productDao.getBookDetails("3423434234");
			System.out.println(book1.getBookAuthor());
			Assert.assertEquals(book1.getBookPrice(), book.getBookPrice());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testEditBooksServiceImpl() {

		BookVO book = new BookVO();
		book.setBookAuthor("Malala Yousafz edited");
		book.setBookDetailDesc("very very good book");
		book.setBookPrice("123");
		book.setBookShortDesc("nice");
		book.setBookTitle("I am Malala edited4 testing");
		book.setCategoryId("7");
		book.setIsbn("4534546");
		book.setOldIsbn("698745612");
		book.setPublisherName("testingpublisher");
		book.setRentAvailable("Y");
		book.setRentPrice("23");
		book.setQuantity(12);
		book.setActive("Y");
		book.setThumbPath("D:/bookimage.jpg");
		book.setFullPath("D:/bookimage.jpg");

		try {
			invertoryService.updateBooks(book);
			BookVO book1 = productDao.getBookDetails("4534546");
			System.out.println(book1.getBookAuthor());
			Assert.assertEquals(book1.getBookPrice(), book.getBookPrice());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	

}