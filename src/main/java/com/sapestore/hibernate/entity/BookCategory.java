package com.sapestore.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="SAPESTORE_BOOKS_CATEGORIES")
@NamedQueries(value = {
		@NamedQuery(name = "BookCategory.findAll", query = "from BookCategory b")
 		})
public class BookCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292756594043092405L;

	@Id
	@Column(name="CATEGORY_ID")
	private Integer categoryId;
		
	@Column(name="CATEGORY_NAME")
	private String categoryName;

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

			
		
}
