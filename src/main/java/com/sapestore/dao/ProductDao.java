package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookCategory;
import com.sapestore.hibernate.entity.Search;
import com.sapestore.vo.BookVO;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for retrieving the book's list from the database.
 *
 * CHANGE LOG VERSION DATE AUTHOR MESSAGE 1.0 20-06-2014 SAPIENT Initial version
 */
@Repository
public class ProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory factory;

	String isbn;
	int commentsSize;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	/**
	 * Logger for log messages.
	 */
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ProductDao.class.getName());

	/**
	 * Method to fetch the book list from the database.
	 * 
	 * @param categoryId
	 * @param checkMeFromSession
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */

	@SuppressWarnings("unchecked")
    public List<BookVO> getBookList(int categoryId, Object checkMeFromSession) throws SapeStoreException {
           Session session = factory.openSession();
           Transaction transaction = session.beginTransaction();
           List<Book> listBook = new ArrayList<Book>();
           List<BookVO> listBookVo = new ArrayList<BookVO>();

           if (checkMeFromSession != null && (boolean) checkMeFromSession) {
                  Query query = session.createQuery("from Book b where (:categoryId is 0 or b.categoryId = :categoryId ) and (b.quantity - b.rentedQuantity) > 0");
                  query.setParameter("categoryId", categoryId);
                  listBook = query.list();

           } else {
                  Query query = session.createQuery(
                               "from Book b where (:categoryId is 0 or b.categoryId = :categoryId ) and (b.quantity - b.rentedQuantity) > 0 and b.isFromPartnerStore is NULL");
                  query.setParameter("categoryId", categoryId);
                  listBook = query.list();

           }
           transaction.commit();
           session.close();
           BookVO bookVo = new BookVO();
           for (Book bookItem : listBook) {
                  bookVo = setBookDetailBean(bookItem);
                  listBookVo.add(bookVo);
           }
           return listBookVo;
    }

	public List<BookVO> getBookListBySearch(Search search, boolean partnerStoreCheck, boolean mostCommentsCheck) throws SapeStoreException {         
        List<Book> listBook = null;
        List<BookVO> listBookVo=new ArrayList<BookVO>(); 
        System.out.println(partnerStoreCheck);
        
               Session session=factory.openSession();
                            Transaction transaction=session.beginTransaction();
                     
                            if(partnerStoreCheck){
                  Query query=session.createQuery("from Book b where (:publisherName is NULL or upper(b.publisherName) LIKE :publisherName ) and (:categoryId is 0 or b.categoryId = :categoryId ) and (:bookAuthor is NULL or upper(b.bookAuthor) LIKE :bookAuthor ) and (:bookTitle is NULL or upper(b.bookTitle) LIKE :bookTitle ) and (b.quantity - b.rentedQuantity) > 0 order by b.averageRating desc ");
                  query.setParameter("publisherName", "%"+search.getPublisher().toUpperCase()+"%");
                  query.setParameter("bookAuthor",  "%"+search.getBookAuthor().toUpperCase()+"%");
                  query.setParameter("bookTitle",  "%"+search.getBookTitle().toUpperCase()+"%");
                  query.setParameter("categoryId", search.getCategoryId()); 
                  listBook=query.list();     
                  System.out.println(listBook);
           }
           else{
                  Query query=session.createQuery("from Book b where (:publisherName is NULL or upper(b.publisherName) LIKE :publisherName ) and (:categoryId is 0 or b.categoryId = :categoryId ) and (:bookAuthor is NULL or upper(b.bookAuthor) LIKE :bookAuthor ) and (:bookTitle is NULL or upper(b.bookTitle) LIKE :bookTitle ) and (b.quantity - b.rentedQuantity) > 0 and b.isFromPartnerStore is NULL order by b.averageRating desc ");
           query.setParameter("publisherName",  "%"+search.getPublisher().toUpperCase()+"%");
           query.setParameter("bookAuthor",  "%"+search.getBookAuthor().toUpperCase()+"%");
           query.setParameter("bookTitle",  "%"+search.getBookTitle().toUpperCase()+"%");
           query.setParameter("categoryId", search.getCategoryId()); 
           
           listBook=query.list();
           System.out.println(listBook);

           }

                            
                            BookVO bookVo=new BookVO();
                            for(Book bookItem:listBook){
                                   bookVo=setBookDetailBean(bookItem);
                                   listBookVo.add(bookVo);}
                                   if(mostCommentsCheck)
                                   for(BookVO b: listBookVo){
                     isbn=b.getIsbn();
                     Query thirdQuery = session.createQuery("select brc.bookComments from BookRatingComments brc where (upper(brc.isbn) LIKE :isbn)"); 
                      thirdQuery.setParameter("isbn", isbn);
                     List<String> commentsList = thirdQuery.list();
                     commentsSize = commentsList.size();
                     b.setNumberOfComments(commentsSize);
                            }

                                   transaction.commit();
                                   session.close();
                            return listBookVo;         

 }

	/**
	 * Method to fetch the book's category list.
	 * 
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	@SuppressWarnings("unchecked")
	public List<BookCategory> getBookCategoryList() throws SapeStoreException {
		List<BookCategory> listBookCategories = (List<BookCategory>) hibernateTemplate
				.findByNamedQuery("BookCategory.findAll");
		return listBookCategories;
	}

	/**
	 * deleteBook method updates the quantity of the selected book to zero in
	 * the database
	 * 
	 * @param isbn
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public void deleteBook(String isbn) throws SapeStoreException {
		LOGGER.debug(" BookDao.deleteBook method: START");
		Book book = hibernateTemplate.get(Book.class, isbn.trim());
		System.out.println("current book quantity: " + book.getQuantity());
		System.out.println("current rented book quantity: " + book.getRentedQuantity());
		// if(book.getRentedQuantity().intValue()==0)
		book.setQuantity(0);
		hibernateTemplate.saveOrUpdate(book);
	}

	/**
	 * Method to add a new book to the database.
	 * 
	 * @param vo
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public void addNewBooks(BookVO vo) throws SapeStoreException {
		LOGGER.debug(" ProductDao.addNewBooks method: START");
		try {
			Book book = new Book();
			BookCategory bookCategory = new BookCategory();

			book.setIsbn(vo.getIsbn());
			book.setPublisherName(vo.getPublisherName());
			bookCategory.setCategoryId(Integer.parseInt(vo.getCategoryId()));
			book.setCategoryId(Integer.parseInt(vo.getCategoryId()));
			book.setBookCategory(bookCategory);
			book.setRentedQuantity(0);
			book.setBookTitle(vo.getBookTitle());
			book.setQuantity(vo.getQuantity());
			book.setBookAuthor(vo.getBookAuthor());
			book.setBookThumbImage(vo.getThumbPath());
			book.setBookFullImage(vo.getFullPath());
			book.setBookPrice(new BigDecimal(vo.getBookPrice()));
			book.setBookShortDescription(vo.getBookShortDesc());
			book.setBookDetailDescription(vo.getBookDetailDesc());
			book.setRentAvailability(vo.getRentAvailable());
			if (vo.getRentPrice() == null) {
				book.setRentPrice(new BigDecimal("0"));
			} else {
				book.setRentPrice(new BigDecimal(vo.getRentPrice()));
				Integer rentfee = Integer.parseInt(vo.getRentPrice());
				BigDecimal latefee = new BigDecimal(rentfee / 15);
				book.setLateFee(latefee);
			}

			// book.setRentPrice(new BigDecimal(vo.getRentPrice()));
			book.setCreatedDate(new java.util.Date());
			book.setUpdatedDate(new java.util.Date());
			book.setIsActive("Y");
			hibernateTemplate.save(book);
		} catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
		}
		LOGGER.debug(" ProductDao.addNewBooks method: END ");
	}

	/**
	 * Update Book method updates the detail of the corresponding book.
	 * 
	 * @param updateInventoryBean
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public void updateBooks(BookVO updateInventoryBean) throws SapeStoreException {
		LOGGER.debug(" ProductDao.updateBooks method: START ");
		// System.out.println("in update book dao");
		System.out.println(updateInventoryBean);
		try {
			Book book = hibernateTemplate.get(Book.class, updateInventoryBean.getOldIsbn().trim());
			if (book != null) {
				BookCategory bookCategory = new BookCategory();

				book.setIsbn(updateInventoryBean.getIsbn().trim());
				book.setPublisherName(updateInventoryBean.getPublisherName());
				book.setBookTitle(updateInventoryBean.getBookTitle());
				book.setQuantity(updateInventoryBean.getQuantity());
				book.setBookAuthor(updateInventoryBean.getBookAuthor());
				book.setBookPrice(new BigDecimal(updateInventoryBean.getBookPrice()));
				book.setBookShortDescription(updateInventoryBean.getBookShortDesc());
				book.setRentAvailability(updateInventoryBean.getRentAvailable());
				if (updateInventoryBean.getRentPrice() == null) {
					book.setRentPrice(new BigDecimal("0"));
				} else {
					book.setRentPrice(new BigDecimal(updateInventoryBean.getRentPrice().trim()));
				}
				book.setBookDetailDescription(updateInventoryBean.getBookDetailDesc());
				bookCategory.setCategoryId(Integer.parseInt(updateInventoryBean.getCategoryId()));

				book.setBookCategory(bookCategory);
				if (updateInventoryBean.getThumbImage().getOriginalFilename() != "")
					book.setBookThumbImage(updateInventoryBean.getThumbPath());
				if (updateInventoryBean.getFullImage().getOriginalFilename() != "")
					book.setBookFullImage(updateInventoryBean.getFullPath());
				book.setUpdatedDate(new java.util.Date());
				hibernateTemplate.update(book);
				LOGGER.debug(" Book is updated ");
			}
		} catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
		}
		LOGGER.debug(" ProductDao.updateBooks method: END ");
	}

	/**
	 * Method to get book details from the database.
	 *
	 * @param isbn
	 * @return
	 * @throws ConnectionException
	 * @throws TransactionExecutionException
	 */
	public BookVO getBookDetails(String isbn) throws SapeStoreException {
		LOGGER.debug(" ProductDao.getBookDetails method: START");
		Book book = null;
		try {
			book = hibernateTemplate.get(Book.class, isbn.trim());
		} catch (SapeStoreSystemException se) {
			LOGGER.fatal("A DB exception occured while inserting the book's information", se);
		}
		return setBookDetailBean(book);
	}

	/**
	 * Converts the Map representation of book details HashMap to its DO
	 * representation
	 * 
	 * @param hash
	 * @return
	 */
	private BookVO setBookDetailBean(Book book) {
		BookVO vo = null;
		if (book != null) {
			vo = new BookVO();
			vo.setIsbn(book.getIsbn());
			vo.setBookTitle(book.getBookTitle());
			vo.setBookAuthor(book.getBookAuthor());
			vo.setBookPrice(book.getBookPrice().toString());
			vo.setThumbPath(book.getBookThumbImage());
			vo.setQuantity(book.getQuantity());
			vo.setBookFullImage(book.getBookFullImage());
			vo.setAverageRating(book.getAverageRating());
			vo.setRentPrice((book.getRentPrice().toString()));
			vo.setCategoryName(book.getCategoryName());
			vo.setCategoryId(book.getCategoryId().toString());
			vo.setPublisherName(book.getPublisherName());
			vo.setRentAvailable(book.getRentAvailability());

		}
		return vo;
	}

}
