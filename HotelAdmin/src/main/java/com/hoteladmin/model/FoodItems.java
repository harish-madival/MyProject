package com.hoteladmin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class FoodItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fid;
	private String itemName;
	private int price;
	private String type;	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return fid;
	}
	public void setId(int id) {
		this.fid = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public FoodItems(int fid, String itemName, int price, String type) {
		super();
		this.fid = fid;
		this.itemName = itemName;
		this.price = price;
		this.type = type;
	}
	public FoodItems() {
		super();
	}
	@Override
	public String toString() {
		return "FoodItems [id=" + fid + ", itemName=" + itemName + ", price=" + price + ", type=" + type + "]";
	}
	
	
}
