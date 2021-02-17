package com.hotel.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cart {
	@Id
	private int id;
	private int userId;
	private String itemName;
	private int quantity;
	private int price;
	private long totalPrice;
	
	@ManyToOne
	private User user;
	
	
	public User getUser() {
		return user;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", userid=" + userId + ", itemname=" + itemName + ", quantity=" + quantity
				+ ", price=" + price + ", totalprice=" + totalPrice + ", user=" + user + "]";
	}
	
	
}
