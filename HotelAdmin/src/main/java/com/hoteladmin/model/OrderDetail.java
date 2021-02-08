package com.hoteladmin.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetail {
	@Id	
	private int id;
	
	
	private Date ordereddate;
	private long orderid;
	private int cartid;
	private String cartItemName;
	private String cartPrice;
	private int userid;
	
	public Date getOrdereddate() {
		return ordereddate;
	}
	public void setOrdereddate(Date ordereddate) {
		this.ordereddate = ordereddate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrderDetail() {		
	}
	public long getOrderid() {
		return orderid;
	}
	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public String getCartItemName() {
		return cartItemName;
	}
	public void setCartItemName(String cartItemName) {
		this.cartItemName = cartItemName;
	}
	public String getCartPrice() {
		return cartPrice;
	}
	public void setCartPrice(String cartPrice) {
		this.cartPrice = cartPrice;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", ordereddate=" + ordereddate + ", orderid=" + orderid + ", cartid=" + cartid
				+ ", cartItemName=" + cartItemName + ", cartPrice=" + cartPrice + ", userid=" + userid + "]";
	}	
	
	
}
