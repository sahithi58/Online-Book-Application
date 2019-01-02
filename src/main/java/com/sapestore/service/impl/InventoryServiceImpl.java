package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.InventoryDao;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.service.InventoryService;
import com.sapestore.vo.BookVO;

/**
 * Service class for Manage Inventory functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

@Service("inventoryService")
public class InventoryServiceImpl implements InventoryService {

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(InventoryServiceImpl.class.getName());

	@Autowired
	private InventoryDao inventoryDao;

	@Autowired
	private ProductDao bookDao;

	@Override
	public List<BookVO> getAdminInventoryCategory(int pageid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryCategory method: START");
		}
		List<BookVO> adminInventoryBeanList = null;
		try {
			adminInventoryBeanList = inventoryDao.getAdminInventoryCategory(pageid);
		} catch (Exception e) {
			LOGGER.error("getAdminInventoryCategory method: ERROR: " + e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryCategory method: END");
		}
		return adminInventoryBeanList;
	}

	@Override
	public List<BookVO> getAdminInventoryBookName(int pageid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryBookName: START");
		}
		List<BookVO> adminInventoryBeanList = null;
		try {
			adminInventoryBeanList = inventoryDao.getAdminInventoryBookName(pageid);
		} catch (Exception e) {
			LOGGER.error("getAdminInventoryBookName: ERROR: " + e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryBookName: END");
		}
		return adminInventoryBeanList;
	}

	@Override
	public List<BookVO> getAdminInventoryAuthor(int pageid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryAuthor method: START");
		}
		List<BookVO> adminInventoryBeanList = null;
		try {
			adminInventoryBeanList = inventoryDao.getAdminInventoryAuthor(pageid);
		} catch (Exception e) {
			LOGGER.error("getAdminInventoryAuthor method: ERROR: " + e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryAuthor method: END");
		}
		return adminInventoryBeanList;
	}

	@Override
	public List<BookVO> getAdminInventoryPublisher(int pageid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryPublisher method: START");
		}
		List<BookVO> adminInventoryBeanList = null;
		try {
			adminInventoryBeanList = inventoryDao.getAdminInventoryPublisher(pageid);
		} catch (Exception e) {
			LOGGER.error("getAdminInventoryPublisher method: ERROR: " + e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryPublisher method: END");
		}
		return adminInventoryBeanList;
	}

	@Override
	public List<BookVO> getAdminInventoryQuantity(int pageid) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryQuantity method: START");
		}
		List<BookVO> adminInventoryBeanList = null;
		try {
			adminInventoryBeanList = inventoryDao.getAdminInventoryQuantity(pageid);
		} catch (Exception e) {
			LOGGER.error("getAdminInventoryQuantity method: ERROR: " + e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryQuantity method: END");
		}
		return adminInventoryBeanList;
	}

	@Override
	public void updateBooks(BookVO updateInventory) throws SapeStoreException {
		LOGGER.debug("updateBooks method: START");

		if (null != updateInventory) {
			bookDao.updateBooks(updateInventory);
		}
		LOGGER.debug("updateBooks method: END");
	}

	@Override
	public Long getRecordsQuantity() {
		// TODO Auto-generated method stub
		Long quantity=(long) 0;
		try {
			quantity = inventoryDao.getRecordsQuantity();
		} catch (Exception e) {
			LOGGER.error("getAdminInventoryQuantity method: ERROR: " + e);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getAdminInventoryQuantity method: END");
		}
		return quantity;
	}

}
