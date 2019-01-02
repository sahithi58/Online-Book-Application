package com.sapestore.hibernate.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="ORDER_INFO")
@NamedQueries(value = {@NamedQuery(name = "OrderInfo.findAll", query = "from OrderInfo o")
	    })
public class OrderInfo implements Serializable{
			
	/**
	 * 
	 */
	private static final long serialVersionUID = -2687916517368691592L;

	@Id
	@Column(name="ORDER_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long orderId;	
		
	@Column(name="USER_ID")
	private String userId;

	@Column(name="ORDER_DATE")
	private Date orderDate= new Date();	
	
	@Column(name="TOTAL_PAYMENT")
	private Integer totalPayment;
	
	@Column(name="PAYMENT_MODE")
	private String paymentMode;	
	
	@Column(name="CREATED_DATE")
	private Date createdDate = new Date();	
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate = new Date();	
		
	@Column(name="IS_ACTIVE")
	private String isActive;
	
	@Column(name="ORDER_STATUS")
	private String orderStatus="NOT_DISPATCHED";
	
	@OneToMany
	@JoinColumn(name = "ORDER_ID")
	private List<OrderItemInfo> orderItemInfoList;		

	/**
	 * @return the orderItemInfoList
	 */
	public List<OrderItemInfo> getOrderItemInfoList() {
		return orderItemInfoList;
	}

	/**
	 * @param orderItemInfoList the orderItemInfoList to set
	 */
	public void setOrderItemInfoList(List<OrderItemInfo> orderItemInfoList) {
		this.orderItemInfoList = orderItemInfoList;
	}
	
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID", insertable=false, updatable=false)
	private User user;	
		
	@Transient
	private String name;	
	
	@Transient
	private String emailAddress;	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return user.getName();
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return user.getEmailAddress();
	}

	/**
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the totalPayment
	 */
	public Integer getTotalPayment() {
		return totalPayment;
	}

	/**
	 * @param totalPayment the totalPayment to set
	 */
	public void setTotalPayment(Integer totalPayment) {
		this.totalPayment = totalPayment;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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

	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderInfo [orderId=" + orderId + ", userId=" + userId + ", orderDate=" + orderDate + ", totalPayment="
				+ totalPayment + ", paymentMode=" + paymentMode + ", createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + ", isActive=" + isActive + ", orderStatus=" + orderStatus + ", orderItemInfoList="
				+ orderItemInfoList + ", user=" + user + ", name=" + name + ", emailAddress=" + emailAddress + "]";
	}
	
}
