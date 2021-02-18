package com.hoteladmin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.hoteladmin.model.FoodItems;
import com.hoteladmin.repository.FoodItemsRepo;


@Repository("userDao")
public class FoodItemsDao {
	@Autowired
	private FoodItemsRepo foodItemsrepo;
	
	

	public void saveFood(FoodItems food) {
		this.foodItemsrepo.save(food);
		
	}

	

}
