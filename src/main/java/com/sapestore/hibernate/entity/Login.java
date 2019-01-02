package com.sapestore.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="LOGIN")
@NamedQueries(value = {
		@NamedQuery(name = "Login.findByUserId", query = "from Login l where l.userId = :userId")
		})
public class Login implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 525940974719409200L;

	@Id
	@Column(name="USER_ID")
	private String userId;	
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="NAME")
	private String name;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
		
}
