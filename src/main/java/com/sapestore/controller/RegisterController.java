
package com.sapestore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.CustomerRegDao;
import com.sapestore.dao.ProductDao;
import com.sapestore.dao.SendEmail;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.BookService;
import com.sapestore.service.CustomerRegService;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.HomeVO;
import com.sapestore.vo.RegisterVO;
import com.sapestore.vo.UserVO;

@Controller
public class RegisterController {
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ProductController.class.getName());

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

	@Autowired
	CustomerRegService customerReg;

	@Autowired
	User user;

	@Autowired
	Address address;

	
	
	 
		@RequestMapping(value = "/beforeRegis1"/* , method = RequestMethod.GET */)
		@ResponseBody
		public List<City> beforeRegister1(@RequestParam("stateId") int state, ModelMap modelMap, HttpServletRequest httpServletRequest, HttpSession httpSession)
				throws SapeStoreException {
			
			LOGGER.debug(" RegisterController.beforeRegister method: START ");
			
			System.out.println(state);
			System.out.println("getting the city list");
			
			ArrayList<City> city=customerReg.getAllCities(state );
			System.out.println(city+""+state);
			
		List<City> json = null;
				ObjectMapper objectMapper = new ObjectMapper();
		        
				try {
					json = objectMapper.readValue(
						     objectMapper.writeValueAsString(city),  new TypeReference<List<City>>(){});
					System.out.println(json);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			modelMap.addAttribute("cityList", customerReg.getAllCities(state ));
			System.out.println("after the map");
			//System.out.println(customerReg.getAllCities(state ));
			//modelMap.addAttribute("countryList", customerReg.getAllCountries());
			LOGGER.debug(" RegisterController.beforeRegister method: END ");

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("welcome method: START");
			}


			System.out.println("at last");
			System.out.println("in the controller"+json);

			return json;
		}
	
	/*
	 * From register button control comes to beforeRegister method . From here
	 * it goes to customerRegistration.jsp
	 */
	@RequestMapping(value = "/beforeRegister"/* , method = RequestMethod.GET */)
	public String beforeRegister(@RequestParam("stateId") int state, ModelMap modelMap, HttpServletRequest httpServletRequest, HttpSession httpSession)
			throws SapeStoreException {
		
		LOGGER.debug(" RegisterController.beforeRegister method: START ");
		String button = "none";
		System.out.println(state);
		
		
		modelMap.addAttribute("visibility", button);
		modelMap.addAttribute("user", new RegisterVO());
		modelMap.addAttribute("stateList", customerReg.getAllStates());
		modelMap.addAttribute("genderList", customerReg.getAllGenders());
		modelMap.addAttribute("cityList", customerReg.getAllCities(state ));
		System.out.println(customerReg.getAllCities(state ));
		modelMap.addAttribute("countryList", customerReg.getAllCountries());
		LOGGER.debug(" RegisterController.beforeRegister method: END ");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: START");
		}

		List<BookVO> bookList = new ArrayList<BookVO>();

		try {
			this.setCatList(getCategoryList());
			Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(checkMeFromSession);

			this.setBookList(bookList);
			this.setCategoryName("Top Rated");
			modelMap.addAttribute("bookList", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());
		} catch (SapeStoreSystemException e) {
			LOGGER.error("welcome method: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
			return "redirect:/errorPage";
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: ModelMap: " + modelMap);
			LOGGER.debug("welcome method: END");
		}
		System.out.println("at last");

		return "CustomerRegistration";
	}

	@RequestMapping(value = "/bookListByCats", method = RequestMethod.GET)
	public String getBooksListByCat(
			/* @ModelAttribute("welcome") HomeVO welcome, */@RequestParam("categoryId") int categoryId,

			@RequestParam("categoryName") String categoryName, ModelMap modelMap) throws Exception {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksListByCat method: START");
		}

		List<BookVO> list = new ArrayList<BookVO>();

		modelMap.addAttribute("bookList", list);
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

	private List<BookVO> getBooksList(Object checkMeFromSession) throws SapeStoreException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getBooksList method: START");
		}
		List<BookVO> bookList = new ArrayList<BookVO>();
		try {
			try {
				bookList = bookService.getBookList(0, checkMeFromSession);
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

	/*
	 * On clicking submit on customer registration.jsp register method is hit.
	 * From here it redirects back to CustomerRegistration.jsp
	 */
	@RequestMapping(value = "/register")
	public String register(@ModelAttribute("user") RegisterVO register, ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession) throws SapeStoreException {
		LOGGER.debug(" RegisterController.Register method: START ");
		boolean checkUser = customerReg.checkUserName(register.getLoginName());
		System.out.println("in controller");
		
		System.out.println("in the register page");

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: START");
		}

		List<BookVO> bookList = new ArrayList<BookVO>();

		try {
			this.setCatList(getCategoryList());
			Object checkMeFromSession = httpSession.getAttribute("checkMe");
			bookList = getBooksList(checkMeFromSession);

			this.setBookList(bookList);
			this.setCategoryName("Top Rated");
			modelMap.addAttribute("bookList", this.getBookList());
			modelMap.addAttribute("catList", this.getCatList());
			modelMap.addAttribute("categoryName", getCategoryName());
			modelMap.addAttribute("userlogin", new UserVO());
			modelMap.addAttribute("categoryId", 0);
			modelMap.addAttribute("welcome", new HomeVO());
		} catch (SapeStoreSystemException e) {
			LOGGER.error("welcome method: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
			return "redirect:/errorPage";
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("welcome method: ModelMap: " + modelMap);
			LOGGER.debug("welcome method: END");
		}

		try {
			if (checkUser) {
				Date date = new Date();

				modelMap.addAttribute("user", register);
				address.setUserId(register.getLoginName());
				address.setAddressLine1(register.getAddress1());
				address.setAddressLine2(register.getAddress2());
				address.setCityId(register.getCityId());
				address.setStateId(register.getStateId());
				address.setPostalCode(register.getZip());
				address.setCreatedDate(date);
				address.setUpdatedDate(date);
				address.setIsActive("Y");
				address.setMobileNumber(register.getMobileNumber());
				customerReg.addAddress(address);

				if (register.getPhoneNumber() == null) {
					user.setPhone((long) 0);
				} else {
					user.setPhone( (register.getPhoneNumber()));
				}

				user.setAddressId(address.getAddressId());
				user.setUserId(register.getLoginName());
				user.setEmailAddress(register.getEmail());
				user.setIsAdmin("N");
				user.setIsActive("Y");
				user.setGenderId(register.getGenderId());
				// user.setPhone(register.getPhoneNumber());
				if (register.getMobileNumber() == null) {
					user.setMobileNumber((long) 0);
				} else {
					user.setMobileNumber((Long) register.getMobileNumber());
				}
				user.setName(register.getFullName());
				user.setPassword(register.getPassword());
				
				String username=register.getLoginName();
				String password=register.getPassword();

				SendEmail sendingmail = new SendEmail();
				sendingmail.sendmail(register.getEmail(),username,password);

				customerReg.addCustomer(user);

				final String disabled = "disabled";

				final String hidden = "hidden";
				final String button = "visible";
				modelMap.addAttribute("visibility", button);
				modelMap.addAttribute("hidden", "submit");
				modelMap.addAttribute("fieldset", disabled);
				modelMap.addAttribute("hidden", hidden);
				modelMap.addAttribute("user", register);
				String message = "Successfully registered";
					String userId = register.getLoginName();
							HttpSession session=httpServletRequest.getSession();  
							session.setAttribute("userId", userId);
							session.setAttribute("username", user.getName()); 

				
				modelMap.addAttribute("message", message);

			} else {
				int sId=address.getStateId();
				System.out.println("login name exists");
				modelMap.addAttribute("user", register);
				modelMap.addAttribute("stateList", customerReg.getAllStates());
				modelMap.addAttribute("genderList", customerReg.getAllGenders());
				modelMap.addAttribute("cityList", customerReg.getAllCities(sId));
				modelMap.addAttribute("countryList", customerReg.getAllCountries());
				modelMap.addAttribute("errormessage", "loginname already exists");
					
			}
		} catch (SapeStoreSystemException ex) {
			LOGGER.error("register Controller: ERROR: " + ex);

		}
		LOGGER.debug(" RegisterController.Register method: End ");
		return "CustomerRegistration";
	}

	/*
	 * On clicking Edit button in customerRegistration.jsp afterEdit method is
	 * hit
	 */

	@RequestMapping(value = "/afterEdit")
	public String afterEdit(@ModelAttribute("user") RegisterVO register, ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug(" RegisterController.afterEdit method: START ");

		LOGGER.debug(" RegisterController.afterEdit method: END ");
		return "editProfile";
	}
	
	@RequestMapping(value = "/checkU", method = RequestMethod.GET)
	@ResponseBody
	public String  login1(@RequestParam("loginName") String loginName, final  ModelMap modelMap,HttpServletRequest httpServletRequest,HttpSession httpSession) throws SapeStoreException
	{
		LOGGER.debug("login method: START");
		System.out.println(loginName);
		System.out.println("in the checking user");
		String flag1="false";
		boolean flag = customerReg.checkUserName(loginName);
		if(flag)
		{
		flag1="true";
		}
		
		
  

       
		LOGGER.debug("login method: END");
		 
		return flag1;
	}
	

}
