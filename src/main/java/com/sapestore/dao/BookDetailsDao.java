package com.sapestore.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.BookRatingComments;
import com.sapestore.hibernate.entity.User;
import com.sapestore.vo.BookRatingCommentsVO;
import com.sapestore.vo.BookVO;
import com.sapestore.vo.UserVO;
//by

@Repository
public class BookDetailsDao {
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
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(BookDetailsDao.class.getName());
	
	public String getUserName(String userId) {
		LOGGER.debug(" BookDetailsDao.getUserName method: START");
		
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Query query=session.createQuery("select u.name from User u where u.userId like:userId");
		query.setString("userId", userId);
		String name= (String) query.uniqueResult();
		transaction.commit();
		session.close();
		return name;
		
	}

	public BookVO getBookDetails(String isbn) {
		LOGGER.debug(" BookDetailsDao.getBookDetails method: START");
		Book book = null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		book= (Book) session.get(Book.class, isbn.trim());
		transaction.commit();
		session.close();
    	return setBookDetailBean(book);
		
	}

	private BookVO setBookDetailBean(Book book) {
		BookVO vo = null;
        if (book != null) {
        	vo = new BookVO();
            vo.setIsbn(book.getIsbn());
            vo.setBookTitle(book.getBookTitle());
            vo.setBookAuthor(book.getBookAuthor());    
            vo.setBookPrice(book.getBookPrice().toString());
            vo.setRentPrice(book.getRentPrice().toString());
            vo.setThumbPath(book.getBookThumbImage());
            vo.setAverageRating(findAverageRating(book.getIsbn()));
            vo.setRentAvailable(book.getRentAvailability());
            vo.setQuantity(book.getQuantity());
            vo.setPublisherName(book.getPublisherName());
            vo.setBookDetailDesc(book.getBookDetailDescription());
            vo.setCategoryName(book.getCategoryName());
            vo.setCategoryId(book.getCategoryId().toString());
        } 
        return vo;
	}

	
	public List<BookRatingCommentsVO> getBookComments(String isbn) {
		LOGGER.debug(" BookDetailsDao.getBookComments method: START");
		List<BookRatingComments> bookRatingComments=null;
		Session session=factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query=session.createQuery("from BookRatingComments b where b.isbn like:isbn order by b.bookCommentDate desc");
		query.setString("isbn", isbn);
		bookRatingComments=query.list();
		List<BookRatingCommentsVO> bookRatingCommentsBeanList=new ArrayList<>();
		for(BookRatingComments b:bookRatingComments){
			
			bookRatingCommentsBeanList.add(setBookRatingCommentsBean(b));
		}
		transaction.commit();
		session.close();
		return bookRatingCommentsBeanList;
	}

	private BookRatingCommentsVO setBookRatingCommentsBean(BookRatingComments b) {
		BookRatingCommentsVO comment = null;
		if(b!= null){
			comment=new BookRatingCommentsVO();
			comment.setBookCommentDate(b.getBookCommentDate());
			comment.setBookComments(b.getBookComments());
			comment.setBookRating(b.getBookRating());
			comment.setUserId(getUserName(b.getUserId()));
			
			
		}
		return comment;
		
		
	}
   public int getTotalRatings(String isbn){
	Session session=factory.openSession();
	Transaction transaction=session.beginTransaction();
	Query query=session.createQuery("from BookRatingComments b where b.isbn like:isbn");
	query.setString("isbn", isbn);
	int ratings=query.list().size();
	transaction.commit();
	session.close();
	return ratings;
}
  
	   public BigDecimal findAverageRating(String isbn){
	   		LOGGER.debug(" To get Average rating");
	   		List<BookRatingComments> bookRatingComments=null;
	   		Session session=factory.openSession();
	   		Transaction transaction=session.beginTransaction();
	   		Query query=session.createQuery("from BookRatingComments b where b.isbn like:isbn");
	   		query.setString("isbn", isbn);
	   		bookRatingComments=query.list();
	   		int rating=0;
	   		int size=query.list().size();
	   		if(size==0){
	   			return new BigDecimal(0);
	   		}
	   		else{
	   		for(BookRatingComments b:bookRatingComments){
	   			rating=rating + b.getBookRating();
	   		}
	   		}
	   		double average=rating/size;
	   		
	   		BigDecimal avg= BigDecimal.valueOf(average);
	   		transaction.commit();
	   		session.close();
	   		session=factory.openSession();
			transaction=session.beginTransaction();
			Query query1=session.createQuery("update Book b set b.averageRating=? where b.isbn like:isbn");
			
			query1.setParameter(0, avg);
			query1.setString("isbn", isbn);
			query1.executeUpdate();
			
			transaction.commit();
			session.close(); 

	   		return avg;
	   	} 

}
