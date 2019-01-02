package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ReportsDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.service.ReportService;
import com.sapestore.vo.AdminDefaulterReport;
import com.sapestore.vo.ReportVO;

/**
 * Service class for Admin Report functionality.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

@Service("reportService")
public class ReportServiceImpl implements ReportService {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ReportServiceImpl.class.getName());
	
	@Autowired
	private ReportsDao reportsDao;

	@Override
	public List<ReportVO> getAdminReport() throws SapeStoreException {
		LOGGER.debug("getAdminReport method: START");
		List<ReportVO> adminReportList = null;
		adminReportList = reportsDao.getAdminReport();
		LOGGER.debug("getAdminReport method: END");
		return adminReportList;
	}
	
	@Override
	public List<ReportVO> getPurchasedRentedAdminReport() throws SapeStoreException {
		LOGGER.debug("getPurchasedRentedAdminReport method: START");
		List<ReportVO> adminReportsBeanList = null;
		adminReportsBeanList = reportsDao.getPurchasedRentedAdminReport();
		LOGGER.debug("getPurchasedRentedAdminReport method: END");
		return adminReportsBeanList;
	}
	
	@Override
	public List<AdminDefaulterReport> getDefaultersAdminReport() throws SapeStoreException {
		LOGGER.debug("getDefaultersAdminReport method: START");
		List<AdminDefaulterReport> adminReportsBeanList = null;
		adminReportsBeanList = reportsDao.getDefaultersAdminReport();
		LOGGER.debug("getDefaultersAdminReport method: END");
		return adminReportsBeanList;
	}

}
