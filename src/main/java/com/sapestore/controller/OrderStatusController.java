/*package com.sapestore.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderStatusDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.OrderStatusService;

import com.sapestore.vo.OrderVO;

@Controller
public class OrderStatusController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderStatusController.class.getName());

	@Autowired 
	OrderStatusService orderservice;
@Autowired
OrderStatusDao orderStatusDao;
	OrderVO orderVO = new OrderVO();

	@RequestMapping(value = "/orderStatus")
	public String orderStatus(ModelMap modelMap) {
		return "orderStatus";
	}

	@RequestMapping(value = "/getorderstatus", method = RequestMethod.POST)
	public String getOrderStatus(@RequestParam("orderId") int orderNumber, ModelMap modelMap, HttpSession session)
			throws SapeStoreException {
		

		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("order status: START");
			}

			orderVO.setOrderNumber(orderNumber);
			
			modelMap.addAttribute("orderNumber", orderNumber);
			Integer orderId=orderNumber;
			boolean check=orderStatusDao.checkOrderId(orderId);
			
			if(check==true){
				
			String status = orderservice.returnOrderStatus(orderNumber);
			modelMap.addAttribute("status", status);
			}else{
				modelMap.addAttribute("status", "No such order Id found");
			}
			modelMap.addAttribute("orderNumber", orderNumber);

		} catch (SapeStoreSystemException e) {
			LOGGER.error("Error in adding comments: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
			return "error";
		}
		return "orderStatus";
	}
}
*/



package com.sapestore.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderStatusDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderStatusService;
import com.sapestore.vo.OrderStatusVO;
import com.sapestore.vo.OrderVO;

@Controller
public class OrderStatusController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderStatusController.class.getName());

	@Autowired 
	OrderStatusService orderservice;
@Autowired
OrderStatusDao orderStatusDao;
	OrderVO orderVO = new OrderVO();

	@RequestMapping(value = "/orderStatus")
	public String orderStatus(ModelMap modelMap) {
		return "orderStatus";
	}

	@RequestMapping(value = "/getorderstatus", method = RequestMethod.POST)
	public String getOrderStatus(@RequestParam("orderId") int orderNumber, ModelMap modelMap, HttpSession session)
			throws SapeStoreException {
		System.out.println(orderNumber+ " ORDER NUMBER IS COMING TILL CONTROLLER!!!!!!!!!!!!!! ");
		
		/*System.out.println(orderservice.getMyRentedOrder(orderNumber));
*/
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("order status: START");
			}
String userId= (String) session.getAttribute("userId");
			orderVO.setOrderNumber(orderNumber);
			System.out.println(orderNumber);
			modelMap.addAttribute("orderNumber", orderNumber);
			Integer orderId=orderNumber;
			boolean check=orderStatusDao.checkOrderId(orderId,userId);
			System.out.println(check);
			if(check==true){
				
			String status = orderservice.returnOrderStatus(orderNumber, userId);
			String htmlStatus = "<span> Your Order <font color='red'><u>"+orderNumber+" "+" "+"</u></font>"+status+"</span>";
			/*String htmlStatus = "Your Order"+" "+"";*/
			
			List<OrderStatusVO> myorderslist= orderservice.getMyRentedOrder((long) orderNumber);
			System.out.println("in controler sahi"+ myorderslist);
			modelMap.addAttribute("myorders", myorderslist);
			modelMap.addAttribute("htmlStatus", htmlStatus);
			}
				else{
					modelMap.addAttribute("htmlStatus", "Incorrect order number");
				}
				modelMap.addAttribute("orderNumber", orderNumber);
				System.out.println("order"+orderNumber);
				
			} catch (SapeStoreSystemException e) {
				LOGGER.error("Error in adding comments: ERROR: " + e);
				modelMap.addAttribute("errorMessage", "Error in opening the welcome page.");
				return "error";
			}
			return "orderStatus";
		}
	}
