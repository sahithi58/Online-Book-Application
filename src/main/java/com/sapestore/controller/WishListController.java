package com.sapestore.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.WishList;
import com.sapestore.service.BookService;
import com.sapestore.service.WishListService;
import com.sapestore.vo.BookDetailsNComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.HomeVO;
import com.sapestore.vo.UserVO;

@Controller
@SessionAttributes(value = { "userId", "message","addbook" })
public class WishListController {
	private List<BookVO> bookList;
	private List<BookCategory> catList;
	private String categoryName;
	private boolean checkMe;
	@Autowired
	WishListService wishListService;
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ProductDao productDao;
	

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	private final static SapeStoreLogger LOGGER = SapeStoreLogger
			.getLogger(WishListController.class.getName());
	public boolean isCheckMe() {
		return checkMe;
	}

	public void setCheckMe(boolean checkMe) {
		this.checkMe = checkMe;
	}

	public List<BookVO> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookVO> bookList) {
		this.bookList = bookList;
	}

	public List<BookCategory> getCatList() {
		return catList;
	}

	public void setCatList(List<BookCategory> catList) {
		this.catList = catList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	List<BookRatingCommentsVO> commentsList;
	BookVO bookDetails;

	@Autowired
	private BookDetailsCommentsFacade getAllDetails;
	
	public BookDetailsCommentsFacade getGetAllDetails() {
		return getAllDetails;
	}

	public void setGetAllDetails(BookDetailsCommentsFacade getAllDetails) {
		this.getAllDetails = getAllDetails;
	}
	
	@RequestMapping(value = "/mycart/addToWishList")
	public String addToShoppingCart(@RequestParam(value = "book.isbn") String isbn, ModelMap modelMap,
			HttpSession session, HttpServletResponse response) throws Exception {
       
		

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		// retireve the userId from session attribute
		String userId = (String) session.getAttribute("userId");
		System.out.println("in the controller");

		WishList wishlist = new WishList();
		wishlist.setUserId(userId);
		wishlist.setIsbn(isbn);

		boolean flag = wishListService.addWishList(wishlist);

		if (flag) {

			out.print("Book is already added to wishlist");

			// modelMap.addAttribute("message","Book is already added to
			// wishlist");
		} else {
			out.print("Book is added to wishlist");
			// modelMap.addAttribute("message","Book is added to wishlist");
		}

		return "redirect:/bookDetails?isbn=" + isbn;

	}

	@RequestMapping(value = "/mycart/wishlist")
	public String wishlistpage(@ModelAttribute("welcome") HomeVO welcome,@RequestParam(value="checkMe",required=false) boolean checkMe,
			@RequestParam(value="page",required=false) String pageString,
			ModelMap modelMap,HttpServletRequest httpServletRequest, HttpSession session) throws Exception {
		String userId = (String) session.getAttribute("userId"); 
		//for side navigation
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: START");
		}
		this.setCheckMe(welcome.isCheckMe());
		List<BookVO> bookList = new ArrayList<BookVO>(); 

		try {
			this.setCatList(getCategoryList());
			//Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = wishListService.wishlistbooks(userId);
			
            /*if (checkMeFromSession!=null && (boolean) checkMeFromSession) {
            	bookList.addAll(bookService.getBookFromWebService(0));
            }*/

			List<BookVO> newBookList = new ArrayList<BookVO>();
			int page = 1;
			if(pageString != null){
				page = Integer.parseInt(pageString);
				int limiter = (page-1)*2 ;
				for(int i = limiter; i< (limiter + 2); i++){
					if(i < bookList.size()){
						BookVO book = bookList.get(i);
						newBookList.add(book);
					}
				}
			}else{
				for(int i = 0; i< 2; i++){
					if(i < bookList.size()){
						BookVO book = bookList.get(i);
						newBookList.add(book);
					}
				}
			}
			
			
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newBooksCount", newBookList.size());
			modelMap.addAttribute("booksCount", bookList.size());
			this.setBookList(newBookList);
			modelMap.addAttribute("addbook", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			
		} catch (SapeStoreSystemException e) {
			LOGGER.error("welcome method: ERROR: " + e);
			modelMap.addAttribute("errorMessage",
					"Error in opening the welcome page.");
			return "redirect:/errorPage";
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: ModelMap: " + modelMap);
			LOGGER.debug("welcome method: END");
		}
		//nav code ends
 
		//BookVO bookvo=new BookVO();
		System.out.println("in the controller1 wishlist");
	
		WishList wishlist = new WishList();
		wishlist.setUserId(userId);
		
		return "wishlist";

	}
	private List<BookCategory> getCategoryList() throws SapeStoreException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getCategoryList method: START");
		}

		List<BookCategory> bookCategoryList = null;

		try {
			bookCategoryList = bookService.getCategoryList();

		} catch (SapeStoreSystemException ex) {
			LOGGER.error("getCategoryList method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getCategoryList method: END");
		}
		return bookCategoryList;
	}

/*	private List<BookVO> getBooksList(Object checkMeFromSession) throws SapeStoreException{
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: START");
		}
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			try {
				bookList = bookService.getBookList(0,checkMeFromSession);
			} catch (SapeStoreSystemException e) {
				
				LOGGER.error("getBooksList method: ERROR: " + e);
				
			}
			this.setBookList(bookList);
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("welcome method: ERROR: " + ex);
			return null;
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: END");
		}
		return bookList;
	}*/
}
