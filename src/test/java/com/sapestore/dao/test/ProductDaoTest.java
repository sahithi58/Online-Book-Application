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
import com.sapestore.exception.SapeStoreException;
import com.sapestore.vo.BookVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml"})
@WebAppConfiguration
public class ProductDaoTest {

	@Autowired
	private ProductDao productDao;
	
	

    @Transactional
	@Rollback(true)
	@Test
	public void testAddNewBooks() {
	
	
		BookVO book = new BookVO();
		book.setBookAuthor("sahithi");
		book.setBookDetailDesc("very very good book");
		book.setBookPrice("122");
		book.setBookShortDesc("nice");
		book.setBookTitle("Harry potter");
		book.setCategoryId("3");
		book.setIsbn("455464556756756575");
		book.setPublisherName("saahithi");
		book.setRentAvailable("Y");
		book.setRentPrice("23");
		book.setQuantity(3);
		book.setActive("Y");
		book.setThumbPath("img/products/Thumbnails/Chrysanthemum.jpg");
		book.setFullPath("img/products/Thumbnails/Chrysanthemum.jpg");
		
	
		try {
			productDao.addNewBooks(book);
	
	BookVO book1 = productDao.getBookDetails("455464556756756575");
		System.out.println(book1.getBookAuthor());
        Assert.assertEquals(book1.getBookAuthor(), book.getBookAuthor());
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}



    @Transactional
   	@Rollback(true)
   	@Test
   	public void testAddNewBooks1() {
   	
   	
   		BookVO book = new BookVO();
   		book.setBookAuthor("sahithi");
   		book.setBookDetailDesc("very very good book");
   		book.setBookPrice("122");
   		book.setBookShortDesc("nice");
   		book.setBookTitle("Harry potter");
   		book.setCategoryId("3");
   		book.setIsbn("455464556756756575");
   		book.setPublisherName("saahithi");
   		book.setRentAvailable("Y");
   		book.setQuantity(3);
   		book.setActive("Y");
   		book.setThumbPath("img/products/Thumbnails/Chrysanthemum.jpg");
   		book.setFullPath("img/products/Thumbnails/Chrysanthemum.jpg");
   		
   	
   		try {
   			productDao.addNewBooks(book);
   	
   	BookVO book1 = productDao.getBookDetails("455464556756756575");
   		System.out.println(book1.getBookAuthor());
           Assert.assertEquals(book1.getBookAuthor(), book.getBookAuthor());
   		}
   		 catch (Exception e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	}
    
}