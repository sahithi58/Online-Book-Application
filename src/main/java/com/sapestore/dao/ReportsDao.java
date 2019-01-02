package com.sapestore.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.exception.SapeStoreSystemException;
import com.sapestore.hibernate.entity.Book;
import com.sapestore.hibernate.entity.OrderInfo;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.AdminDefaulterReport;
import com.sapestore.vo.ReportVO;
import com.sun.mail.iap.ConnectionException;

/**
 * DAO class for admin report.
 * 
 * CHANGE 	LOG 
 * VERSION 	DATE 			AUTHOR MESSAGE 
 * 1.0 		20-06-2014 		SAPIENT Initial version
 */

@Repository
@Transactional
public class ReportsDao {
	
    /**
     * Logger for log messages.
     */
    private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(ReportsDao.class.getName());
    
	@Autowired
	private HibernateTemplate hibernateTemplate;

    /**
     * Method to fetch admin report from the database.
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
    @SuppressWarnings("unchecked")
	public List<ReportVO> getAdminReport() throws SapeStoreException{
        LOGGER.debug("getBookDetails method: START");    
        
        try {        	
        	List<Book> bookList = (List<Book>) hibernateTemplate.findByNamedQuery("Book.findAllInventory");	
            if (!bookList.isEmpty()) {
            	System.out.println("In reports dao"+bookList);
                return setCategoryDetailBean(bookList);
            } else {
                LOGGER.debug(" There is no book available.");
                return null;
            }
        } catch (SapeStoreSystemException dbe) {
            LOGGER.fatal("A DB exception occured while getting the user profile", dbe);
            throw dbe;
        }
    }

    /**
     * Method to map the values from the map to bean.
     * @param list
     * @return
     */
	private List<ReportVO> setCategoryDetailBean(List<Book> bookList) {
       LOGGER.debug(" ProductDao.setCategoryDetailBean method: START ");
		List<ReportVO> finalList = new ArrayList<ReportVO>();
		ReportVO transDO = null;
		for (int i = 0; i < bookList.size(); i++) {				
				transDO = new ReportVO();
				transDO.setCategoryName(bookList.get(i).getCategoryName());	
				transDO.setBookTitle(bookList.get(i).getBookTitle());				
				transDO.setBookAuthor(bookList.get(i).getBookAuthor());
				transDO.setPublisherName(bookList.get(i).getPublisherName());				
				transDO.setBookPrice(bookList.get(i).getBookPrice().intValue());				
				transDO.setQuantity(bookList.get(i).getQuantity());
				transDO.setCategoryName(bookList.get(i).getCategoryName());
				finalList.add(transDO);
			}
        LOGGER.debug(" ProductDao.setCategoryDetailBean method: END ");
		return finalList;
	}

	/**
     * Method to fetch admin report from the database for Purchased/Rented orders.
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
    @SuppressWarnings("unchecked")
	public List<ReportVO> getPurchasedRentedAdminReport() throws SapeStoreException {
        LOGGER.debug(" ProductDao.getPurchasedRentedAdminReport method: START ");
        
        List<ReportVO> finalList = null;
        ReportVO report = null;
        Book book = null;
        
        try{        	
    		String sqlQuery = "select sum(order_item_info.QUANTITY) as QUANTITY, purchase_type,isbn from order_item_info group by purchase_type, isbn";        	  		
    		List<Map<String,Object>> aliasToValueMapList = hibernateTemplate.getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery).
    				setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
    		
    		if(!aliasToValueMapList.isEmpty()){
             	finalList = new ArrayList<ReportVO>();     
             	for(int i = 0; i < aliasToValueMapList.size(); i++) {
             		report = new ReportVO();
                	Map<String, Object> map = aliasToValueMapList.get(i);               	
                	
                	report.setIsbn((String) map.get("ISBN"));
                	
                	BigDecimal quantity = (BigDecimal) map.get("QUANTITY");
                	//BigDecimal bookPrice = (BigDecimal) map.get("BOOK_PRICE");
                	//System.out.println("BookPrice: "+bookPrice);
                	System.out.println((BigDecimal) map.get("QUANTITY"));
                	report.setQuantity(quantity.intValue());
                	report.setOrderType((String) map.get("PURCHASE_TYPE"));
                	book = hibernateTemplate.get(Book.class, (String) map.get("ISBN"));
                	report.setCategoryName(book.getCategoryName());
                	report.setBookTitle(book.getBookTitle());
                	report.setBookAuthor(book.getBookAuthor());
                	report.setPublisherName(book.getPublisherName());
                	System.out.println(report.getOrderType());
                	if(report.getOrderType().equalsIgnoreCase("PURCHASED"))
                	{
                		int price = book.getBookPrice().intValue();
                		int quantity1 = report.getQuantity();
                		int totalPrice = price*quantity1; 
                		report.setBookPrice(totalPrice);
                		report.setRentPrice(0);
                	}
                	else if(report.getOrderType().equalsIgnoreCase("RENTED"))
                	{
                		int price = book.getRentPrice().intValue();
                		int quantity1 = report.getQuantity();
                		int totalPrice = price*quantity1; 
                		report.setRentPrice(totalPrice);
                		report.setBookPrice(0);
                	}
                	
            		/*report.setBookPrice(book.getBookPrice().intValue());
            		report.setRentPrice(book.getRentPrice().intValue());*/
             		finalList.add(report);        		          		
             	}
    		}        		                
        }
        catch (SapeStoreSystemException se) {
            LOGGER.fatal("A DB exception occured while getting the user profile", se);
        }
        LOGGER.debug(" ProductDao.getPurchasedRentedAdminReport method: END ");
		return finalList;
    }
    
    /**
     * Method to fetch admin report from the database for Defaultered orders.
     * @return
     * @throws ConnectionException
     * @throws TransactionExecutionException
     */
    @SuppressWarnings("unchecked")
    public List<AdminDefaulterReport> getDefaultersAdminReport() throws SapeStoreException{
        LOGGER.debug("getDefaultersAdminReport method: START");
        
        ArrayList<AdminDefaulterReport> finalList = null;
        AdminDefaulterReport report = null;
        
        try{
                  List<OrderItemInfo> orderItemInfoList = (List<OrderItemInfo>) hibernateTemplate.findByNamedQuery("OrderItemInfo.findDefaulters");        
               //System.out.println(orderItemInfoList);
                  if(!orderItemInfoList.isEmpty()){
                  finalList = new ArrayList<AdminDefaulterReport>();    
                  for(OrderItemInfo orderItemInfo: orderItemInfoList) {
                         report = new AdminDefaulterReport();                                 
                  report.setOrderID(orderItemInfo.getOrderId());                
                  report.setExpectedReturnDate(orderItemInfo.getExpectedReturnDate());
                  
                  report.setActualReturnDate(orderItemInfo.getActualReturnDate());
                  if(orderItemInfo.getReturnStatus().trim().equalsIgnoreCase("false")){
                         report.setReturnStatus("Not Returned");
                  }
                  else{
                         report.setReturnStatus("Returned");
                  }
                  report.setCategoryName(orderItemInfo.getCategoryName());             
                  report.setBookTitle(orderItemInfo.getBookTitle());
                  report.setRentPrice(orderItemInfo.getRentPrice().doubleValue());
                  
                  //report.setLateFee(orderItemInfo.getLateFee().doubleValue());
                  Date date=new Date();
                  OrderInfo orderInfo = hibernateTemplate.get(OrderInfo.class,orderItemInfo.getOrderId());
                  if(orderItemInfo.getActualReturnDate()!=null && orderItemInfo.getExpectedReturnDate()!=null){
                         final DateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
                         Date expected = fmt.parse(orderItemInfo.getExpectedReturnDate());
                         Date actual=fmt.parse(orderItemInfo.getActualReturnDate());
                         long diffInMilis=actual.getTime()-expected.getTime();
                         long diff=diffInMilis/(24*60*60*1000);
                         int f=(int) (diff*orderItemInfo.getLateFee().doubleValue());
                         report.setLateFee(f);
                  }
                  report.setCustomerName(orderInfo.getName());
                 report.setEmail(orderInfo.getEmailAddress());
                         finalList.add(report);                                        
                  }
                  }
        }
        catch (SapeStoreSystemException se) {
               LOGGER.fatal("A DB exception occured while getting the user profile", se);
           }
        catch (ParseException e) {
                  LOGGER.fatal("A parsing exception occured", e);
           }
           
           return finalList;
 }


    public Date getDateWithOutTime(Date targetDate) { 	
        Calendar newDate = Calendar.getInstance();
        newDate.setLenient(false);
        newDate.setTime(targetDate);
        newDate.set(Calendar.HOUR_OF_DAY, 0);
        newDate.set(Calendar.MINUTE,0);
        newDate.set(Calendar.SECOND,0);
        newDate.set(Calendar.MILLISECOND,0);
        return newDate.getTime();
    }
    
}
