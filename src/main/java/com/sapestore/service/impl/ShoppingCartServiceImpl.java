package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;

/**
 * Service class for Add to Cart functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ProductDao productdao;

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ShoppingCartServiceImpl.class.getName());

	@Override
	public ShoppingCartVO addToShoppingCart(ShoppingCartVO shoppingCart, String isbn, String isRent)
			throws SapeStoreException {
		LOGGER.debug("addBookToCart method: START");
		BookVO addToCart = productdao.getBookDetails(isbn);
		if (addToCart.getQuantity() == 0) {
			return shoppingCart;
		}
		BookVO existingBookBean = null;
		int bookPosition = 0;
		int cartquantity = 0;

		if (shoppingCart != null) {
			bookPosition = shoppingCart.getBooksInCart().indexOf(addToCart);
			if (bookPosition == -1) {
				addToCart.setCartQuantity(1);
				if (isRent.equals("y"))
					addToCart.setIsRented("y");
				else
					addToCart.setIsRented("n");

				shoppingCart.addtoCart(addToCart);
				shoppingCart = this.calculateTotalPrice(shoppingCart);
				shoppingCart = this.calculateTotalQuantity(shoppingCart);

			} else {
				existingBookBean = shoppingCart.getBooksInCart().remove(bookPosition);
				cartquantity = existingBookBean.getCartQuantity();
				existingBookBean.setCartQuantity(++cartquantity);
				if (isRent.equals("y"))
					existingBookBean.setIsRented("y");
				else
					addToCart.setIsRented("n");
				shoppingCart.addtoCart(existingBookBean);
				shoppingCart = this.calculateTotalPrice(shoppingCart);
				shoppingCart = this.calculateTotalQuantity(shoppingCart);
			}
		} else {
			shoppingCart = new ShoppingCartVO();
			addToCart.setCartQuantity(1);
			if (isRent.equals("y"))
				addToCart.setIsRented("y");
			else
				addToCart.setIsRented("n");
			shoppingCart.addtoCart(addToCart);
			shoppingCart = this.calculateTotalPrice(shoppingCart);
			shoppingCart = this.calculateTotalQuantity(shoppingCart);
		}
		LOGGER.debug("addBookToCart method: END");
		return shoppingCart;
	}

	@Override
	public ShoppingCartVO removeFromShoppingCart(ShoppingCartVO shoppingCart, BookVO book) {
		LOGGER.debug("removeFromShoppingCart method: START");
		BookVO removeFromCart = book;
		int bookPosition = 0;
		bookPosition = shoppingCart.getBooksInCart().indexOf(removeFromCart);
		shoppingCart.getBooksInCart().remove(bookPosition);
		shoppingCart = this.calculateTotalPrice(shoppingCart);
		shoppingCart = this.calculateTotalQuantity(shoppingCart);
		LOGGER.debug("removeFromShoppingCart method: END");
		return shoppingCart;
	}

	@Override
	public ShoppingCartVO updateBookQuantity(ShoppingCartVO shoppingCart, BookVO book, int quantity, String trantype) {
		LOGGER.debug("updateBookQuantity method: START");
		
		if (quantity <= 0) {
			return this.removeFromShoppingCart(shoppingCart, book);
		} else {
			BookVO addToCart = book;				
			BookVO existingBookBean = null;
			int bookPosition = 0;
			bookPosition = shoppingCart.getBooksInCart().indexOf(addToCart);
			
			existingBookBean = shoppingCart.getBooksInCart().remove(bookPosition);
			if (trantype.equals("purchase")) {
				existingBookBean.setIsRented("n");
				existingBookBean.setCartQuantity(quantity);
				shoppingCart.addtoCart(existingBookBean);
				shoppingCart = this.calculateTotalPrice(shoppingCart);
				shoppingCart = this.calculateTotalQuantity(shoppingCart);
			} else {
				existingBookBean.setIsRented("y");
				existingBookBean.setCartQuantity(quantity);
				shoppingCart.addtoCart(existingBookBean);
				shoppingCart = this.calculateTotalPrice(shoppingCart);
				shoppingCart = this.calculateTotalQuantity(shoppingCart);
			}
			/*
			 * int initQuantity = existingBookBean.getCartQuantity();
			 * 
			 * shoppingCart.setTotalQuantity(shoppingCart.getTotalQuantity() -
			 * initQuantity + quantity); shoppingCart.setTotalPrice(
			 * shoppingCart.getTotalPrice() - (initQuantity *
			 * Integer.parseInt(existingBookBean.getBookPrice())) + (quantity *
			 * Integer.parseInt(existingBookBean.getBookPrice())));
			 * existingBookBean.setCartQuantity(quantity);
			 * shoppingCart.addtoCart(existingBookBean);
			 */

		}
		LOGGER.debug("updateBookQuantity method: END");
		return shoppingCart;
	}

	@Override
	public ShoppingCartVO calculateTotalPrice(ShoppingCartVO shoppingcart) {
		int totalPrice = 0;
		for (BookVO book : shoppingcart.getBooksInCart()) {
			if (book.getIsRented().equals("y"))
				totalPrice += book.getCartQuantity() * Integer.parseInt(book.getRentPrice());
			else
				totalPrice += book.getCartQuantity() * Integer.parseInt(book.getBookPrice());
			shoppingcart.setTotalPrice(totalPrice);
		}
		return shoppingcart;
	}

	@Override
	public ShoppingCartVO calculateTotalQuantity(ShoppingCartVO shoppingcart) {
		int totalQuantity = 0;
		for (BookVO book : shoppingcart.getBooksInCart()) {
			totalQuantity += book.getCartQuantity();
		}
		shoppingcart.setTotalQuantity(totalQuantity);
		return shoppingcart;
	}

}
