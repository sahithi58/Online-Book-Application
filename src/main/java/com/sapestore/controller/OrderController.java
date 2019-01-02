package com.sapestore.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapestore.common.ApplicationConstants;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.OrderDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.service.EmailDispatchService;
import com.sapestore.service.OrderService;
import com.sapestore.vo.DispatchSlip;
import com.sapestore.vo.PurchasedUpdate;
import com.sapestore.vo.PurchasedUpdateForm;
import com.sapestore.vo.RentedUpdate;
import com.sapestore.vo.RentedUpdateForm;

@Controller
@SessionAttributes("dispatchList")
public class OrderController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(OrderController.class.getName());

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderService orderService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.setAutoGrowCollectionLimit(1024);
	}
	
	@Autowired
	private EmailDispatchService emailDispatchService;

	private List<DispatchSlip> dispatchSlipBeans;

	public List<DispatchSlip> getDispatchSlipBeans() {
		return dispatchSlipBeans;
	}

	public void setDispatchSlipBeans(List<DispatchSlip> dispatchSlipBeans) {
		this.dispatchSlipBeans = dispatchSlipBeans;
	}

	private static List<RentedUpdate> rentedUpdates = new ArrayList<RentedUpdate>();
	private static List<PurchasedUpdate> purchasedUpdates = new ArrayList<PurchasedUpdate>();
	
	

	/**
	 * Redirects to manage orders page
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "admin/manageOrders", method = RequestMethod.GET)
	public String manageOrders(ModelMap modelMap) {
		LOGGER.debug(" OrderController.manageOrders method: START ");
		return "ManageOrders";
	}

	@RequestMapping(value = "/manageOrders", method = RequestMethod.GET)
	public String manageOrdersTemp(ModelMap modelMap) {
		LOGGER.debug(" OrderController.manageOrders method: START ");
		return "ManageOrders";
	}

	@RequestMapping(value = "/purchasedOrders", method = RequestMethod.GET)
	public String getPurchasedOrders(ModelMap modelMap) throws Exception {
		LOGGER.debug(" OrderController.getPurchasedOrders method: START ");
		modelMap.addAttribute("purchasedOrdersList", orderDao.getPurchasedOrders());
		modelMap.addAttribute("purchasedUpdateBeans", new ArrayList<PurchasedUpdate>());
		LOGGER.debug(" OrderController.getPurchasedOrders method: END ");
		return "PurchasedOrders";
	}

	@RequestMapping(value = "/rentedOrders", method = RequestMethod.GET)
	public String getRentedOrders(ModelMap modelMap) throws Exception {
		LOGGER.debug(" OrderController.getRentedOrders method: START ");
		modelMap.addAttribute("rentedOrdersList", orderDao.getRentedOrders());
		modelMap.addAttribute("rentedUpdateBeans", new ArrayList<RentedUpdate>());
		LOGGER.debug(" OrderController.getRentedOrders method: END ");
		return "RentedOrders";
	}

	/**
	 * Processes the rent updation requests.
	 * 
	 * @param rentedUpdateArr
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/updateRent", method = RequestMethod.POST, params = "dispatch")
	public String dispatchOrder(@ModelAttribute("rentedUpdateForm") RentedUpdateForm rentedUpdateArr, ModelMap modelMap)
			throws Exception {
		LOGGER.debug("dispatchOrder method: START");
		List<Integer> dispatchedOrders = null;
		if (rentedUpdateArr != null) {
			List<RentedUpdate> rentedUpdateList = rentedUpdateArr.getRentedUpdateList();
			OrderController.rentedUpdates = rentedUpdateList;
			dispatchedOrders = orderService.updateDispatch(rentedUpdateList);
		}
		modelMap.addAttribute("dispatchList", dispatchedOrders);
		LOGGER.debug("dispatchOrder method: END");
		return "redirect:/dispatchSlip";
	}*/
	
	
	@RequestMapping(value = "/updateRent/{dispatch}", method = RequestMethod.POST)
	public String dispatchRentedOrder(@PathVariable String dispatch,
@ModelAttribute("rentedUpdateForm") RentedUpdateForm rentedUpdateArr, ModelMap modelMap)throws Exception {
		LOGGER.debug("dispatchOrder method: START");
		if (dispatch.equals("dispatch")) {

			System.out.println("dispatchOrder method: START");
			List<BigDecimal> dispatchedOrders = null;
			if (rentedUpdateArr != null) {
				List<RentedUpdate> rentedUpdateList = rentedUpdateArr.getRentedUpdateList();
				OrderController.rentedUpdates = rentedUpdateList;
				dispatchedOrders = orderService.updateRentedDispatch(rentedUpdateList);
			}
			modelMap.addAttribute("dispatchList", dispatchedOrders);
			LOGGER.debug("dispatchOrder method: END");
			System.out.println("hello");
			return "redirect:/dispatchRentSlip";
		} else if(dispatch.equals("payment")){
		
			System.out.println("payment orders method: START");
			if (rentedUpdateArr != null) {
				List<RentedUpdate> rentedUpdateList = rentedUpdateArr.getRentedUpdateList();
				OrderController.rentedUpdates = rentedUpdateList;
				orderService.updateRentedPayment(rentedUpdateList);
			}
			LOGGER.debug("dispatchOrder method: END");
			return "redirect:/manageOrders";
		}
		else {
			System.out.println("return orders method: START");
			if (rentedUpdateArr != null) {
				List<RentedUpdate> rentedUpdateList = rentedUpdateArr.getRentedUpdateList();
				OrderController.rentedUpdates = rentedUpdateList;
				List<BigDecimal> updatedItems = orderService.updateRentedReturn(rentedUpdateList);
				emailDispatchService.sendRentMail(updatedItems);
			}
			LOGGER.debug("dispatchOrder method: END");
			return "redirect:/manageOrders";
		}

	}

	@RequestMapping(value = "/updatePurchase/{dispatch}", method = RequestMethod.POST)
	public String dispatchPurchaseOrder(@PathVariable String dispatch,
			@ModelAttribute("purchasedUpdateForm") PurchasedUpdateForm purchasedUpdateArr, ModelMap modelMap)
			throws Exception {
		LOGGER.debug("dispatchOrder method: START");
		if (dispatch.equals("dispatch")) {

			System.out.println("dispatchOrder method: START");
			List<BigDecimal> dispatchedOrders = null;
			if (purchasedUpdateArr != null) {
				List<PurchasedUpdate> purchasedUpdateList = purchasedUpdateArr.getPurchasedUpdateList();
				OrderController.purchasedUpdates = purchasedUpdateList;
				dispatchedOrders = orderService.updatePurchaseDispatch(purchasedUpdateList);
			}
			modelMap.addAttribute("dispatchList", dispatchedOrders);
			LOGGER.debug("dispatchOrder method: END");
			return "redirect:/dispatchSlip";
		} else {
			System.out.println("payment orders method: START");
			if (purchasedUpdateArr != null) {
				List<PurchasedUpdate> purchasedUpdateList = purchasedUpdateArr.getPurchasedUpdateList();
				OrderController.purchasedUpdates = purchasedUpdateList;
				orderService.updatePurchasePayment(purchasedUpdateList);
			}
			LOGGER.debug("dispatchOrder method: END");
			return "redirect:/manageOrders";
		}

	}
/*
	@RequestMapping(value = "/updateRent", method = RequestMethod.POST, params = "return")
	public String returnOrder(@ModelAttribute("rentedUpdateForm") RentedUpdateForm rentedUpdateArr, ModelMap modelMap)
			throws SapeStoreException {
		LOGGER.debug("returnOrder method: START");
		if (rentedUpdateArr != null) {
			List<RentedUpdate> rentedUpdateList = rentedUpdateArr.getRentedUpdateList();
			orderService.updateReturn(rentedUpdateList);
		}
		LOGGER.debug("returnOrder method: END");
		return "redirect:/manageOrders";
	}
*/
	/**
	 * Processes the requests for Dispatch Slip.
	 * 
	 * @param dispatchList
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/dispatchSlip", method = RequestMethod.GET)
	public String dispatchSlip(@ModelAttribute("dispatchList")  List<BigDecimal> dispatchList,ModelMap modelMap)
			throws SapeStoreException {
		LOGGER.debug("dispatchSlip method: START");
		 List<BigDecimal> list = dispatchList;
		List<DispatchSlip> dispatchSlips = null;
		try {
			dispatchSlips = orderService.getDispatchedOrders(list);
		} catch (SapeStoreSystemException e) {
			LOGGER.error("dispatchSlip method: ERROR: " + e);
			return ApplicationConstants.FAILURE;
		}
		modelMap.addAttribute("dispatchSlips", dispatchSlips);
		this.setDispatchSlipBeans(dispatchSlips);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("dispatchSlip method: END");
		}
		return "DispatchResult";
	}
	
	@RequestMapping(value = "/dispatchRentSlip", method = RequestMethod.GET)
	public String dispatchRentSlip(@ModelAttribute("dispatchList")  List<BigDecimal> dispatchList,ModelMap modelMap)
			throws SapeStoreException {
		LOGGER.debug("dispatchSlip method: START");
		 List<BigDecimal> list = dispatchList;
		List<DispatchSlip> dispatchSlips = null;
		try {
			dispatchSlips = orderService.getRentedDispatchedOrders(list);
		} catch (SapeStoreSystemException e) {
			LOGGER.error("dispatchSlip method: ERROR: " + e);
			return ApplicationConstants.FAILURE;
		}
		modelMap.addAttribute("dispatchSlips", dispatchSlips);
		this.setDispatchSlipBeans(dispatchSlips);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("dispatchSlip method: END");
		}
		return "DispatchResult";
	}

}