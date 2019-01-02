/*package com.sapestore.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.service.impl.ShoppingCartServiceImpl;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;

public class ShoppingCartServiceTest {
	ShoppingCartService cartService = null;
	ShoppingCartVO cart = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		cartService = new ShoppingCartServiceImpl();
		cart = new ShoppingCartVO();
		BookVO book1 = new BookVO();
		book1.setIsbn("rty");
		book1.setQuantity(12);
		book1.setBookPrice("250");
		BookVO book2 = new BookVO();
		book2.setIsbn("var");
		book2.setQuantity(10);
		book2.setBookPrice("300");
		cart.setBooksInCart(book1);
		cart.setBooksInCart(book2);
		BookVO book3 = new BookVO();
		book3.setIsbn("upd");
		book3.setQuantity(5);
		book3.setBookPrice("300");
		cart.setBooksInCart(book3);

	}

	@After
	public void tearDown() throws Exception {
		cartService = null;
		cart = null;
	}

	@Test
	public void testAddNewToShoppingCart() throws SapeStoreException {
		BookVO book = new BookVO();
		book.setIsbn("xyzzy");
		book.setQuantity(15);
		book.setBookPrice("250");
		cart = cartService.addToShoppingCart(cart, "19");
		int position = cart.getBooksInCart().indexOf(book);
		BookVO cartBook = cart.getBooksInCart().get(position);
		assertEquals(book, cartBook);

	}

	@Test
	public void testAddExistingToShoppingCart() throws SapeStoreException {
		BookVO book = new BookVO();
		book.setIsbn("rty");
		book.setQuantity(12);
		book.setBookPrice("250");
		cart = cartService.addToShoppingCart(cart, "12");
		book.setQuantity(13);
		int position = cart.getBooksInCart().indexOf(book);
		BookVO cartBook = cart.getBooksInCart().get(position);
		assertEquals(book, cartBook);

	}

	@Test
	public void testRemoveFromShoppingCart() {
		BookVO book2 = new BookVO();
		book2.setIsbn("var");
		book2.setQuantity(10);
		book2.setBookPrice("300");
		cart = cartService.removeFromShoppingCart(cart, book2);
		assertEquals(-1, cart.getBooksInCart().indexOf(book2));
	}

	@Test
	public void testUpdateBookQuantity() {		
		BookVO book3 = new BookVO();
		book3.setIsbn("upd");
		book3.setQuantity(5);
		book3.setBookPrice("300");
		cart = cartService.updateBookQuantity(cart, book3, 3);
		book3.setQuantity(3);
		int position = cart.getBooksInCart().indexOf(book3);
		BookVO checkBook = cart.getBooksInCart().get(position);
		assertEquals(book3.getQuantity(), checkBook.getQuantity());

	}

	@Test
	public void testAddtoEmptyCart() throws SapeStoreException {
		BookVO book = new BookVO();
		book.setIsbn("rty");
		book.setQuantity(12);
		book.setBookPrice("250");
		ShoppingCartVO cart1 = cartService.addToShoppingCart(null, "12");
		int size = cart1.getBooksInCart().size();
		assertEquals(1, size);
	}

	@Test
	public void testFirstBookinCart() throws SapeStoreException {
		BookVO book = new BookVO();
		book.setIsbn("rty");
		book.setQuantity(12);
		book.setBookPrice("250");
		ShoppingCartVO cart1 = cartService.addToShoppingCart(new ShoppingCartVO(), "12");
		assertEquals(1, cart1.getBooksInCart().size());
	}
	
	@Test
	public void testUpdateBookQuantity0() {		
		BookVO book3 = new BookVO();
		book3.setIsbn("var");
		book3.setQuantity(10);
		book3.setBookPrice("300");
		cart = cartService.updateBookQuantity(cart, book3, 0);
		int position = cart.getBooksInCart().indexOf(book3);		
		assertEquals(-1, position);

	}

}
*/