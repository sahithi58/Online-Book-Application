package com.sapestore.vo;

import java.util.List;

/**
 * Bean class for updating rent informatio.. 
 *
 * CHANGE LOG
 *      VERSION    DATE          AUTHOR       MESSAGE               
 *        1.0    20-06-2014     SAPIENT      Initial version
 */

public class RentedUpdateForm {
	
	List<RentedUpdate> rentedUpdateList;

	public List<RentedUpdate> getRentedUpdateList() {
		return rentedUpdateList;
	}

	public void setRentedUpdateList(List<RentedUpdate> rentedUpdateList) {
		this.rentedUpdateList = rentedUpdateList;
	}
	
}
