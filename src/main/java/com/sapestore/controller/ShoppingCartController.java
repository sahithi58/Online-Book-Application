package com.sapestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.ShoppingCartService;
import com.sapestore.service.WishListService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.ShoppingCartVO;

/**
 * This is a controller class for the shopping cart.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
@SessionAttributes("ShoppingCart")
public class ShoppingCartController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ShoppingCartController.class.getName());

	@Autowired(required = true)
	public ShoppingCartService shoppingCartService;

	@Autowired
	public WishListService wishlist;

	/**
	 * Processes the Add to Cart requests.
	 * 
	 * @param categoryId
	 * @param categoryName
	 * @param checkMe
	 * @param isbn
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/mycart/addToShoppingCart")
	public String addToShoppingCart(@RequestParam(value = "book.isbn") String isbn,
			@RequestParam(value = "book.categoryId", required = false) String categoryId,
			@RequestParam(value = "book.categoryName", required = false) String categoryName,
			@RequestParam(value = "book.isRent", required = false) String isRent,
			/*@RequestParam(value = "page", required = false) String page,*/
			@RequestParam(value = "checkMe", required = false) boolean checkMe, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws Exception {

		BookVO bookvo = new BookVO();
		bookvo.setIsbn(isbn);
		bookvo.setCategoryId(categoryId);
		bookvo.setCategoryName(categoryName);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addToShoppingCart method: START");
		}
		ShoppingCartVO shoppingCart = null;
		String forwardStr = null;

		try {

			shoppingCart = (ShoppingCartVO) httpSession.getAttribute("ShoppingCart");
			shoppingCart = shoppingCartService.addToShoppingCart(shoppingCart, isbn, isRent);
			httpSession.setAttribute("ShoppingCart", shoppingCart);

			if (!(categoryId == null)) {
				if (bookvo.getCategoryName().equalsIgnoreCase("Top Rated")) {
					forwardStr = "redirect:/bookListByCat?categoryId=0&categoryName=Top Rated&checkMe="
							+ httpSession.getAttribute("checkMe");
				} else {
					forwardStr = "redirect:/bookListByCat?categoryId=" + bookvo.getCategoryId() + "&categoryName="
							+ bookvo.getCategoryName() +  "&checkMe="
							+ httpSession.getAttribute("checkMe");
				}
				return forwardStr;
			}
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("addToShoppingCart method: ERROR: " + ex);
			forwardStr = ApplicationConstants.FAILURE;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addToShoppingCart method: END");
		}

		return "redirect:/welcome";

	}

	@RequestMapping(value = "/removeFromShoppingCart", method = RequestMethod.GET)
	public String removeFromShoppingCart(@RequestParam(value = "book.isbn") String isbn,
			@RequestParam(value = "book.categoryId", required = false) String categoryId,
			@RequestParam(value = "book.categoryName", required = false) String categoryName,
			@RequestParam(value = "checkMe", required = false) boolean checkMe, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws Exception {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removeFromShoppingCart method: START");
		}

		BookVO bookvo = new BookVO();
		bookvo.setIsbn(isbn);
		bookvo.setCategoryId(categoryId);
		bookvo.setCategoryName(categoryName);
		/* bookvo.setQuantity(1); */

		ShoppingCartVO shoppingCart = (ShoppingCartVO) httpSession.getAttribute("ShoppingCart");
		shoppingCart = shoppingCartService.removeFromShoppingCart(shoppingCart, bookvo);
		httpSession.setAttribute("ShoppingCart", shoppingCart);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removeFromShoppingCart method: END");
		}

		return "redirect:/welcome";

	}

	@RequestMapping(value = "/mycart/updateBookQuantity", method = RequestMethod.GET)
	public String updateBookQuantity(@RequestParam(value = "book.isbn") String isbn,
			@RequestParam(value = "trantype", required = false) String trantype, @RequestParam("quantity") int quantity,
			@RequestParam(value = "checkMe", required = false) boolean checkMe, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws Exception {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateBookQuantity method: START");
		}

		BookVO bookvo = new BookVO();
		bookvo.setIsbn(isbn);

		ShoppingCartVO shoppingCart = (ShoppingCartVO) httpSession.getAttribute("ShoppingCart");

		shoppingCart = shoppingCartService.updateBookQuantity(shoppingCart, bookvo, quantity, trantype);
		httpSession.setAttribute("ShoppingCart", shoppingCart);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("updateBookQuantity method: END");
		}

		return "cartQuantity";

	}

	@RequestMapping(value = "/mycart/continueShopping", method = RequestMethod.GET)
	public String continueShopping(@RequestParam(value = "checkMe", required = false) boolean checkMe,
			HttpServletRequest httpServletRequest, HttpSession httpSession) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("continueShopping method: START");
		}

		// httpSession.setAttribute("shoppingCart", shoppingCart);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("continueShopping method: END");
		}

		return "redirect:/welcome";

	}

	@RequestMapping(value = "/mycart/checkoutCart", method = RequestMethod.GET)
	public String checkoutCart(@RequestParam(value = "checkMe", required = false) boolean checkMe,
			HttpServletRequest httpServletRequest, HttpSession httpSession) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("checkoutCart method: START");
		}

		// httpSession.getAttribute("ShoppingCart");

		/*
		 * BookVO book = new BookVO();
		 * 
		 * book.setIsbn("xyz"); book.setBookTitle("How to Cart");
		 * book.setBookAuthor("herbert"); book.setQuantity(2);
		 * book.setPublisherName("mcgraw");
		 * book.setThumbPath("img/products/Thumbnails/1.jpg");
		 * book.setCategoryId("12"); book.setCategoryName("dsjfl");
		 * book.setBookPrice("250"); book.setRentPrice("100");
		 * 
		 * ShoppingCartVO scart = new ShoppingCartVO();
		 * scart.setBooksInCart(book);
		 * 
		 * httpSession.setAttribute("ShoppingCart", scart);
		 */

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("checkoutCart method: END");
		}

		return "redirect:/myorder/displayShippingAddressForm"; // url of jsp
																// from other
																// module

	}

	@RequestMapping(value = "/mycart/addToShoppingCarts")
	public String addToShoppingCartandRemovefromWishlist(@RequestParam(value = "book.isbn") String isbn,
			@RequestParam(value = "book.categoryId", required = false) String categoryId,
			@RequestParam(value = "book.isRent", required = false) String isRent,
			@RequestParam(value = "book.categoryName", required = false) String categoryName,
			@RequestParam(value = "page", required = false) String page,

			@RequestParam(value = "checkMe", required = false) boolean checkMe, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws Exception {

		BookVO bookvo = new BookVO();
		bookvo.setIsbn(isbn);
		bookvo.setCategoryId(categoryId);
		bookvo.setCategoryName(categoryName);
		bookvo.setQuantity(1);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addToShoppingCart method: START");
		}
		ShoppingCartVO shoppingCart = null;
		String forwardStr = null;

		try {

			shoppingCart = (ShoppingCartVO) httpSession.getAttribute("ShoppingCart");
			shoppingCart = shoppingCartService.addToShoppingCart(shoppingCart, isbn, isRent);
			httpSession.setAttribute("ShoppingCart", shoppingCart);

			/*if (!(categoryName == null)) {
				if (bookvo.getCategoryName().equalsIgnoreCase("Top Rated")) {
					forwardStr = "redirect:/bookListByCat?categoryId=0&categoryName=Top Rated&checkMe="
							+ httpSession.getAttribute("checkMe");
				} else {
					forwardStr = "redirect:/bookListByCat?categoryId=" + bookvo.getCategoryId() + "&categoryName="
							+ bookvo.getCategoryName() + "&page=" + page + "&checkMe="
							+ httpSession.getAttribute("checkMe");
				}
				return forwardStr;
			}*/
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("addToShoppingCart method: ERROR: " + ex);
			forwardStr = ApplicationConstants.FAILURE;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addToShoppingCart method: END");
		}

		wishlist.deleteBook(isbn);
		return "redirect:/mycart/wishlist";

	}

}