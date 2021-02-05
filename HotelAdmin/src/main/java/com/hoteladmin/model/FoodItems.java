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
	private String itemname;
	private String price;
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
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	public FoodItems(int fid, String itemname, String price, String type) {
		super();
		this.fid = fid;
		this.itemname = itemname;
		this.price = price;
		this.type = type;
	}
	public FoodItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FoodItems [id=" + fid + ", itemname=" + itemname + ", price=" + price + ", type=" + type + "]";
	}
	
	
}
