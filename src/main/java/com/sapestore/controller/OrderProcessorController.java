package com.sapestore.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sapestore.vo.BookVO;
import com.sapestore.vo.CurrentShippingAddress;
import com.sapestore.vo.MemberAddress;
import com.sapestore.vo.OrderInfoVO;
import com.sapestore.vo.OrderItemInfoVO;
import com.sapestore.vo.ShippingAddressVO;
import com.sapestore.vo.ShoppingCartVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.City;
import com.sapestore.service.CustomerEditProfileService;
import com.sapestore.service.EmailConfirmationService;
import com.sapestore.service.OrderInfoService;
import com.sapestore.service.OrderItemInfoService;
import com.sapestore.service.ShippingAddressService;

@Controller
@SessionAttributes(value = { "userId", "ShoppingCart" })
public class OrderProcessorController {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderProcessorController.class.getName());
	public int sid;
	public int cid;
	
	public Long oid;
	@Autowired
	ShippingAddressService shippingService;
	@Autowired
	OrderInfoService orderInfoService;
	@Autowired
	OrderItemInfoService orderInfoItemInfoService;
	@Autowired
	CustomerEditProfileService customerEditProfile;
	@Autowired
	private EmailConfirmationService emailAddressService;
	@RequestMapping(value = "/beforeSavingShipping"/* , method = RequestMethod.GET */)
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
	@RequestMapping(value = "/myorder/displayShippingAddressForm", method = RequestMethod.GET)
	public String displayShippingAddress(ModelMap modelMap, HttpSession session) {
	
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("DisplayShippingAddress: Enter");
		}
		
		// retireve the userId from session attribute
		String userId = (String) session.getAttribute("userId");
		
		String username = shippingService.getUserName(userId);
		

		MemberAddress address = new MemberAddress();
		address = shippingService.retriveShippingAddress(userId);
		address.setName(username);

		modelMap.addAttribute("stateList", shippingService.getAllStateId());
		modelMap.addAttribute("cityList", shippingService.getAllCityId());
		modelMap.addAttribute("address", address);
		address.setCityName(shippingService.getCityName(address.getCityId()));
		address.setStateName(shippingService.getStateName(address.getStateId()));
		
		cid=address.getCityId();
		sid=address.getStateId();
		System.out.println("in contoller"+shippingService.getCityName(address.getCityId()));
		System.out.println("in contoller"+shippingService.getStateName(address.getStateId()));
		System.out.println("in controoler mobile"+address.getMobileNumber());
		
		
		modelMap.addAttribute("address", address);
		
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("DisplayShippingAddress: End");
		}
		return "ShippingAddress";
		

	}

	@RequestMapping(value = "/myorder/saveShippingAddress", method = RequestMethod.POST)
	public String saveShippingAddressDetails(@ModelAttribute("address") MemberAddress address, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, HttpSession session) {
	
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("saveShippingAddressDetails:");
		}
		String userId = (String) session.getAttribute("userId");
	
		
		ShoppingCartVO cart = (ShoppingCartVO) session.getAttribute("ShoppingCart");
		System.out.println("cart object in controoler\n"+cart);
		
		OrderInfoVO orderInfoVo=new OrderInfoVO();
		orderInfoVo.setUserId(userId);
		//orderInfoVo.setTotalPayment();
		System.out.println("in controller adding orderid");
		oid=orderInfoService.addOrder(orderInfoVo);
		
		System.out.println("in controller after adding orderid"+oid);
		
		ShippingAddressVO shippingAddress = new ShippingAddressVO();
		
		
	
		
		
		shippingAddress.setName(shippingService.getUserName(userId));
		
		shippingAddress.setAddressLine1(address.getAddressLine1());
		shippingAddress.setAddressLine2(address.getAddressLine2());
		if(address.getCityId()==0){
			address.setCityId(cid);
		}
		if(address.getStateId()==0){
			address.setStateId(sid);
		}
		shippingAddress.setCityId(address.getCityId());
		shippingAddress.setStateId(address.getStateId());
		shippingAddress.setZipCode(address.getPostalCode());
		shippingAddress.setOrderId(oid);
	/*	
		List<Integer> orderId = shippingService.getOrderId(userId);

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < orderId.size(); i++) {
			if (orderId.get(i) > max) {
				max = orderId.get(i);
			}
		}*/
		
		

		CurrentShippingAddress currentShippingAddress = new CurrentShippingAddress();
		currentShippingAddress.setCustomerName(shippingAddress.getName());
		currentShippingAddress.setAddressLine1(address.getAddressLine1());
		currentShippingAddress.setAddressLine2(address.getAddressLine2());
		currentShippingAddress.setZipcode(shippingAddress.getZipCode());
		currentShippingAddress.setMobilenumber(address.getMobileNumber());
		currentShippingAddress.setOrderId(oid);
		
		String statename = shippingService.getStateName(shippingAddress.getStateId());
		currentShippingAddress.setState(statename);
		
		String cityname = shippingService.getCityName(shippingAddress.getCityId());
		currentShippingAddress.setCity(cityname);
		

		System.out.println("in controller abefore adding address");
		
		shippingService.addShippingAddress(shippingAddress);

		System.out.println("in controller aafter adding address");
		
		redirectAttributes.addFlashAttribute("shippingAddress", currentShippingAddress);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("saveShippingAddressDetails:END");
		}
		return "redirect:/myorder/orderconfirmation";
	}

	
	
	@RequestMapping(value = "/myorder/orderconfirmation", method = RequestMethod.GET)
	public String sendOrderConfirmation(
			@ModelAttribute("shippingAddress") CurrentShippingAddress currentShippingAddress, HttpSession session,
			ModelMap modelMap) {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("sendOrderConfirmation:");
		}
		
		OrderItemInfoVO orderItemInfoVO=new OrderItemInfoVO();
		orderItemInfoVO.setOrderId(new Long(oid));
		BookVO bookVo=new BookVO();
		
		ShoppingCartVO order = (ShoppingCartVO) session.getAttribute("ShoppingCart");
		System.out.println("in orderconfirmation"+order);
		List<BookVO> books = new ArrayList<BookVO>();
		books = order.getBooksInCart();
		Iterator<BookVO> iterator =books.iterator();
		while (iterator.hasNext()) {
			bookVo=iterator.next();
			System.out.println("in loop"+bookVo);
			orderItemInfoVO.setIsbn(bookVo.getIsbn());
			orderItemInfoVO.setBookPrice(bookVo.getBookPrice());
			orderItemInfoVO.setOrderQuantity(bookVo.getCartQuantity());
			orderItemInfoVO.setReturnDate(order.getReturnDate());
			System.out.println("in controller"+order.getReturnDate());
			orderItemInfoVO.setRented(bookVo.getIsRented());
			BigDecimal orderItemId=orderInfoItemInfoService.addOrderItemInfo(orderItemInfoVO);
			//orderItemInfoVO.setIsActive(bookVo.setActive("y"));
			//orderItemInfoVO.setOrderStatus("false");
			//orderItemInfoVO.setPaymentStatus("false");
		}
		System.out.println("books"+books.get(0));
		//orderItemInfoVO.setIsbn("123");
		
		
		
		
		// this is used to retireve the shopping cart items from previous module
		


		modelMap.addAttribute("address", currentShippingAddress);
		modelMap.addAttribute("orders", order);
		modelMap.addAttribute("books", books);
		modelMap.addAttribute("orderId",oid);
		
		
		String userId = (String) session.getAttribute("userId");
		
		//this is for sending email which is not working due to network issue
		emailAddressService.sendOrderConfirmationMail(currentShippingAddress,order,books,userId);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("sendOrderConfirmation:END");
		}
	//	 session.removeAttribute("ShoppingCart");
		//	ShoppingCartVO order1 = (ShoppingCartVO) session.getAttribute("ShoppingCart");
		 //System.out.println(order1);
		modelMap.addAttribute("ShoppingCart", new ShoppingCartVO());
		/*session.setAttribute("ShoppingCart",);*/
		ShoppingCartVO order1 = (ShoppingCartVO) session.getAttribute("ShoppingCart");
		System.out.println(order1);
		
		return "OrderConfirmation";
		//return "email-template";

	}
	/*@RequestMapping(value = "/myorder/sendOrderSummaryMail", method = RequestMethod.GET)
	public String sendOrderConfirmation(
			@ModelAttribute("shippingAddress") CurrentShippingAddress currentShippingAddress, HttpSession session,
			ModelMap modelMap) {
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("sendOrderConfirmation:");
		}
		

		// this is used to retireve the shopping cart items from previous module
		ShoppingCartVO order = (ShoppingCartVO) session.getAttribute("ShoppingCart");
	

		List<BookVO> books = new ArrayList<BookVO>();
		books = order.getBooksInCart();


		modelMap.addAttribute("address", currentShippingAddress);
		modelMap.addAttribute("orders", order);
		modelMap.addAttribute("books", books);
		modelMap.addAttribute("orderId",currentShippingAddress.getOrderId());
		
		
		String userId = (String) session.getAttribute("userId");
		
		//this is for sending email which is not working due to network issue
		emailAddressService.sendOrderConfirmationMail(currentShippingAddress,order,userId);
		
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("sendOrderConfirmation:END");
		}
		return "email-template";

	}*/

}
