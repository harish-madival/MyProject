package com.hotel.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetail {
	@Id	
	private int id;
	
	private Date orderedDate;
	private long orderId;
	private int cartId;
	private String cartItemName;
	private long cartPrice;
	private int userId;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getOrderedDate() {
		return orderedDate;
	}


	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}


	public long getOrderId() {
		return orderId;
	}


	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public String getCartItemName() {
		return cartItemName;
	}


	public void setCartItemName(String cartItemName) {
		this.cartItemName = cartItemName;
	}


	public long getCartPrice() {
		return cartPrice;
	}


	public void setCartPrice(long cartPrice) {
		this.cartPrice = cartPrice;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", ordereddate=" + orderedDate + ", orderid=" + orderId + ", cartid=" + cartId
				+ ", cartItemName=" + cartItemName + ", cartPrice=" + cartPrice + ", userid=" + userId + "]";
	}	
	
	
}
