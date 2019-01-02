package com.sapestore.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Bean class for keeping shopping cart information.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public class ShoppingCartVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<BookVO> booksInCart = new ArrayList<>();
	private int totalPrice;
	private int totalQuantity;
	private String returnDate;

	

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public ShoppingCartVO() {
		super();
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 15); // Adding 5 days
		String output = DATE_FORMAT.format(c.getTime());
		System.out.println(output);
		
		 this.setReturnDate(output);
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	/**
	 * Add book to the list of books in shopping cart
	 * 
	 * @param book
	 */

	public void addtoCart(BookVO book) {
		this.booksInCart.add(book);
	}

	public void reducePriceQuantity(BookVO book) {
		this.totalPrice -= Integer.parseInt(book.getBookPrice());
		this.totalQuantity -= book.getQuantity();
	}

	/**
	 * 
	 * @return the list of books in shopping cart.
	 */
	public List<BookVO> getBooksInCart() {
		return booksInCart;
	}

	/**
	 * 
	 * @return totalPrice.
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * 
	 * @return totalQuantity.
	 */
	public int getTotalQuantity() {
		return totalQuantity;
	}

	@Override
	public String toString() {
		return "ShoppingCartVO [booksInCart=" + booksInCart + ", totalPrice=" + totalPrice + ", totalQuantity="
				+ totalQuantity + "]";
	}

}
