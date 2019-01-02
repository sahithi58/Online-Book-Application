package com.sapestore.service.test;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.sapestore.dao.ProductDao;
import com.sapestore.service.InventoryService;
import com.sapestore.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class InventoryServiceTest {
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	InventoryService servicetest=null;

	List<BookVO> listBook = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAdminInventoryCategory() {
		try {
			listBook = servicetest.getAdminInventoryCategory(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());

	}

	@Test
	public void testGetAdminInventoryBookName() {
		try {
			listBook = servicetest.getAdminInventoryBookName(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}

	@Test
	public void testGetAdminInventoryAuthor() {
		try {
			listBook = servicetest.getAdminInventoryAuthor(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}

	@Test
	public void testGetAdminInventoryPublisher() {
		try {
			listBook = servicetest.getAdminInventoryPublisher(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}

	@Test
	public void testGetAdminInventoryQuantity() {
		try {
			listBook = servicetest.getAdminInventoryQuantity(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}
	
	@Transactional
	@Rollback(true)
	@Test
	public void testUpdateBooks() {
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
			servicetest.updateBooks(book);
			BookVO book1 = productDao.getBookDetails("4534546");
		//	System.out.println(book1.getBookAuthor());
			Assert.assertEquals(book1.getBookPrice(), book.getBookPrice());
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
}
