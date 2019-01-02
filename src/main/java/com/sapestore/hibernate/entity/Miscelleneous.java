package com.sapestore.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="MISCELLANEOUS")
@NamedQueries(value = {
		@NamedQuery(name = "Miscelleneous.findAll", query = "from Miscelleneous m")
		})
public class Miscelleneous implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2228400959309372457L;

	@Id
	@Column(name="CONTACTUS")
	private String contactUs;	
	
	@Column(name="POLICY")
	private String policy;

	/**
	 * @return the contactUs
	 */
	public String getContactUs() {
		return contactUs;
	}

	/**
	 * @param contactUs the contactUs to set
	 */
	public void setContactUs(String contactUs) {
		this.contactUs = contactUs;
	}

	/**
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(String policy) {
		this.policy = policy;
	}		

}
