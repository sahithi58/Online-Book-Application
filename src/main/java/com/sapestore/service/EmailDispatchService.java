package com.sapestore.service;

import java.math.BigDecimal;
import java.util.List;

public interface EmailDispatchService {
	public List<Long> getAllOrderId(List<BigDecimal> orderItemId);
	public List<String> getAllUserId(List<Long> orderId);
	public List<String> getAllEmailId(List<String> userId);
	public void sendRentMail(List<BigDecimal> orderItemNumber);
}
