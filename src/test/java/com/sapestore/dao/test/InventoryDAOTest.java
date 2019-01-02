package com.sapestore.dao.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.GregorianCalendar;

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

import com.sapestore.dao.InventoryDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class InventoryDAOTest {

	@Autowired
	InventoryDao test = null;

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

	@Transactional
	@Rollback(true)
	
	@Test
	public void testSetBookDetailsBean() {
		List<BookVO> listBookVO = null;
		Book testbook = new Book();
		
		BookCategory testBookCategory = new BookCategory();
		testBookCategory.setCategoryId(564);
		testBookCategory.setCategoryName("Messed Up thing");
		
		testbook.setBookCategory(testBookCategory );
		BigDecimal testnum1 = new BigDecimal(12);
		testbook.setAverageRating(testnum1);
		testbook.setBookAuthor("rahul tester");
		testbook.setBookDetailDescription("Bla Bla bla");
		testbook.setBookFullImage("Lool");
		BigDecimal testnum2 = new BigDecimal(12);
		testbook.setBookPrice(testnum2);
		testbook.setBookShortDescription("sdgsrg");
		testbook.setBookThumbImage("sdgsdgsdgf");
		testbook.setBookTitle("test book");
		Date testdate1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
		testbook.setCreatedDate(testdate1);
		testbook.setIsActive("active");
		testbook.setIsbn("45353");
		testbook.setIsFromPartnerStore("maybe");
		BigDecimal testnum3 = new BigDecimal(82);
		testbook.setLateFee(testnum3);
		testbook.setPublisherName("pingu");
		testbook.setQuantity(9);
		testbook.setRentAvailability("Y");
		BigDecimal testnum4 = new BigDecimal(82);
		testbook.setRentPrice(testnum4);
		Date testdate2 = new GregorianCalendar(2003, Calendar.FEBRUARY, 11).getTime();
		testbook.setUpdatedDate(testdate2);
		
		
		
		List<Book> listBook1 = new ArrayList<>();
		listBook1.add(testbook);
		for(Book iterator1 : listBook1){
			System.out.println("Book List :" +iterator1.getBookAuthor());
		}
			
		listBookVO = test.setBookDetailsBean(listBook1);
		for(BookVO iterator : listBookVO){
			System.out.println("Book VO list "+iterator);
		}
		
		BookVO vo = listBookVO.get(0);
		System.out.println(listBookVO.get(0));
		System.out.println(vo);
		
		if(vo.getBookAuthor().equals(testbook.getBookAuthor())){
			System.out.println("PASS");
		}
		else{
			System.out.println("FAIL");
		}
		
	}

	@Test
	public void testGetAdminInventoryCategory() {
		try {
			listBook = test.getAdminInventoryCategory(1);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());

	}

	@Test
	public void testGetAdminInventoryBookName() {
		try {
			listBook = test.getAdminInventoryBookName(1);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}

	@Test
	public void testGetAdminInventoryAuthor() {
		try {
			listBook = test.getAdminInventoryAuthor(1);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}

	@Test
	public void testGetAdminInventoryPublisher() {
		try {
			listBook = test.getAdminInventoryPublisher(1);
		} catch (SapeStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}
	
	@Test
	public void testGetAdminInventoryQuantity() {
		try {
			listBook = test.getAdminInventoryQuantity(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, listBook.size());
	}
}
