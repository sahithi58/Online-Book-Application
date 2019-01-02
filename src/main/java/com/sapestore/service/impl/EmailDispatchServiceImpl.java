package com.sapestore.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.EmailDispatchServiceDao;
import com.sapestore.service.EmailDispatchService;
@Service("emailDispatchService")
@Transactional
public class EmailDispatchServiceImpl implements EmailDispatchService {
	
	@Autowired
	EmailDispatchServiceDao emailDispatchDao;
	
	@Autowired
	private MailSender mailSender;  
	
	   
	public void setMailSender(MailSender mailSender) {  
	        this.mailSender = mailSender;  
	    }  
	
	
	public EmailDispatchServiceDao getEmailDispatchDao() {
		return emailDispatchDao;
	}

	public void setEmailDispatchDao(EmailDispatchServiceDao emailDispatchDao) {
		this.emailDispatchDao = emailDispatchDao;
	}

	@Override
	public List<Long> getAllOrderId(List<BigDecimal> orderItemId) {
		// TODO Auto-generated method stub
		List<Long> orderId = emailDispatchDao.getAllOrderId(orderItemId);
		return orderId;
	}

	@Override
	public List<String> getAllUserId(List<Long> orderId) {
		// TODO Auto-generated method stub
		List<String> userName = emailDispatchDao.getAllUserId(orderId);
		return userName;
	}

	
	@Override
	public void sendRentMail(List<BigDecimal> orderItemNumber) {
		// TODO Auto-generated method stub
		List<Long> orderId=getAllOrderId(orderItemNumber);
		
		System.out.println("orderId in service"+orderId);
		
		List<String> userId=getAllUserId(orderId);
		
		System.out.println("userId in service"+userId);
		
		List<String> emailId=getAllEmailId(userId);
		
		System.out.println("emailId in service"+emailId);
		
		for(String email:emailId){
		 SimpleMailMessage message = new SimpleMailMessage();  
	        message.setFrom("deshratan82@gmail.com");  
	        message.setTo(email);  
	        message.setSubject("ORDER DISPATCH ACKNOWLEDGMENT");  
	        message.setText("Your order has been returned");  
	        //sending message  
	       try{
	        mailSender.send(message);
	        System.out.println("mail send successful");
	       }
	       catch(Exception e){
	    	   e.printStackTrace();
	    	   System.out.println("mail error");
	       }
	       }
		
	}

	@Override
	public List<String> getAllEmailId(List<String> userId) {
		// TODO Auto-generated method stub
		List<String> emailId=emailDispatchDao.getAllEmailId(userId);
		return emailId;
	}

}
