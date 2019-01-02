package com.sapestore.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="GENDER")
@NamedQueries(value = {
		@NamedQuery(name = "Gender.findByGenderId", query = "from Gender g where g.genderId = :genderId")
		})
public class Gender implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7470463233358511325L;

	@Id
	@Column(name="GENDER_ID")
	private Integer genderId;	
	
	@Column(name="GENDER_NAME")
	private String genderName;

	/**
	 * @return the genderId
	 */
	public Integer getGenderId() {
		return genderId;
	}

	/**
	 * @param genderId the genderId to set
	 */
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	/**
	 * @return the genderName
	 */
	public String getGenderName() {
		return genderName;
	}

	/**
	 * @param genderName the genderName to set
	 */
	public void setGenderName(String genderName) {
		this.genderName = genderName;
	}
	
	

	
}
