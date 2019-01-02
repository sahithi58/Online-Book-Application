package com.sapestore.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.WishList;
import com.sapestore.vo.BookVO;

@Repository
public class WishListDao {

	
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(WishListDao.class.getName());
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private SessionFactory factory;

	
	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	
	public boolean addWishListDao(WishList wishList) {
		
		String uId=wishList.getUserId();
		String bookId=wishList.getIsbn();
		boolean flag=false;
		
		String query = "select isbn from WishList where userId like :userid";
		@SuppressWarnings("unchecked")
		ArrayList<String> isbnList = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid",'%' + uId+ '%');
	System.out.println(isbnList);
	

	for(String v:isbnList)
	{
		while(v.equals(bookId))
		{
			flag=true;
			break;
			
		}
	}
		if(!flag)
		{
		hibernateTemplate.save(wishList);
		}
		
		
		LOGGER.debug("wishList IS ADDED INTO DB");
	
		return flag;
		
	}
	
	public List wishlistbooks(String userId){
		
		String query = "select isbn from WishList where userId like :userid";
		@SuppressWarnings("unchecked")
		ArrayList<String> isbnList = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid",'%' + userId+ '%');
		System.out.println(isbnList);
		
		
		ArrayList<Book> list=new ArrayList<Book>();
		ArrayList<Book> DaoList=new ArrayList<Book>();
		for(String v:isbnList)
		{
			
			System.out.println(v);
			String query1= "from Book where isbn like :isbn";
			DaoList= (ArrayList<Book>)hibernateTemplate.findByNamedParam(query1, "isbn",'%' + v+ '%');
			list.addAll((DaoList));
			
		}
		ArrayList<BookVO> listBookVo=new ArrayList<BookVO>();
		System.out.println(list);
		BookVO bookVo = new BookVO();
		for (Book bookItem : list) {
			bookVo = setBookDetailBean(bookItem);
			listBookVo.add(bookVo);
		}
		return listBookVo;
		
		
	}

	public void deleteBook(String isbn) {
		// TODO Auto-generated method stub
	System.out.println("isbn is"+isbn);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String query="delete from WishList w where w.isbn like :isbn";
		Query q = session.createQuery(query);
		q.setString("isbn",isbn);
		q.executeUpdate();
		  t.commit();
		session.close();
		
		
	}
	
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

		}
		return vo;
	}

}
