package com.sapestore.service;

import java.util.List;

import com.sapestore.vo.BookVO;
import com.sapestore.vo.CurrentShippingAddress;

import com.sapestore.vo.ShoppingCartVO;

public interface EmailConfirmationService {
	
	
	public void sendOrderConfirmationMail(final CurrentShippingAddress address,final ShoppingCartVO orders,final List<BookVO> books,String userId); 

	public String retrieveMailId(String userId);
	
	
}
