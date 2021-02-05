package com.hotel.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hotel.model.FoodItems;
import com.hotel.repository.FoodItemsRepo;

@Repository("foodItemsDao")
public class FoodItemsDao {
	
	@Autowired
	private FoodItemsRepo foodItemsrepo;
	
	public List<FoodItems> getVeg(String veg) {
		// TODO Auto-generated method stub
		return this.foodItemsrepo.findByType(veg);
	}

	public List<FoodItems> getNonVeg(String nonveg) {
		// TODO Auto-generated method stub
		return this.foodItemsrepo.findByType(nonveg);
	}

	public FoodItems getData(int id) {
		// TODO Auto-generated method stub
		return this.foodItemsrepo.findByFid(id);
	}

}
