package com.sapestore.dao.test;

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
import com.sapestore.hibernate.entity.Search;
import com.sapestore.service.BookService;
import com.sapestore.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-application-context.xml" })
@WebAppConfiguration
public class BookSearchTest {
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private BookService bookService;

	 
	@Test 
public void testGetBookSearchList() throws SapeStoreException {
		Search search=new Search() ;
		search.setBookAuthor("R. K. Narayanan");
		search.setBookTitle("Guide");
		search.setCategoryId(8);
		search.setPublisher("Star Publication");
	List<BookVO> books = productDao.getBookListBySearch(search, true, true);
	assertEquals(1, books.size());
	for (BookVO bookVo : books) {
		assertEquals(search.getBookAuthor(), bookVo.getBookAuthor());
		assertEquals(search.getBookTitle(), bookVo.getBookTitle());
		//assertEquals(search.getCategoryId(), Integer.parseInt(bookVo.getCategoryId()));
		
		}
		} 
	
	@Test 
	public void testGetBookSearchList2() throws SapeStoreException {
			Search search=new Search() ;
			search.setBookAuthor("zxz");
			search.setBookTitle("asad");
			search.setCategoryId(6);
			search.setPublisher("Kluwer");
		List<BookVO> books = productDao.getBookListBySearch(search, false, true);
		//assertEquals(1, books.size());
		for (BookVO bookVo : books) {
			assertEquals(search.getBookAuthor(), bookVo.getBookAuthor());
			assertEquals(search.getBookTitle(), bookVo.getBookTitle());
			//assertEquals(search.getCategoryId(), Integer.parseInt(bookVo.getCategoryId()));
			
			}
			} 
		

	
	/*@Test
		public void testGetBookSearchListService() throws SapeStoreException {
			Search search=new Search() ;
			search.setBookAuthor("R. K. Narayanan");
			search.setBookTitle("Guide");
			search.setCategoryId(8);
			search.setPublisher("Star Publication");
		List<BookVO> books = bookService.getBookListBySearch(search, false, false);
		//assertEquals(1, books.size()); 
	for (BookVO bookVo : books) {
		assertEquals(search.getBookAuthor(), bookVo.getBookAuthor());
		assertEquals(search.getBookTitle(), bookVo.getBookTitle());
	//	assertEquals(search.getCategoryId(), Integer.parseInt(bookVo.getCategoryId()));
		
		}
		}*/
}