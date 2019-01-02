/*package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.service.BookService;
import com.sapestore.service.ReviewService;
import com.sapestore.vo.BookDetailsNComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;

@Controller
@SessionAttributes("userId")
public class CommentsRatingsController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(CommentsRatingsController.class.getName());

	@Autowired
	ReviewService reviewservice;
	
	String Isbn,userId;
	
	@Autowired
	private BookDetailsCommentsFacade getAllDetails;
	
	@Autowired
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BookDetailsCommentsFacade getGetAllDetails() {
		return getAllDetails;
	}

	public void setGetAllDetails(BookDetailsCommentsFacade getAllDetails) {
		this.getAllDetails = getAllDetails;
	}

	BookRatingCommentsVO bookRatingCommentsVO = new BookRatingCommentsVO();

	@RequestMapping(value = "/reviewcontroller/{isbn}", method = RequestMethod.POST)
	public String addReview(@PathVariable("isbn") String isbn, @RequestParam("rating") Integer rating,
			@RequestParam("comment") String comment, ModelMap modelMap, HttpSession session, HttpServletRequest reuest)
			throws SapeStoreException {
		String userId = (String) session.getAttribute("userId");

		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("post comments: START");
			}

			if (userId == null) {
				return "login";
			} else {
				bookRatingCommentsVO.setBookComments(comment);
				bookRatingCommentsVO.setBookRating(rating);
				bookRatingCommentsVO.setUserId(userId);
				bookRatingCommentsVO.setIsbn(isbn);
				reviewservice.addReview(bookRatingCommentsVO);
				return "bookDetails";
			}
		} catch (SapeStoreSystemException e) {
			LOGGER.error("Error in adding comments: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
			return "error";

		}
	}
	@RequestMapping(value = "/postcomment")
	public String postComment(@RequestParam(value = "book.isbn") String isbn,
			@RequestParam(value = "book.bookTitle") String bookTitle,
			@RequestParam(value = "book.bookAuthor") String bookAuthor,
			@RequestParam(value = "book.thumbPath") String thumbPath,
			ModelMap modelmap , HttpSession session){
		
		userId = (String) session.getAttribute("userId");
		if(userId==null){
			return "login";
		}else{
		modelmap.addAttribute("isbn",isbn);
		modelmap.addAttribute("bookTitle",bookTitle);
		modelmap.addAttribute("bookAuthor",bookAuthor);
		modelmap.addAttribute("thumbPath",thumbPath);
		Isbn = isbn;
		return "Comment";
		}
		
	}
	@RequestMapping(value = "/reviewcontroller", method = RequestMethod.POST)
	public String addReview(@RequestParam("rating") Integer rating,
			@RequestParam("comment") String comment, ModelMap modelMap, HttpSession session, HttpServletRequest reuest)
			throws SapeStoreException {
		String userId = (String) session.getAttribute("userId");

		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("post comments: START");
			}
			BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(Isbn);
			BookVO bookDetails=bookDetailsNComments.getBookDetails();
			List<BookRatingCommentsVO> bookComments=bookDetailsNComments.getBookComments();
			int ratings=bookDetailsNComments.getRatings();
			modelMap.addAttribute("bookDetails", bookDetails);
			modelMap.addAttribute("ratings", ratings);
			modelMap.addAttribute("bookComments", bookComments);
			modelMap.addAttribute("catList", bookService.getCategoryList());
			modelMap.addAttribute("commentsCount", bookComments.size());
			
				bookRatingCommentsVO.setBookComments(comment);
				bookRatingCommentsVO.setBookRating(rating);
				bookRatingCommentsVO.setUserId(userId);
				bookRatingCommentsVO.setIsbn(Isbn);
				reviewservice.addReview(bookRatingCommentsVO);
				return "bookDetails";
		
		} catch (SapeStoreSystemException e) {
			LOGGER.error("Error in adding comments: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
			return "error";

		}
	}
	@RequestMapping(value = "/cancelreviews/{isbn}")
	public String cancelReviews(@PathVariable("isbn") String isbn,ModelMap modelMap) throws SapeStoreException {
		
		BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(isbn);
		BookVO bookDetails=bookDetailsNComments.getBookDetails();
		List<BookRatingCommentsVO> bookComments=bookDetailsNComments.getBookComments();
		int ratings=bookDetailsNComments.getRatings();
		double averageRating=bookDetailsNComments.getAverageRating();
		modelMap.addAttribute("bookDetails", bookDetails);
		modelMap.addAttribute("bookComments",bookComments);
		modelMap.addAttribute("ratings", ratings);
		modelMap.addAttribute("averageRating", averageRating);
		modelMap.addAttribute("bookComments", bookComments);
		modelMap.addAttribute("catList", bookService.getCategoryList());
		modelMap.addAttribute("commentsCount", bookComments.size());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("cancel reviews: START");
		}

		return "bookDetails";
	}
}
*/

package com.sapestore.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.service.BookService;
import com.sapestore.service.ReviewService;
import com.sapestore.vo.BookDetailsNComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;

@Controller
@SessionAttributes("userId")
public class CommentsRatingsController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(CommentsRatingsController.class.getName());

	@Autowired
	ReviewService reviewservice;
	
	
	@Autowired
	private BookDetailsCommentsFacade getAllDetails;
	
	@Autowired
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BookDetailsCommentsFacade getGetAllDetails() {
		return getAllDetails;
	}

	public void setGetAllDetails(BookDetailsCommentsFacade getAllDetails) {
		this.getAllDetails = getAllDetails;
	}

	BookRatingCommentsVO bookRatingCommentsVO = new BookRatingCommentsVO();

	
	@RequestMapping(value = "/reviewcontroller/{isbn}", method = RequestMethod.POST)
	public String addReview(@PathVariable("isbn") String isbn, @RequestParam("rating") Integer rating,
			@RequestParam("comment") String comment, ModelMap modelMap, HttpSession session, HttpServletRequest reuest)
			throws SapeStoreException {
		String userId = (String) session.getAttribute("userId");
		if (userId == null) {
			return "login";
		}

		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("post comments: START");
			}
			
			
				bookRatingCommentsVO.setBookComments(comment);
				bookRatingCommentsVO.setBookRating(rating);
				bookRatingCommentsVO.setUserId(userId);
				bookRatingCommentsVO.setIsbn(isbn);
				reviewservice.addReview(bookRatingCommentsVO);
				BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(isbn);
				BookVO bookDetails=bookDetailsNComments.getBookDetails();
				List<BookRatingCommentsVO> bookComments=bookDetailsNComments.getBookComments();
				int ratings=bookDetailsNComments.getRatings();
				modelMap.addAttribute("bookDetails", bookDetails);
				modelMap.addAttribute("ratings", ratings);
				modelMap.addAttribute("bookComments", bookComments);
				modelMap.addAttribute("catList", bookService.getCategoryList());
				modelMap.addAttribute("commentsCount", bookComments.size());
				/*return RedirectToAction("LogIn", "Account", isbn = new String(isbn));*/
				return "bookDetails"; 
		
		} catch (SapeStoreSystemException e) {
			LOGGER.error("Error in adding comments: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
			return "error";

		}
	}
	@RequestMapping(value = "/cancelreviews/{isbn}")
	public String cancelReviews(@PathVariable("isbn") String isbn,ModelMap modelMap) throws SapeStoreException {
		
		BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(isbn);
		BookVO bookDetails=bookDetailsNComments.getBookDetails();
		List<BookRatingCommentsVO> bookComments=bookDetailsNComments.getBookComments();
		int ratings=bookDetailsNComments.getRatings();
		
		modelMap.addAttribute("bookDetails", bookDetails);
	
		modelMap.addAttribute("ratings", ratings);
	
		modelMap.addAttribute("bookComments", bookComments);
		modelMap.addAttribute("catList", bookService.getCategoryList());
		modelMap.addAttribute("commentsCount", bookComments.size());
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("cancel reviews: START");
		}

		return "bookDetails";
	}
}
