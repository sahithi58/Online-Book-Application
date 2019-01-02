package com.sapestore.service;



import java.util.List;

import com.sapestore.exception.SapeStoreException;
import com.sapestore.hibernate.entity.OrderItemInfo;
import com.sapestore.vo.OrderStatusVO;


public interface OrderStatusService {

String returnOrderStatus(int orderId,String userId) throws SapeStoreException;
public List<OrderStatusVO> getMyRentedOrder(Long orderId) throws SapeStoreException;
	
}

