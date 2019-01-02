package com.sapestore.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.OrderInfoDao;
import com.sapestore.dao.OrderItemInfoDao;
import com.sapestore.service.OrderInfoService;
import com.sapestore.service.OrderItemInfoService;
import com.sapestore.vo.OrderItemInfoVO;
@Service("orderInfoItemInfoService")
@Transactional

public class OrderItemInfoServiceImpl implements OrderItemInfoService {
@Autowired
OrderItemInfoDao orderItemInfoDao;



public OrderItemInfoDao getOrderItemInfoDao() {
	return orderItemInfoDao;
}



public void setOrderItemInfoDao(OrderItemInfoDao orderItemInfoDao) {
	this.orderItemInfoDao = orderItemInfoDao;
}



@Override
public BigDecimal addOrderItemInfo(OrderItemInfoVO orderItemInfoVO) {
	
	BigDecimal orderItemId=orderItemInfoDao.addOrderItemInfo(orderItemInfoVO);
	return orderItemId;
}
	

}
