package com.hotel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.FoodItems;
import com.hotel.model.User;


public interface FoodItemsRepo extends JpaRepository<FoodItems, Integer> {

	List<FoodItems> findByType(String veg);

	FoodItems findByFid(int id);

	

	

}
