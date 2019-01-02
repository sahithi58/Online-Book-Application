package com.sapestore.dao.test;

import static org.junit.Assert.*;

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
import com.sapestore.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml"})
@WebAppConfiguration
public class BookDetailsTest {

	@Autowired
	private ProductDao productDao;
	
	
	@Transactional
	@Rollback(true)
	@Test
	public void testAddNewBooks1() {


		BookVO book = new BookVO();
		book.setBookAuthor("GreatAuthor");
		book.setBookDetailDesc("very very good book");
		book.setBookPrice("1223");
		book.setBookShortDesc("nicde");
		book.setBookTitle("Hardfry potter");
		book.setCategoryId("3");
		book.setIsbn("sah21");
		book.setPublisherName("saahiethi");
		book.setRentAvailable("Y");
		book.setRentPrice("23");
		book.setQuantity(3);
		book.setActive("Y");
		book.setThumbPath("img/products/Thumbnails/Chrysanthemum.jpg");
		book.setFullPath("img/products/Thumbnails/Chrysanthemum.jpg");
		try {
				BookVO book1 = productDao.getBookDetails("GoodIsbn");
				System.out.println(book1);
	   String author = book1.getBookAuthor();
	   String author1 =book.getBookAuthor();
				assertEquals(author,author1);
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
