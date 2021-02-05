package com.hoteladmin.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hoteladmin.model.FoodItems;


public interface FoodItemsRepo extends JpaRepository<FoodItems, Integer> {

	
}
