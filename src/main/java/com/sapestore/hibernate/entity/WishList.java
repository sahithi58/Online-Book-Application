package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="SAPESTORE_USER_WISHLIST")
@NamedQueries(value = {
		@NamedQuery(name = "WishList.findByWishId", query = "from WishList w where w.wishId = :wishId")
 		})
public class WishList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1347130875419134578L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="WISH_ID")
	private Integer wishId;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="CREATED_DATE")
	private Date createdDate=new Date();

	@Column(name="UPDATED_DATE")
	private Date updatedDate=new Date();

	@Column(name="IS_ACTIVE")
	private String isActive;

	/**
	 * @return the wishId
	 */
	public Integer getWishId() {
		return wishId;
	}

	/**
	 * @param wishId the wishId to set
	 */
	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}

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
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	
	
}
