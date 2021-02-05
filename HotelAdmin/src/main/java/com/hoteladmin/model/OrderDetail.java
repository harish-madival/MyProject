package com.hoteladmin.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OrderDetail {
	@Id		
	private long orderid;
	private int cartid;
	private String cartItemName;
	private String cartPrice;
	private int userid;
	
	
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
		return "OrderDetail [orderid=" + orderid + ", cartid=" + cartid + ", cartItemName=" + cartItemName
				+ ", cartPrice=" + cartPrice + ", userid=" + userid + "]";
	}	
	
	
}
