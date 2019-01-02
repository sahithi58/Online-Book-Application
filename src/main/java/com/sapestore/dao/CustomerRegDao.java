package com.sapestore.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.sapestore.common.SapeStoreLogger;
import com.sapestore.daointerface.CustomerRegDaoI;
import com.sapestore.hibernate.entity.Address;
import com.sapestore.hibernate.entity.City;
import com.sapestore.hibernate.entity.Country;
import com.sapestore.hibernate.entity.Gender;
import com.sapestore.hibernate.entity.State;
import com.sapestore.hibernate.entity.User;

@Repository
public class CustomerRegDao implements CustomerRegDaoI {
	private final static SapeStoreLogger LOGGER = SapeStoreLogger.getLogger(CustomerRegDao.class.getName());
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Autowired
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public void addCustomer(User user) {

		
		LOGGER.debug("ADDING CUSTOMER TO DB:START");
		/*
		 * User user =new User(); user.setName(cust_vo.getName());
		 * user.setUserId(cust_vo.getUserId());
		 * user.setPassword(cust_vo.getPassword());
		 * user.setEmailAddress(cust_vo.getEmailAddress());
		 * user.setPhone(Integer.parseInt(cust_vo.getPhone()));
		 * user.setMobileNumber(Integer.parseInt(cust_vo.getMobileNumber()));
		 * user.setAddressId(cust_vo.getAddressId());
		 * user.setGenderId(cust_vo.getGenderId());
		 */
		
		
		
		
	/*	Address address=new Address();
		Date date = new Date(); 

				  address.setUserId(register.getLoginName());
		           address.setAddressLine1(register.getAddress1());
		           address.setAddressLine2(register.getAddress2());
		           address.setCityId(register.getCityId());
		           address.setStateId(register.getStateId());
		           address.setPostalCode(register.getZip());
		           address.setCreatedDate(date);
		           address.setUpdatedDate(date);
		           address.setIsActive("Y");
				hibernateTemplate.save(address);

				String query = "from Address where userId like :userid";
				ArrayList address1 = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid",
						'%' + address.getUserId() + '%');
				Address address2 = (Address) address1.get(0);
				
				User user=new User();
		 user.setAddressId(address2.getAddressId());
		 
          user.setUserId(register.getLoginName());
          user.setEmailAddress(register.getEmail());
          user.setGenderId(register.getGenderId());
          user.setMobileNumber(register.getMobileNumber());
          user.setName(register.getFullName());
          user.setPassword(register.getPassword());*/
          
		hibernateTemplate.save(user);

		LOGGER.debug("USER IS ADDED INTO DB");
	//	return user;

	}

	@Override
	public ArrayList<City> getAllCities(int sId) {

	
		Session session = factory.openSession();
	Transaction t = session.beginTransaction();
	
		String queryString = " from City c where c.stateId= :stateId";
		Query q = session.createQuery(queryString);
		q.setInteger("stateId", sId);
		System.out.println(q.list());
		ArrayList<City> temp1=(ArrayList<City>) q.list();
		session.close();
		return temp1;
		
		/*	LOGGER.debug("retieving cities from db to and displaying in jsp");
		System.out.println(sId);
		String query = "from City c where c.stateId= :stateId";
		ArrayList cities=  (ArrayList) (hibernateTemplate.findByNamedParam(query, "stateId",'%' + sId + '%'));
		System.out.println();
		
		
		return cities;*/

	}
	
	@Override
	public ArrayList<State> getAllStates() {
		LOGGER.debug("retieving states from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		ArrayList<State> states = (ArrayList<State>) hibernateTemplate.find("from State");
		return states;
	}

	@Override
	public ArrayList<Country> getAllCountries() {
		LOGGER.debug("retieving countries from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		ArrayList<Country> countries = (ArrayList<Country>) hibernateTemplate.find("from Country");
		return countries;
	}

	@Override
	public boolean checkUserName(String loginName) {
		boolean flag = false;
		LOGGER.debug("CHECKING THE LOGINNAME");
		
		String query = "from User where userId like :userid";
		ArrayList userIdentity = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid",
				'%' + loginName + '%');
		if(userIdentity.isEmpty())
		{
			LOGGER.debug("username is doesnt exist");
			return true;
		}
		else
			LOGGER.debug("username already exixts");
			return flag;

	}

	@Override
	public Integer addAddress(Address address) {
/*Address address=new Address();
Date date = new Date(); 

		  address.setUserId(register.getLoginName());
           address.setAddressLine1(register.getAddress1());
           address.setAddressLine2(register.getAddress2());
           address.setCityId(register.getCityId());
           address.setStateId(register.getStateId());
           address.setPostalCode(register.getZip());
           address.setCreatedDate(date);
           address.setUpdatedDate(date);
           address.setIsActive("Y");*/
		hibernateTemplate.save(address);

		String query = "from Address where userId like :userid";
		ArrayList address1 = (ArrayList) hibernateTemplate.findByNamedParam(query, "userid",
				'%' + address.getUserId() + '%');
		Address address2 = (Address) address1.get(0);
		return address2.getAddressId();

	}

	
	@Override
	public ArrayList<Gender> getAllGenders() {
		// TODO Auto-generated method stub
		LOGGER.debug("retieving genders from db to and displaying in jsp");
		@SuppressWarnings("unchecked")
		ArrayList<Gender> gender = (ArrayList<Gender>) hibernateTemplate.find("from Gender");
		return gender;
	}

}
