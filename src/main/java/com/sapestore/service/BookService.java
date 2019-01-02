package com.sapestore.service;

import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.Search;
import com.sapestore.vo.BookVO;
import com.sun.mail.iap.ConnectionException;

/**
 * Service interface for fetching books information.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

public interface BookService {
	
	/**
	 * Returns list of book belonging to the specified category.
	 * @param catId
	 * @param checkMeFromSession 
	 * @return
	 * @throws SapeStoreSystemException
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public List<BookVO>  getBookList(int catId, Object checkMeFromSession) throws SapeStoreException;
	public List<BookVO>  getBookListBySearch(Search search, boolean partnerStoreCheck, boolean mostCommentsCheck ) throws SapeStoreException;
	public List<BookVO> getBookListByComment(Search search, boolean partnerStoreCheck, boolean mostCommentsCheck) throws SapeStoreException;


	/**
	 * Returns list of categories
	 * @return
	 * @throws SapeStoreSystemException
	 */
	public List<BookCategory> getCategoryList() throws SapeStoreException;

	/**
	 * Returns list of books pulled from the partner services
	 * @param catId
	 * @return
	 */
	public List<Book> getBookFromWebService(int catId);
	
	/**
	 * Add new books to the store
	 * @param addBooks
	 * @throws SapeStoreSystemException
	 */
	void addBooks(BookVO addBooks) throws SapeStoreException;
	
	/**
	 * Deletes the book with the specified ISBN
	 * @param isbn
	 * @throws SapeStoreSystemException
	 */
	void deleteBook(String isbn) throws SapeStoreException;
	public boolean checkIsbn(String isbn);
}
