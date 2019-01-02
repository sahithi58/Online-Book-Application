package com.sapestore.service;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;

/**
 * Service interface for Add to Cart functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public interface ShoppingCartService {

	ShoppingCartVO addToShoppingCart(ShoppingCartVO shoppingCart, String isbn, String isRent) throws SapeStoreException;

	ShoppingCartVO removeFromShoppingCart(ShoppingCartVO shoppingCart, BookVO book);

	ShoppingCartVO updateBookQuantity(ShoppingCartVO shoppingCart, BookVO book, int quantity, String trantype);
	
	ShoppingCartVO calculateTotalPrice(ShoppingCartVO shoppingcart);
	
	ShoppingCartVO calculateTotalQuantity(ShoppingCartVO shoppingcart);

}
