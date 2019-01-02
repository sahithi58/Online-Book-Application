package com.sapestore.service;

import com.sapestore.vo.RegisterVO;

public interface UpdateEmailConfirmation {
	
public String retrieveMailId(String userId);
	
	public void sendAccountUpdateEmail(final RegisterVO register, String userId);
	

}
