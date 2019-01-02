package com.sapestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.OrderStatusDao;
import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.service.OrderStatusService;
import com.sapestore.vo.OrderStatusVO;

@Service
@Transactional
public class OrderStatusServiceImpl implements OrderStatusService {

	/*
	 * @Autowired OrderStatusDao orderstatusdao ;
	 */
	@Autowired
	OrderStatusDao orderstatusdao;

	@Override
	public String returnOrderStatus(int orderId,String userId) throws SapeStoreException {
		System.out.println("order id IS COMING TILL SERVICE IMPL LAYER!!!!!!!!!!!!!!!!!!!!:" + orderId);

		Integer orderid = orderId;

		/* return orderstatusdao.getOrderStatus(orderid); */

		return orderstatusdao.getOrderStatus(orderId,userId);
		/*
		 * System.out.println(
		 * "startign dao stuff here to check if  hitting database"); Session
		 * session = factory.openSession(); Transaction transaction =
		 * session.beginTransaction(); OrderVO ordervo = new OrderVO(); //
		 * String returnStatus = null; System.out.println("in dao"); //
		 * System.out.println("ORDER ID IS COMINH ITLL TYHE DAO "+ orderId);
		 * Query query = session.createQuery(
		 * "select orderStatus from OrderInfo o where o.orderId =:oid ");
		 * query.setInteger("oid", orderId);
		 * 
		 * String status = (String) query.uniqueResult();
		 * 
		 * String status = orderstatusdao.getOrderStatus(orderId);
		 * transaction.commit(); session.close();
		 */

	}

	@Override
	public List<OrderStatusVO> getMyRentedOrder(Long orderId) throws SapeStoreException {
		// TODO Auto-generated method stub
		List<OrderStatusVO> myrenterorders= orderstatusdao.getMyRentedOrder(orderId);
		return myrenterorders;
	}

}
