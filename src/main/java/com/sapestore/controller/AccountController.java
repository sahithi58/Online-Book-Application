package com.sapestore.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.TransactionHistoryDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.User;
import com.sapestore.service.AccountService;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.TransactionHistoryVO;

/**
 * This is a controller class for the login functionality.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Controller
@SessionAttributes(value = { "userId", "username", "IsAdmin" })
public class AccountController {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(AccountController.class.getName());

	// creating the account service object
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private TransactionHistoryDao transactionHistoryDao;

	// creating the user bean in which all the fields like city,gender getting
	// the data from the Data Base
	@RequestMapping(value = "/beforelogin", method = RequestMethod.GET)
	public String beforeLogin(ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug(" AccountController.beforeLogin method: START ");
		modelMap.addAttribute("user", new User());
		LOGGER.debug(" AccountController.beforeLogin method: END ");
		return "index";

	}

	/**
	 * Processes the login requests
	 * 
	 * @param userlogin
	 * @param modelMap
	 * @return
	 * @throws SapeStoreSystemException
	 */

	@RequestMapping(value = "/logintest", method = RequestMethod.GET)
	@ResponseBody
	public String login1(@RequestParam("name") String name, @RequestParam("password") String password,
			final ModelMap modelMap, HttpServletRequest httpServletRequest, HttpSession httpSession)
			throws SapeStoreException {
		LOGGER.debug("login method: START");
		User user = new User();
		user.setUserId(name);
		user.setPassword(password);
		System.out.println(user);
		System.out.println("in the testing");

		User localUserlogin = accountService.authenticate(user);

		String flag = "false";
		if (localUserlogin == null) {

			// message="Please provide the correct login credentials";
			flag = "true";

		}
		LOGGER.debug("login method: END");

		return flag;
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public User login(@RequestParam("name") String name, @RequestParam("password") String password,
			@ModelAttribute User user, final ModelMap modelMap, HttpServletRequest httpServletRequest,
			HttpSession httpSession) throws SapeStoreException {

		System.out.println("in the login");
		User user1 = new User();
		user1.setUserId(name);
		user1.setPassword(password);

		String url = httpServletRequest.getRequestURL().toString();
		String forwardStr = null;
		System.out.println(url);
		System.out.println(user1);
		User localUserlogin = accountService.authenticate(user1);
		System.out.println(localUserlogin);
		User json = null;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			json = objectMapper.readValue(objectMapper.writeValueAsString(localUserlogin), User.class);
			System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* if (localUserlogin != null && localUserlogin.getIsAdmin()!=null) { */
		/*
		 * if (localUserlogin.getIsAdmin().equalsIgnoreCase("Y")) { forwardStr =
		 * "redirect:/admin/manageInventory";
		 * 
		 * } else { forwardStr =
		 * "redirect:/bookListByCat?categoryId=0&categoryName=Top Rated&checkMe="
		 * +httpSession.getAttribute("checkMe");
		 * 
		 * 
		 * 
		 * }
		 */

		/*
		 * else { modelMap.addAttribute("errorMessage1",
		 * "Previous login failed."); forwardStr = "redirect:/welcome"; }
		 */

		modelMap.addAttribute("userId", localUserlogin.getUserId());
		modelMap.addAttribute("username", localUserlogin.getName());
		modelMap.addAttribute("IsAdmin", localUserlogin.getIsAdmin());

		LOGGER.debug("login method: END");
		return json;
	}

	/**
	 * Processes the Logout requests
	 * 
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(WebRequest request, SessionStatus status, ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession) throws SapeStoreException {
		LOGGER.debug("logout method: START");
		status.setComplete();
		httpServletRequest.getSession().invalidate();
		request.removeAttribute("userId", WebRequest.SCOPE_SESSION);
		request.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		request.removeAttribute("checkMe", WebRequest.SCOPE_SESSION);
		LOGGER.debug("logout method: END");
		return "redirect:/welcome?checkMe=false";
	}

	@RequestMapping(value = "/transactionHistory", method = RequestMethod.GET)
	public String searchBook(@ModelAttribute("transactionHistory") TransactionHistoryVO transactionHistory,
			@ModelAttribute(value = "userId") String userId,
			@RequestParam(value = "page", required = false) String pageString, ModelMap modelMap,
			HttpServletRequest httpServletRequest, HttpSession httpSession) throws SapeStoreException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("searchBook method: START");
		}

		List<BookVO> bookList = new ArrayList<BookVO>();
		List<TransactionHistoryVO> transactionHistoryList = new ArrayList<TransactionHistoryVO>();
		try {

			transactionHistoryList = transactionHistoryDao.getTransactionHistory(userId);

			List<TransactionHistoryVO> newTranxList = new ArrayList<TransactionHistoryVO>();
			int page = 1;
			if (pageString != null) {
				page = Integer.parseInt(pageString);
				int limiter = (page - 1) * 4;
				for (int i = limiter; i < (limiter + 4); i++) {
					if (i < transactionHistoryList.size()) {
						TransactionHistoryVO tranx = transactionHistoryList.get(i);
						newTranxList.add(tranx);
					}
				}
			} else {
				for (int i = 0; i < 4; i++) {
					if (i < transactionHistoryList.size()) {
						TransactionHistoryVO tranx = transactionHistoryList.get(i);
						newTranxList.add(tranx);
					}
				}
			}


			modelMap.addAttribute("page", page);
			modelMap.addAttribute("newTranxCount", newTranxList.size());
			modelMap.addAttribute("tranxCount", transactionHistoryList.size());

			modelMap.addAttribute("transactionHistoryList", newTranxList);

			/*
			 * if (checkMeFromSession!=null && (boolean) checkMeFromSession) {
			 * bookList.addAll(bookService.getBookFromWebService(0)); }
			 */

		} catch (SapeStoreSystemException e) {
			LOGGER.error("searchBook method: ERROR: " + e);
			modelMap.addAttribute("errorMessage", "Error in opening the searchBook page.");
			return "redirect:/errorPage";
		} catch (Exception e) {
			modelMap.addAttribute("errorMsg", "Transaction history is empty");
		}

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("searchBook method: ModelMap: " + modelMap);
			LOGGER.debug("searchBook method: END");
		}
		return "transactionHistory";

	}

}
