package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.dao.ProductDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.Search;
import com.sapestore.partner.services.SSPartnerBooksListBean;
import com.sapestore.partner.services.SSPartnerWebService;
import com.sapestore.partner.services.SSPartnerWebServicePortType;
import com.sapestore.service.BookService;
import com.sapestore.vo.BookVO;

/**
 * Service class for fetching books information.
 * 
 * CHANGE LOG 
 * VERSION 	DATE 		AUTHOR 	MESSAGE 
 * 1.0 		20-06-2014 	SAPIENT Initial version
 */

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(BookServiceImpl.class.getName());

	@Autowired
	private ProductDao bookDao;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	/**
	 * Returns list of books belonging to the specified category
	 */
	@Override
	public List<BookVO> getBookList(int catId,Object checkMeFromSession) throws SapeStoreException {	
		LOGGER.debug("getBookList method: START");
		List<BookVO> bookBeanList = null; 
		bookBeanList = bookDao.getBookList(catId,checkMeFromSession);
		
		if(bookBeanList!=null && bookBeanList.size()>0)
		{
			Collections.sort(bookBeanList, new Comparator<BookVO>() {
				@Override
				public int compare(final BookVO arg0, final BookVO arg1) {
					if(arg0.getAverageRating() == null && arg1.getAverageRating() == null){
						return 0;
					}else if(arg0.getAverageRating() == null){
						return 1;
					}else if(arg1.getAverageRating() == null){
						return -1;
					}else{
						return arg1.getAverageRating().compareTo(arg0.getAverageRating());
					}
				}
			});
		}
		LOGGER.debug("getBookList method: END");
		return bookBeanList;
	}

	/**
	 * Returns list of book categories
	 */
	@Override
	public List<BookCategory> getCategoryList()	throws SapeStoreException {
		LOGGER.debug("getCategoryList method: START");
		List<BookCategory> bookCategoryBeanList = null;
		bookCategoryBeanList = bookDao.getBookCategoryList();
		if(bookCategoryBeanList.size()>0)
		{
			Collections.sort(bookCategoryBeanList, new Comparator<BookCategory>() {

				@Override
				public int compare(final BookCategory arg0, final BookCategory arg1) {
					return arg0.getCategoryName().compareTo(arg1.getCategoryName());
				}
			});
		}
		LOGGER.debug("getCategoryList method: END");
		return bookCategoryBeanList;
	}
	
	@Override
	public List<Book> getBookFromWebService(int catId) {
		LOGGER.debug("getBookFromWebService method: START");
		SSPartnerWebService service  = new SSPartnerWebService();
		SSPartnerWebServicePortType client = service.getSSPartnerWebServiceHttpSoap11Endpoint();
		List<SSPartnerBooksListBean> partnerBookList = client.getBookList(String.valueOf(catId));
		LOGGER.debug("getBookFromWebService method: END");
		return mapToBookListBean (partnerBookList);
	}
	
	/**
	 * Maps book list collected from partner services to book bean
	 * @param partnerList
	 * @return
	 */
	private List<Book> mapToBookListBean (List<SSPartnerBooksListBean> partnerList) {
		List<Book> bookListBeanList = new ArrayList<Book>();
		Book bookBean = null;
		
		for (SSPartnerBooksListBean partnerBook : partnerList) {
			bookBean = new Book();
			bookBean.setIsActive(partnerBook.getActive());
			bookBean.setBookAuthor(partnerBook.getBookAuthor());
			bookBean.setBookDetailDescription(partnerBook.getBookDetailDesc());
			bookBean.setBookPrice(new BigDecimal(partnerBook.getBookPrice()));
			bookBean.setBookShortDescription(partnerBook.getBookShortDesc());
			bookBean.setBookTitle(partnerBook.getBookTitle());
			bookBean.setCategoryId(partnerBook.getCategoryIdpr());
			bookBean.setBookFullImage(partnerBook.getFullImageUrl());
			bookBean.setIsbn(partnerBook.getIsbn());
			bookBean.setPublisherName(partnerBook.getPublisherName());
			bookBean.setQuantity(partnerBook.getQuantity());
			bookBean.setBookThumbImage(partnerBook.getThumbImageUrl());			
			bookListBeanList.add(bookBean);
		}
		return bookListBeanList;
	}
	
	/**
	 * Add new books to the store
	 * @param addBooks
	 * @throws SapeStoreSystemException
	 */
	@Override
	public void addBooks(BookVO addBooks) throws SapeStoreException {
		LOGGER.debug("addBooks method: START");			
		 if (null != addBooks) {
		    	bookDao.addNewBooks(addBooks);
		    }		
		LOGGER.debug("addBooks method: END");
	}
	
	@Override
	public void deleteBook(String isbn) throws SapeStoreException {
		LOGGER.debug("deleteBook method: START");
		if (null != isbn) {
			bookDao.deleteBook(isbn);
		}
		LOGGER.debug("deleteBook method: END");
	}

	@Override
	public boolean checkIsbn(String isbn) {
		boolean flag = false;
		LOGGER.debug("CHECKING THE ISBN");
		
		String query = "from Book where isbn like :isbn";
		ArrayList isbnList = (ArrayList) hibernateTemplate.findByNamedParam(query, "isbn",
				'%' + isbn + '%');
		if(isbnList.isEmpty())
		{
			System.out.println("isbn is doesnt exist");
			return true;
		}
		else
			System.out.println("isbn already exixts");
			return flag;

	}
		
	

	@Override
	public List<BookVO> getBookListBySearch(Search search, boolean partnerStoreCheck, boolean mostCommentsCheck) throws SapeStoreException {
		// TODO Auto-generated method stub
		LOGGER.debug("getBookList method: START");
		List<BookVO> bookBeanList = null; 
		bookBeanList = bookDao.getBookListBySearch(search, partnerStoreCheck, mostCommentsCheck); 
if(bookBeanList!=null && bookBeanList.size()>0)
		{
			Collections.sort(bookBeanList, new Comparator<BookVO>() {
				public int compare(final BookVO arg0, final BookVO arg1) {
					if(arg0.getAverageRating() == null && arg1.getAverageRating() == null){
						return 0;
					}else if(arg0.getAverageRating() == null){
						return 1;
					}else if(arg1.getAverageRating() == null){
						return -1;
					}else{
						return arg1.getAverageRating().compareTo(arg0.getAverageRating());
					}
				}
			});
		} 
LOGGER.debug("getBookList method: END");
		return bookBeanList; 	}
	
	@Override
	public List<BookVO> getBookListByComment(Search search, boolean partnerStoreCheck, boolean mostCommentsCheck) throws SapeStoreException {
			// TODO Auto-generated method stub
			LOGGER.debug("getBookListByComments method: START");
			List<BookVO> bookBeanList = null; 
			bookBeanList = bookDao.getBookListBySearch(search, partnerStoreCheck, mostCommentsCheck);
	if(bookBeanList!=null && bookBeanList.size()>0)
	{
		Collections.sort(bookBeanList, new Comparator<BookVO>() {
			public int compare(final BookVO arg0, final BookVO arg1) {
				if(arg0.getNumberOfComments() == null && arg1.getNumberOfComments() == null){
					return 0;
				}else if(arg0.getNumberOfComments() == null){
					return 1;
				}else if(arg1.getNumberOfComments() == null){
					return -1;
				}else{
					return arg1.getNumberOfComments().compareTo(arg0.getNumberOfComments());
				}
			}
		});
	} 
	LOGGER.debug("getBookListByComments method: END");
	return bookBeanList;
	}

}