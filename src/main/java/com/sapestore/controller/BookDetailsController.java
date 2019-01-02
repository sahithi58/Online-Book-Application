


package com.sapestore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;

import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.hibernate.entity.BookCategory;

import com.sapestore.service.BookService;
import com.sapestore.vo.BookDetailsNComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;

//no changes
@Controller
public class BookDetailsController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(HomePageController.class.getName());

	List<BookRatingCommentsVO> commentsList;
	private List<BookCategory> catList;
	@Autowired
	private BookService bookService;

	/*
	 * Book Details comment facade declaration 
	 */
	@Autowired
	private BookDetailsCommentsFacade getAllDetails;
	
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public BookDetailsCommentsFacade getGetAllDetails() {
		return getAllDetails;
	}
	
	/*
	 * Setter method for get all details
	 */
	public void setGetAllDetails(BookDetailsCommentsFacade getAllDetails) {
		this.getAllDetails = getAllDetails;
	}
	
	public List<BookCategory> getCatList() {
		return catList;
	}

	public void setCatList(List<BookCategory> catList) {
		this.catList = catList;
	}

	/*
	 @Autowired 
	 private List<BookCategory> catList;
	 public List<BookCategory> getCatList() { return catList; }
	 public void setCatList(List<BookCategory> catList) { this.catList = catList; }
	 * private List<BookCategory> getCategoryList() throws SapeStoreException {
	 * if (LOGGER.isDebugEnabled()) { LOGGER.debug(
	 * "getCategoryList method: START"); }
	 * List<BookCategory> bookCategoryList = null;
	 * try { bookCategoryList = bookService.getCategoryList();
	 * } catch (SapeStoreSystemException ex) { LOGGER.error(
	 * "getCategoryList method: ERROR: " + ex); return null; }
	 * if (LOGGER.isDebugEnabled()) { LOGGER.debug("getCategoryList method: END"
	 * ); } return bookCategoryList; }
	 */
	


	@RequestMapping(value = "readAll/{isbn}", method = RequestMethod.GET)
	public String readALL(@PathVariable("isbn") String isbn, ModelMap modelMap) throws SapeStoreException {
			
		BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(isbn);
		BookVO bookDetails=bookDetailsNComments.getBookDetails();
		List<BookRatingCommentsVO> bookComments=bookDetailsNComments.getBookComments();
		int ratings=bookDetailsNComments.getRatings();
		/*double averageRating=bookDetailsNComments.getAverageRating();*/
		modelMap.addAttribute("bookDetails", bookDetails);
		/*modelMap.addAttribute("bookComments",bookComments);*/
		modelMap.addAttribute("ratings", ratings);
		/*modelMap.addAttribute("averageRating", averageRating);*/
		modelMap.addAttribute("commentList", bookComments);
		modelMap.addAttribute("catList", bookService.getCategoryList());
		modelMap.addAttribute("commentsCount", bookComments.size());
		return "readallcomment";
	}

	@RequestMapping(value = "readallcomments/{page}/{isbn}", method = RequestMethod.GET)
	public String readALLComments(@PathVariable("page") int page, @PathVariable("isbn") String isbn
									,ModelMap modelMap) throws SapeStoreException {
		
		BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(isbn);
		BookVO bookDetails=bookDetailsNComments.getBookDetails();
		List<BookRatingCommentsVO> commentsList=bookDetailsNComments.getBookComments();
		int ratings=bookDetailsNComments.getRatings();

		
		List<BookRatingCommentsVO> newCommentList = new ArrayList<BookRatingCommentsVO>();
		
		if(page == 1){
			for(int i = 0; i< (10); i++){
				if(i < commentsList.size()){
					BookRatingCommentsVO comment = commentsList.get(i);
					newCommentList.add(comment);
				}
			}
		}
		else{
			int limiter = (page-1)*10;
			for(int i = limiter; i< (limiter +10); i++){
				if(i < commentsList.size()){
					BookRatingCommentsVO comment = commentsList.get(i);
					newCommentList.add(comment);
				}
			}
		}
		
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("commentsCount", commentsList.size());
		modelMap.addAttribute("newcommentsCount", newCommentList.size());
		modelMap.addAttribute("bookDetails", bookDetails);
		/*modelMap.addAttribute("bookComments",bookComments);*/
		modelMap.addAttribute("ratings", ratings);
		/*modelMap.addAttribute("averageRating", averageRating);*/
		modelMap.addAttribute("commentList", newCommentList);
		modelMap.addAttribute("catList", bookService.getCategoryList());
		return "readallcomment";

	}
}