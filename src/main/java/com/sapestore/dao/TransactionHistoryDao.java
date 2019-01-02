package com.sapestore.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.OrderVO;
import com.sapestore.vo.TransactionHistoryVO;

@Repository("transactionHistoryDao")
public class TransactionHistoryDao {

	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(TransactionHistoryDao.class.getName());

	@SuppressWarnings("null")
	public List<TransactionHistoryVO> getTransactionHistory(String userId) {
		LOGGER.debug(" TransactionHistoryDao.getTransactionHistory method: START");
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		List<OrderItemInfo> orderItemList;
		List<TransactionHistoryVO> transactionList = new ArrayList<TransactionHistoryVO>();
		Query query = session.createQuery("from OrderInfo o where o.userId = :uid order by orderDate desc");
		query.setParameter("uid", userId);
		List<OrderInfo> orderList = query.list();
		Collections.sort(orderList, new Comparator<OrderInfo>() {
		    public int compare(OrderInfo t1, OrderInfo t2) {
		        return t2.getCreatedDate().compareTo(t1.getCreatedDate());
		    }
		}); 

		int i = 1;
		for (OrderInfo element : orderList) {
			i = i + 1;
			if (element.getOrderId() != null) {
				Query query1 = session.createQuery("from OrderItemInfo where orderId = :oid");
				query1.setParameter("oid", element.getOrderId());
				List<OrderItemInfo> itemList = query1.list();
				if (itemList != null) {
					for (OrderItemInfo orderItem : itemList) {
						Query query2 = session.createQuery("from Book where isbn = :isbn");
						query2.setParameter("isbn", orderItem.getIsbn());
						Book book = (Book) query2.uniqueResult();
						TransactionHistoryVO transactionHistoryVO = new TransactionHistoryVO();
						transactionHistoryVO.setBookAuthor(book.getBookAuthor());
						transactionHistoryVO.setBookPrice(book.getBookPrice());
						transactionHistoryVO.setBookTitle(book.getBookTitle());
						transactionHistoryVO.setDate(formatDate(orderItem.getCreatedDate()));
						transactionHistoryVO.setDueDate(orderItem.getExpectedReturnDate());
						transactionHistoryVO.setLateFee(book.getLateFee());
						transactionHistoryVO.setPublisherName(book.getPublisherName());
						transactionHistoryVO.setType(orderItem.getPurchaseType());
						transactionList.add(transactionHistoryVO);
					}
				}
			}

		}
		transaction.commit();
		session.close();
		/*Collections.sort(transactionList, new Comparator<TransactionHistoryVO>() {
		    public int compare(TransactionHistoryVO t1, TransactionHistoryVO t2) {
		        return t2.getDate().compareTo(t1.getDate());
		    }
		});*/
		return transactionList;
	}

	private String formatDate(Date date) {
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		String rdate = DATE_FORMAT.format(date);
		return rdate;
	}

}