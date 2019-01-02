package com.sapestore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.Search;
import com.sapestore.service.BookService;
import com.sapestore.vo.UserVO;
import com.sapestore.vo.BookDetailsNComments;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.HomeVO;

/**
 * This is a controller class for landing and post customer login pages.
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */

@Controller
@SessionAttributes("checkMe")
public class HomePageController {

	private List<BookVO> bookList;
	private List<BookCategory> catList;
	private String categoryName;
	private boolean checkMe;

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
			.getLogger(HomePageController.class.getName());

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
	
	/**
	 * Processes the home page requests.
	 * @param checkMe
	 * @param modelMap
	 * @return
	 * @throws SapeStoreSystemException
	 */
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(@ModelAttribute("welcome") HomeVO welcome,@RequestParam(value="checkMe",required=false) boolean checkMe,
			@RequestParam(value="page",required=false) String pageString,
			ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession) throws SapeStoreException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: START");
		}
		this.setCheckMe(welcome.isCheckMe());
		List<BookVO> bookList = new ArrayList<BookVO>(); 

		try {
			this.setCatList(getCategoryList());
			//Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(this.isCheckMe());
			
            /*if (checkMeFromSession!=null && (boolean) checkMeFromSession) {
            	bookList.addAll(bookService.getBookFromWebService(0));
            }*/

			List<BookVO> newBookList = new ArrayList<BookVO>();
			int page = 1;
			if(pageString != null){
				page = Integer.parseInt(pageString);
				int limiter = (page-1)*15 ;
				for(int i = limiter; i< (limiter + 15); i++){
					if(i < bookList.size()){
						BookVO book = bookList.get(i);
						newBookList.add(book);
					}
				}
			}else{
				for(int i = 0; i< 15; i++){
					if(i < bookList.size()){
						BookVO book = bookList.get(i);
						newBookList.add(book);
					}
				}
			}
			
			
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newBooksCount", newBookList.size());
			modelMap.addAttribute("booksCount", bookList.size());
			modelMap.addAttribute("checkMeJs", false);
			this.setBookList(newBookList);
			this.setCategoryName("Top Rated");
			modelMap.addAttribute("bookList", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			if(httpSession.getAttribute("checkMe")!=null){
				modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
			}
			else
			{
				modelMap.addAttribute("checkMe",false);
			}
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());
			
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

		return "index";
	}
    
	@RequestMapping(value = "/searchPage", method = RequestMethod.GET)
	public String searchPage(@RequestParam(value="checkMe",required=false) boolean checkMe,
			@RequestParam(value="page",required=false) String pageString,
			ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession) throws SapeStoreException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("searchPage method: START");
		}
		
		List<BookVO> bookList = null; 

		try {
			this.setCatList(getCategoryList());
			Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(checkMeFromSession);
			
            /*if (checkMeFromSession!=null && (boolean) checkMeFromSession) {
            	bookList.addAll(bookService.getBookFromWebService(0));
            }*/
			
			List<BookVO> newBookList = new ArrayList<BookVO>();
			int page = 1;
			if(pageString != null){
				page = Integer.parseInt(pageString);
				int limiter = (page-1)*10 ;
				for(int i = limiter; i< (limiter + 10); i++){
					if(i < bookList.size()){
						BookVO book = bookList.get(i);
						newBookList.add(book);
					}
				}
			}else{
				for(int i = 0; i< 10; i++){
					if(i < bookList.size()){
						BookVO book = bookList.get(i);
						newBookList.add(book);
					}
				}
			}
			
			
			this.setBookList(newBookList);
			this.setCategoryName("Top Rated");
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newBooksCount", newBookList.size());
			modelMap.addAttribute("booksCount", bookList.size());
			modelMap.addAttribute("bookList", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			if(httpSession.getAttribute("checkMe")!=null){
				modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
			}
			else
			{
				modelMap.addAttribute("checkMe",false);
			}
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());
			modelMap.addAttribute("selectedcat", "Select a Category");
			modelMap.addAttribute("selectedcatid", 0);
		} catch (SapeStoreSystemException e) {
			LOGGER.error("searchPage method: ERROR: " + e);
			modelMap.addAttribute("errorMessage",
					"Error in opening the searchPage page.");
			return "redirect:/errorPage";
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("searchPage method: ModelMap: " + modelMap);
			LOGGER.debug("searchPage method: END");
		}

		return "search";
	}
	

	private List<BookVO> getBooksList(Object checkMeFromSession) throws SapeStoreException{
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
	}

	/**
	 * Processes the requests to pull book list by category
	 * @param categoryId
	 * @param categoryName
	 * @param checkMe
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bookListByCat", method = RequestMethod.GET)
	public String getBooksListByCat(@ModelAttribute("welcome") HomeVO welcome,@RequestParam("categoryId") int categoryId,
			@RequestParam(value="checkMe",required=false) boolean checkMe,
			@RequestParam(value="page",required=false) String pageString,
			@RequestParam("categoryName") String categoryName,
			ModelMap modelMap)
			throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: START");
		}
		
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			this.setCheckMe(checkMe);
			list = bookService.getBookList(categoryId,this.isCheckMe());
			
			List<BookVO> newBookList = new ArrayList<BookVO>();
			int page = 1;
			if(pageString != null){
				page = Integer.parseInt(pageString);
				int limiter = (page-1)*15 ;
				/*BookVO book = list.get(0);
				newBookList.add(book);*/
				for(int i = limiter; i< (limiter + 15); i++){
					if(i < list.size()){
						BookVO book = list.get(i);
						newBookList.add(book);
					}
				}
			}else{
				for(int i = 0; i< 15; i++){
					if(i < list.size()){
						BookVO book = list.get(i);
						newBookList.add(book);
					}
				}
			}
			
            /*if (this.isCheckMe()) {
            	list.addAll(bookService.getBookFromWebService(categoryId));
            }*/
			this.setBookList(newBookList);
			this.setCatList(getCategoryList());
			
			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newBooksCount", newBookList.size());
			modelMap.addAttribute("booksCount", list.size());
			modelMap.addAttribute("checkMeJs", checkMe);
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("getBooksListByCat method: END" + ex);
			modelMap.addAttribute("errorMessage",
					"Error in getting book list by category");
			
			return "redirect:/errorPage";
		}
		
		modelMap.addAttribute("bookList", this.getBookList());
		modelMap.addAttribute("catList", this.getCatList());
		modelMap.addAttribute("categoryName", categoryName);
		modelMap.addAttribute("checkMe", this.checkMe);
		modelMap.addAttribute("userlogin", new UserVO());
		modelMap.addAttribute("categoryId", categoryId);
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: END");
		}
		return "index";
	}
	/**
	 * This method returns the category of books.
	 * 
	 * @return
	 */
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
	
	@RequestMapping(value = "/searchBook", method = RequestMethod.GET)
	public String searchBook(@ModelAttribute("welcome") HomeVO welcome,@RequestParam("booktitle") String bookTitle,
			@RequestParam("bookauthor") String bookAuthor, @RequestParam("publisher") String publisher,
			@RequestParam("category") String category ,@RequestParam(value="partnerstorecheck", required=false) boolean partnerStoreCheck,
			@RequestParam(value="mostcomments", required=false) boolean mostNumberOfComments,
			@RequestParam(value="checkMe",required=false) boolean checkMe, ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession)
			throws SapeStoreException {
        
        String parts[] = category.split("\\+");
		String categoryName = parts[0];
		String categoryId = parts[1];
        
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("searchBook method: START");}
			
		List<BookVO> bookList = new ArrayList<BookVO>(); 

		try {
			this.setCatList(getCategoryList());
			Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(checkMeFromSession);
			
            /*if (checkMeFromSession!=null && (boolean) checkMeFromSession) {
            	bookList.addAll(bookService.getBookFromWebService(0));
            }*/
			this.setBookList(bookList);
			this.setCategoryName("Top Rated");
			modelMap.addAttribute("bookList", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			if(httpSession.getAttribute("checkMe")!=null){
				modelMap.addAttribute("checkMe", httpSession.getAttribute("checkMe"));
			}
			else
			{
				modelMap.addAttribute("checkMe",false);
			}

			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());
		} catch (SapeStoreSystemException e) {
			LOGGER.error("searchBook method: ERROR: " + e);
			modelMap.addAttribute("errorMessage",
					"Error in opening the searchBook page.");
			return "redirect:/errorPage";
		}

			Search searchbook = new Search();
			
			searchbook.setBookTitle(bookTitle);
			searchbook.setBookAuthor(bookAuthor);
			searchbook.setCategoryId(Integer.parseInt(categoryId));
			searchbook.setPublisher(publisher);
			searchbook.setPartnerStore(partnerStoreCheck);
		
			List<BookVO> searchBookList = new ArrayList<BookVO>();
			if(mostNumberOfComments){
				searchBookList = bookService.getBookListByComment(searchbook, partnerStoreCheck, mostNumberOfComments);
			}else{
			searchBookList = bookService.getBookListBySearch(searchbook, partnerStoreCheck, mostNumberOfComments);
			}
			modelMap.addAttribute("bookList", searchBookList);
			modelMap.addAttribute("nobooks","No Results Found");
			modelMap.addAttribute("searchResult", searchbook); 
			if(partnerStoreCheck){
				modelMap.addAttribute("partnerCheck", "checked");
			}else{
				modelMap.addAttribute("partnerCheck", "");
			}
			if(mostNumberOfComments){
				modelMap.addAttribute("mostCommentsCheck", "checked");
			}else{
				modelMap.addAttribute("mostCommentsCheck", "");
			}
            if(searchbook.getCategoryId()==0){
				modelMap.addAttribute("selectedcat", "Select a Category");
				modelMap.addAttribute("selectedcatid", "0");
			}else{
				modelMap.addAttribute("selectedcat", categoryName);
				modelMap.addAttribute("selectedcatid", categoryId);
			}

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("searchBook method: ModelMap: " + modelMap);
				LOGGER.debug("searchBook method: END");
			}
			
			return "search";
			
		}
	@RequestMapping("/bookDetails")
	public String getBookDetails(@RequestParam(value="isbn") String isbn,ModelMap modelMap) throws SapeStoreException {
		BookDetailsNComments bookDetailsNComments=getAllDetails.getBookDetailsComments(isbn);
		BookVO bookDetails=bookDetailsNComments.getBookDetails();
		List<BookRatingCommentsVO> bookComments=bookDetailsNComments.getBookComments();
		int ratings=bookDetailsNComments.getRatings();
		/*double averageRating=bookDetailsNComments.getAverageRating();*/
		modelMap.addAttribute("bookDetails", bookDetails);
		modelMap.addAttribute("bookComments",bookComments);
		modelMap.addAttribute("ratings", ratings);
		/*modelMap.addAttribute("averageRating", averageRating);*/
		List<BookCategory> categoryList = getCategoryList();
		modelMap.addAttribute("catList",categoryList);
		return "bookDetails"; 
	}
}
