package com.sapestore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ReportService;
import com.sapestore.vo.ReportVO;

/**
 * This is a controller class for the admin report. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */

@Controller
public class ReportsController {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ReportsController.class.getName());
	
	@Autowired
	private ReportService reportService;
	
	private List<ReportVO> adminReportList;
	
	
	public List<ReportVO> getAdminReportList() {
		return adminReportList;
	}

	public void setAdminReportList(List<ReportVO> adminReportList) {
		this.adminReportList = adminReportList;
	}

	/**
	 * Processes the requests for admin report.
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/admin/adminReport", method = RequestMethod.GET)
	public String adminReport(ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug("adminReport method: START");
		this.setAdminReportList(reportService.getAdminReport());
		LOGGER.debug("adminReport method: END");
		return "AdminHome";
	}
	
	/**
	 * Processes the requests for defaulters report.
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/admin/defaultersReport", method = RequestMethod.GET)
	public String defaultersReport(ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug("defaultersReport method: START");
		modelMap.addAttribute("adminReportsList", reportService.getDefaultersAdminReport());
		LOGGER.debug("defaultersReport method: END");
		return "DefaulterReport";
	}
	
	/**
	 * Processes the Purchased/Rented report requests.
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/admin/purchasedRentedReport", method = RequestMethod.GET)
	public String purchasedRentedReport(ModelMap modelMap) throws SapeStoreException {
		LOGGER.debug("purchasedRentedReport method: START");
		modelMap.addAttribute("adminReportsList", reportService.getPurchasedRentedAdminReport());
		LOGGER.debug("purchasedRentedReport method: END");
		return "PurchasedRentedReport";
	}

}
