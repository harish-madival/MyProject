package com.hoteladmin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoteladmin.dao.FoodItemsDao;
import com.hoteladmin.model.FoodItems;

@Service
public class UserService {
	@Autowired
	private FoodItemsDao userDao;

	public void saveFood(FoodItems food) {
		this.userDao.saveFood(food);		
	}

	
}
