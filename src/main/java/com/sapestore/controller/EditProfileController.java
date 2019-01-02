
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapestore.common.SapeStoreLogger;

import com.sapestore.dao.ProductDao;
import com.sapestore.dao.SendEmail;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.facade.BookDetailsCommentsFacade;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.BookService;
import com.sapestore.service.CustomerEditProfileService;
import com.sapestore.service.CustomerRegService;
import com.sapestore.service.EmailConfirmationService;
import com.sapestore.service.UpdateEmailConfirmation;
import com.sapestore.service.impl.EmailConfirmationServiceImpl;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.HomeVO;
import com.sapestore.vo.RegisterVO;
import com.sapestore.vo.UserVO;

@Controller
@SessionAttributes(value = "userId")
public class EditProfileController {
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ProductController.class.getName());

	private List<BookVO> bookList;
	private List<BookCategory> catList;
	private String categoryName;
	private boolean checkMe;

	@Autowired
	private BookService bookService;

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UpdateEmailConfirmation emailAddressService1;

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
	CustomerEditProfileService customerEditProfile;

	@Autowired
	User user;

	@Autowired
	Address address;
	@RequestMapping(value = "/beforeEdit1"/* , method = RequestMethod.GET */)
	@ResponseBody
	public List<City> beforeRegister1(@RequestParam("stateId") int state, ModelMap modelMap, HttpServletRequest httpServletRequest, HttpSession httpSession)
			throws SapeStoreException {
		
		LOGGER.debug(" RegisterController.beforeRegister method: START ");
		
		System.out.println(state);
		System.out.println("getting the city list");
		
		ArrayList<City> city=customerEditProfile.getAllCities(state );
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
		modelMap.addAttribute("cityList", customerEditProfile.getAllCities(state ));
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
	@RequestMapping(value = "/beforeEdit")
	public String beforeRegister( ModelMap modelMap, @ModelAttribute(value = "userId") String userId)
			throws SapeStoreException{
		
		LOGGER.debug(" RegisterController.beforeRegister method: START ");
		String button = "none";
		String stateName = null;
		String cityName = null;
		Integer stateId = null;
		RegisterVO registerVO = customerEditProfile.getCustomerInfo(userId);
		/* modelMap.addAttribute("user", new RegisterVO()); */
		List<State> stateList = customerEditProfile.getAllStates();
		
		/*List<City> cityList = customerEditProfile.getAllCities();*/
		for (State state : stateList) {
			if (state.getStateId() == registerVO.getStateId()) {
				stateName = state.getStateName();
				stateId =state.getStateId();
				
			}
		}
		List<City> cityList = customerEditProfile.getAllCities(stateId);
		for (City city : cityList) {
			if (city.getCityId() == registerVO.getCityId()) {
				cityName = city.getCityName();
			}
		}
		modelMap.addAttribute("stateList",stateList );
		modelMap.addAttribute("cityList",cityList);
		modelMap.addAttribute("cityName", cityName);
		modelMap.addAttribute("stateName", stateName);
		modelMap.addAttribute("user", registerVO); 
		
		LOGGER.debug(" RegisterController.beforeRegister method: END ");
		return "editProfile";
	}

	@RequestMapping(value = "/personalInfo")
	public String getCustomerInfo(ModelMap modelMap, HttpSession httpSession,
			@ModelAttribute(value = "userId") String userId) throws SapeStoreException {
		LOGGER.debug("Personal info");
		System.out.println("Personal info");
		System.out.println("userId  " + userId);
		String stateName = null;
		String cityName = null;
		RegisterVO registerVO = new RegisterVO();
		registerVO = customerEditProfile.getCustomerInfo(userId);
		// System.out.println(registerVO);
		Integer stateId = registerVO.getStateId();
		Integer cityId = registerVO.getCityId();
		List<State> stateList = customerEditProfile.getAllStates();
		List<City> cityList = customerEditProfile.getAllCities(stateId);
		for (State state : stateList) {
			if (state.getStateId() == stateId) {
				stateName = state.getStateName();
			}
		}
		for (City city : cityList) {
			if (city.getCityId() == cityId) {
				cityName = city.getCityName();
			}
		}
		// System.out.println("NEW REGISTERVO:"+registerVO);
		
		modelMap.addAttribute("cityName", cityName);
		modelMap.addAttribute("stateName", stateName);
		modelMap.addAttribute("registerVO", registerVO);
		return "personalInfo";
	}

	@RequestMapping(value = "/editprofile")
	public String register(@ModelAttribute("user") RegisterVO register, ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession) throws SapeStoreException {
		LOGGER.debug(" RegisterController.Register method: START ");
		// boolean checkUser =
		// customerEditProfile.checkUserName(register.getLoginName());
		System.out.println("In controller");
		System.out.println("Register:" + register);

		try {

			Date date = new Date();
			System.out.println("inside try");
			modelMap.addAttribute("user", register);
			address.setUserId(register.getLoginName());
			address.setAddressLine1(register.getAddress1());
			address.setAddressLine2(register.getAddress2());
			address.setCityId(register.getCityId());
			address.setStateId(register.getStateId());
			address.setPostalCode(register.getZip());
			address.setCountryId(1);
			address.setAddressId(customerEditProfile.getAddressId(register.getLoginName()));
			address.setCreatedDate(date);
			address.setUpdatedDate(date);
			address.setIsActive("Y");
			address.setMobileNumber(register.getMobileNumber());
			Integer addressId = customerEditProfile.addAddress(address,register.getLoginName());

			System.out.println("Address:" + address);
			// System.out.println("addressId"+addressId);
			if (register.getPhoneNumber() == null) {
				user.setPhone(0l);
			} else {
				user.setPhone((register.getPhoneNumber()));
			}
			user.setName(register.getFullName());
			user.setPassword(register.getPassword());
			user.setAddressId(addressId);
			user.setUserId(register.getLoginName());
			user.setEmailAddress(register.getEmail());
			user.setIsAdmin("N");
			user.setIsActive("Y");
			user.setCreatedDate(date);
			user.setUpdatedDate(date);
			user.setUserStatus("Y");
			user.setGenderId(1);

			// user.setPhone(register.getPhoneNumber());
			if (register.getMobileNumber() == null) {
				user.setMobileNumber(0l);
			} else {
				user.setMobileNumber(register.getMobileNumber());
			}
			user.setName(register.getFullName());
			user.setPassword(register.getPassword());

			/*
			 * SendEmail sendingmail = new SendEmail();
			 * sendingmail.sendmail(register.getEmail());
			 */
			System.out.println("user..." + user);
			customerEditProfile.updateCustomer(user);
			System.out.println("new user:" + user);
			/*
			 * final String disabled = "disabled";
			 * 
			 * final String hidden = "hidden"; final String button = "visible";
			 * modelMap.addAttribute("visibility", button);
			 * modelMap.addAttribute("hidden", "submit");
			 * modelMap.addAttribute("fieldset", disabled);
			 * modelMap.addAttribute("hidden", hidden);
			 * modelMap.addAttribute("registerVO", register);
			 */
			String message = "Successfully registered";
			modelMap.addAttribute("message", message);
		}
 catch (SapeStoreSystemException ex) {
			LOGGER.error("register Controller: ERROR: " + ex);

		}
		LOGGER.debug(" RegisterController.Register method: End ");
		
		String userId = (String) httpSession.getAttribute("userId");
		modelMap.addAttribute("register", register);
		emailAddressService1.sendAccountUpdateEmail(register,userId);
		/*emailAddressService.sendAccountUpdateEmail(register, userId);*/
		return "redirect:/personalInfo1";
	}

	@RequestMapping(value = "/personalInfo1")
	public String getCustomerInfo1(ModelMap modelMap, HttpSession httpSession,
			@ModelAttribute(value = "userId") String userId) throws SapeStoreException {
		LOGGER.debug("Personal info");
		System.out.println("Personal info");
		System.out.println("userId  " + userId);
		String stateName = null;
		String cityName = null;
		RegisterVO registerVO = new RegisterVO();
		registerVO = customerEditProfile.getCustomerInfo(userId);
		System.out.println(registerVO);
		Integer stateId = registerVO.getStateId();
		Integer cityId = registerVO.getCityId();
		List<State> stateList = customerEditProfile.getAllStates();
		List<City> cityList = customerEditProfile.getAllCities(stateId);
		for (State state : stateList) {
			if (state.getStateId() == stateId) {
				stateName = state.getStateName();
			}
		}
		for (City city : cityList) {
			if (city.getCityId() == cityId) {
				cityName = city.getCityName();
			}
		}
		System.out.println("NEW REGISTERVO:" + registerVO);
		modelMap.addAttribute("cityName", cityName);
		modelMap.addAttribute("stateName", stateName);
		modelMap.addAttribute("registerVO", registerVO);
		return "personalInfo";
	}

	/*
	 * On clicking Edit button in customerRegistration.jsp afterEdit method is
	 * hit
	 */

	
	  @RequestMapping(value = "/cancel") 
	  public String cancel(ModelMap modelMap, HttpSession httpSession,
				@ModelAttribute(value = "userId") String userId) throws SapeStoreException {
			LOGGER.debug("Personal info");
			System.out.println("Personal info");
			System.out.println("userId  " + userId);
			String stateName = null;
			String cityName = null;
			RegisterVO registerVO = new RegisterVO();
			registerVO = customerEditProfile.getCustomerInfo(userId);
			// System.out.println(registerVO);
			Integer stateId = registerVO.getStateId();
			Integer cityId = registerVO.getCityId();
			List<State> stateList = customerEditProfile.getAllStates();
			List<City> cityList = customerEditProfile.getAllCities(stateId);
			for (State state : stateList) {
				if (state.getStateId() == stateId) {
					stateName = state.getStateName();
				}
			}
			for (City city : cityList) {
				if (city.getCityId() == cityId) {
					cityName = city.getCityName();
				}
			}
			// System.out.println("NEW REGISTERVO:"+registerVO);
			modelMap.addAttribute("cityName", cityName);
			modelMap.addAttribute("stateName", stateName);
			modelMap.addAttribute("registerVO", registerVO);
			return "personalInfo";
		}
	 
}
