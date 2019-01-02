package com.sapestore.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sapestore.dao.ShippingAddressDao;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.State;
import com.sapestore.service.ShippingAddressService;
import com.sapestore.vo.MemberAddress;
import com.sapestore.vo.ShippingAddressVO;
@Service("shippingService")
@Transactional
public class ShippingAddressServiceImpl implements ShippingAddressService {

	@Autowired
	ShippingAddressDao shippingaddressdao;
	
	
	public ShippingAddressDao getShippingaddressdao() {
		return shippingaddressdao;
	}

	public void setShippingaddressdao(ShippingAddressDao shippingaddressdao) {
		this.shippingaddressdao = shippingaddressdao;
	}

	@Override
	public MemberAddress retriveShippingAddress(String userid) {
		// TODO Auto-generated method stub
		MemberAddress memberaddress = shippingaddressdao.retriveShippingAddress(userid);
		return memberaddress;
	}

	@Override
	public void addShippingAddress(ShippingAddressVO shippingaddress) {
		shippingaddressdao.addShippingAddress(shippingaddress);
		
	}

	@Override
	public ArrayList<City> getAllCityId() {
		// TODO Auto-generated method stub
		ArrayList<City>  cities = shippingaddressdao.getAllCityId();
	return cities;
	
	}

	@Override
	public ArrayList<State>  getAllStateId() {
		// TODO Auto-generated method stub
		ArrayList<State> states = shippingaddressdao.getAllStateId();
		return states;
		
	}

	@Override
	public String getCityName(Integer cityId) {
		// TODO Auto-generated method stub
		String cityName = shippingaddressdao.getCityName(cityId);
		return cityName;
		
	}

	@Override
	public String getStateName(Integer stateId) {
		// TODO Auto-generated method stub
		String stateName = shippingaddressdao.getStateName(stateId);
		return stateName;
		
	}

	

//	@Override
//	public List<Integer> getOrderId(String userId) {
//		
//		// TODO Auto-generated method stub
//		List<Integer> orderIdList = shippingaddressdao.getOrderId(userId);
//		return orderIdList;
//	}

	@Override
	public String getUserName(String userId) {
		// TODO Auto-generated method stub
		String userName = shippingaddressdao.getUserName(userId);
		return userName;
	}

	/*@Override
	public void addOrder(String userId, int orderId) {
		shippingaddressdao.addOrder(userId,orderId);
		
	}*/
/*
	@Override
	public int addOrder(String userId) {
		int oid=shippingaddressdao.addOrder(userId);
		return oid;
		
	}
*/
	

}
