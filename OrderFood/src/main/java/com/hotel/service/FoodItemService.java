package com.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dao.FoodItemsDao;
import com.hotel.model.FoodItems;

@Service
public class FoodItemService {

	@Autowired
	private FoodItemsDao foodItemsDao;
	
	public List<FoodItems> getVeg(String veg) {
		// TODO Auto-generated method stub
		return this.foodItemsDao.getVeg(veg);
	}

	public List<FoodItems> getNonVeg(String nonveg) {
		// TODO Auto-generated method stub
		return this.foodItemsDao.getNonVeg(nonveg);
	}
	
	public FoodItems getdata(int id) {
		// TODO Auto-generated method stub
		return this.foodItemsDao.getData(id);
	}
}
