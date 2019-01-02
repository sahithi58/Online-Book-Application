package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.vo.BookVO;

/**
 * Service interface for Manage Inventory functionality.
 * 
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */

public interface InventoryService {

	/**
	 * Returns admin inventory list.
	 * @param pageid 
	 * 
	 * @return
	 */
	public List<BookVO>getAdminInventoryCategory(int pageid);

	public List<BookVO> getAdminInventoryBookName(int pageid);

	public List<BookVO> getAdminInventoryAuthor(int pageid);

	public List<BookVO> getAdminInventoryPublisher(int pageid);

	public List<BookVO> getAdminInventoryQuantity(int pageid);

	/**
	 * Updates book details.
	 * 
	 * @param updateInventory
	 * @throws SapeStoreSystemException
	 */
	void updateBooks(BookVO updateInventory) throws SapeStoreException;

	public Long getRecordsQuantity();

}
