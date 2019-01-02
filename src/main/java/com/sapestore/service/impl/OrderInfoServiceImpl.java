package com.sapestore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.OrderInfoDao;
import com.sapestore.service.OrderInfoService;
import com.sapestore.vo.OrderInfoVO;
@Service("orderInfoService")
@Transactional
public class OrderInfoServiceImpl implements OrderInfoService {
@Autowired
OrderInfoDao orderInfoDao;




public OrderInfoDao getOrderInfoDao() {
	return orderInfoDao;
}




public void setOrderInfoDao(OrderInfoDao orderInfoDao) {
	this.orderInfoDao = orderInfoDao;
}






@Override
public Long addOrder(OrderInfoVO orderInfoVo) {
	
	Long oid=orderInfoDao.addOrder(orderInfoVo);
	return oid;
}



}
