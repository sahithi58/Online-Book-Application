package com.sapestore.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;

import com.sapestore.vo.RegisterVO;

@Repository
public class CustomerEditProfileDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
@Autowired
SessionFactory factory;

	private final static SapeStoreLogger logger = SapeStoreLogger.getLogger(ReviewDao.class.getName());

	public RegisterVO getCustomerInfo(String userId) {

		logger.debug("Getting user details");
		RegisterVO registerVO = new RegisterVO();
		//String query = "from User where userId like :userid";
		@SuppressWarnings("unchecked")
		User user = hibernateTemplate.get(User.class, userId);
		// User user = userList.get(0);
		System.out.println(user);
		Integer addressId = user.getAddressId();
		//String query1 = "from Address where addressId like :addressId";
		@SuppressWarnings("unchecked")
		Address address =hibernateTemplate.get(Address.class, addressId);
		System.out.println(address);
	/*	Address address = addressList.get(0);*/
		Integer stateId = address.getStateId();
		Integer cityId = address.getCityId();
		//String query2 = "from City where cityId like :cityId";
		@SuppressWarnings("unchecked")
		City city =hibernateTemplate.get(City.class, cityId);
		System.out.println(city);
		/*City city = cityList.get(0);*/
		//String query3 = "from State where stateId like :stateId";
		@SuppressWarnings("unchecked")
		State state = hibernateTemplate.get(State.class, stateId);
		System.out.println(state);
		/*State state = stateList.get(0);*/
		registerVO.setAddress1(address.getAddressLine1());
		registerVO.setAddress2(address.getAddressLine2());
		registerVO.setFullName(user.getName());
		registerVO.setLoginName(user.getUserId());
		registerVO.setEmail(user.getEmailAddress());
		registerVO.setPhoneNumber(user.getPhone());
		registerVO.setPassword(user.getPassword());
		registerVO.setCityId(address.getCityId());
		registerVO.setStateId(address.getStateId());
		registerVO.setMobileNumber(user.getMobileNumber());
		registerVO.setZip(address.getPostalCode());
		return registerVO;

	}

	public void updateCustomer(User user) {
		logger.debug("Updating CUSTOMER TO DB:START");
		System.out.println("updating customer");
		System.out.println("..."+user);
		System.out.println("updating customer");
		hibernateTemplate.saveOrUpdate(user);
		System.out.println("new user"+user);
		logger.debug("USER IS UPDATED INTO DB");
	}

	public ArrayList<City> getAllCities(Integer stateId) {

		logger.debug("retieving cities from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		Session session =factory.openSession();
		Transaction transaction=session.beginTransaction();
		Query query= session.createQuery("from City where stateId like:stateId");
		query.setInteger("stateId", stateId);
		ArrayList<City> cities = (ArrayList<City>)query.list();
		System.out.println(cities);
		transaction.commit();
		session.close();
		return cities;

	}

	public ArrayList<State> getAllStates() {
		logger.debug("retieving states from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		ArrayList<State> states = (ArrayList<State>) hibernateTemplate.find("from State");
		return states;
	}

	public ArrayList<Country> getAllCountries() {
		logger.debug("retieving countries from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		ArrayList<Country> countries = (ArrayList<Country>) hibernateTemplate.find("from Country");
		return countries;
	}

	public boolean checkUserName(String loginName) {
		boolean flag = false;
		logger.debug("CHECKING THE LOGINNAME");

		String query = "from User where userId like :userid";
		ArrayList userIdentity = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid", '%' + loginName + '%');
		if (userIdentity.isEmpty()) {
			logger.debug("username doesnt exist");
			return true;
		} else
			logger.debug("username already exixts");
		return flag;

	}

	public Integer addAddress(Address address, String userId) {
		/*
		 * Address address=new Address(); Date date = new Date();
		 * 
		 * address.setUserId(register.getLoginName());
		 * address.setAddressLine1(register.getAddress1());
		 * address.setAddressLine2(register.getAddress2());
		 * address.setCityId(register.getCityId());
		 * address.setStateId(register.getStateId());
		 * address.setPostalCode(register.getZip());
		 * address.setCreatedDate(date); address.setUpdatedDate(date);
		 * address.setIsActive("Y");
		 */
		System.out.println(address);
		hibernateTemplate.update(address);

		String query = "from Address where userId like :userid";
		ArrayList address1 = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid",
				'%' + address.getUserId() + '%');
		Address address2 = (Address) address1.get(0);
		return address2.getAddressId();

	}

	public ArrayList<Gender> getAllGenders() {
		// TODO Auto-generated method stub
		logger.debug("retieving genders from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		ArrayList<Gender> gender = (ArrayList<Gender>) hibernateTemplate.find("from Gender");
		return gender;
	}
	public Integer getAddressId(String userId){
		Integer addressId=null;
		String query="from Address where userId like :userId";
		List<Address> addressList=(List<Address>) hibernateTemplate.findByNamedParam(query, "userId", userId);
		for(Address address:addressList ){
			addressId=address.getAddressId();
		}
		return addressId;
	}
	
}
