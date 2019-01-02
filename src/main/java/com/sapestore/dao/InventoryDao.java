package com.sapestore.dao;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.math.BigDecimal.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.vo.BookVO;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for admin report.
 * 
 * CHANGE 	LOG 
 * VERSION 	DATE 			AUTHOR MESSAGE 
 * 1.0 		20-06-2014 		SAPIENT Initial version
 */
@Repository
public class InventoryDao {
	
	
	@Autowired
	private SessionFactory factory;
	
	public SessionFactory getFactory() {
			return factory;
		}
	public void setFactory(SessionFactory factory) {
			this.factory = factory;
		} 
		
	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(InventoryDao.class.getName());

	/**
	 * Method to fetch admin report from the database.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	
	public List<BookVO> setBookDetailsBean(List<Book> book){
		LOGGER.debug(" InventoryDao.setBookDetailsBean method: START");
			List<BookVO> resultList = new ArrayList<>();
			BookVO vo=null;
			for (Book book1 : book) {
				if (book1 != null) {
					
				vo=new BookVO();
				vo.setBookAuthor(book1.getBookAuthor());
				vo.setBookDetailDesc(book1.getBookDetailDescription());
				vo.setBookPrice(book1.getBookPrice().toString());
				vo.setBookShortDesc(book1.getBookShortDescription());
				vo.setBookTitle(book1.getBookTitle());
				vo.setCategoryId(book1.getCategoryId().toString());
				vo.setCategoryName(book1.getCategoryName());
				vo.setQuantity(book1.getQuantity());
				vo.setIsbn(book1.getIsbn());
				vo.setPublisherName(book1.getPublisherName());
				vo.setRentAvailable(book1.getRentAvailability());
				vo.setRentPrice(book1.getRentPrice().toString());
				vo.setThumbPath(book1.getBookThumbImage());
				vo.setFullPath(book1.getBookFullImage());
				vo.setRentedQuantity(book1.getRentedQuantity());
				resultList.add(vo);
				}
			}
		
		LOGGER.debug(" InventoryDao.setBookDetailsBean method: END ");
		return resultList;
	}

	
	@SuppressWarnings("unchecked")
	public List<BookVO> getAdminInventoryCategory(int pageid) throws SapeStoreException {
		LOGGER.debug("InventoryDao.getAdminInventoryCategory method: START");
		List<Book> listBook = null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from Book b order by b.categoryId asc");
		query.setFirstResult(pageid);
		query.setMaxResults(10);
		listBook = query.list();
		transaction.commit();
        session.close();
		return setBookDetailsBean(listBook);
	}
	
	@SuppressWarnings("unchecked")
	public List<BookVO> getAdminInventoryBookName(int pageid) throws SapeStoreException {
		LOGGER.debug("InventoryDao.getAdminInventoryBookName() method: START");
		List<Book> listBook1 = null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from Book b order by upper(b.bookTitle) asc");
		query.setFirstResult(pageid);
		query.setMaxResults(10); 
		listBook1 = query.list();
		transaction.commit();
        session.close();	
		return setBookDetailsBean(listBook1);
	}	
	
	@SuppressWarnings("unchecked")
	public List<BookVO> getAdminInventoryAuthor(int pageid) throws SapeStoreException {
		LOGGER.debug("InventoryDao.getAdminInventoryAuthor() method: START");
		List<Book> listBook2 = null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from Book b order by upper(b.bookAuthor) asc");
		query.setFirstResult(pageid);
		query.setMaxResults(10); 
		listBook2 = query.list();
		transaction.commit();
        session.close();	
		return setBookDetailsBean(listBook2);
	}	
	
	@SuppressWarnings("unchecked")
	public List<BookVO> getAdminInventoryPublisher(int pageid) throws SapeStoreException {
		LOGGER.debug("InventoryDao.getAdminInventoryPublisher() method: START");
		List<Book> listBook3 = null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from Book b order by upper(b.publisherName) asc");
		query.setFirstResult(pageid);
		query.setMaxResults(10); 
		listBook3 = query.list();
		transaction.commit();
        session.close();			
		return setBookDetailsBean(listBook3);
	}	
	
	
	@SuppressWarnings("unchecked")
	public List<BookVO> getAdminInventoryQuantity(int pageid) throws SapeStoreException {
		LOGGER.debug("InventoryDao.getAdminInventoryQuantity() method: START");
		List<Book> listBook4 = null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from Book b order by b.quantity asc");
		query.setFirstResult(pageid);
		query.setMaxResults(10); 
		listBook4 = query.list();
		transaction.commit();
        session.close();			
		return setBookDetailsBean(listBook4);
	}
	
	public Long getRecordsQuantity() {
		// TODO Auto-generated method stub
		Session session=factory.openSession();
		String hql = "SELECT count(*) FROM Book";
		Query query = session.createQuery(hql);
		Long results = (Long) query.uniqueResult();
		session.close();
		return results;
	}	
}
