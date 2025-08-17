package com.hm.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hm.food.model.FoodItems;
import com.hm.food.service.FoodService;
import com.hm.food.utils.HmConstants;

@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@PostMapping("/add")
	public ResponseEntity<?> addFood(@RequestBody FoodItems foodItems,
			@RequestHeader(name = HmConstants.USERID) String userId,
			@RequestHeader(name = HmConstants.USER_TYPE) String userType) {
		return new ResponseEntity<>(foodService.addFood(foodItems, userId, userType), HttpStatus.CREATED);
	}

	@GetMapping("/add")
	public ResponseEntity<?> getFood(@RequestHeader(name = HmConstants.USERID) String userId,
			@RequestHeader(name = HmConstants.USER_TYPE) String userType,
			@RequestParam(name = HmConstants.FILTER_KEY) String filterKey,
			@RequestParam(name = HmConstants.FILTER_VALUE) String filterValue,
			@RequestParam(name = HmConstants.SORTBY) String sortBy,
			@RequestParam(name = HmConstants.SORTORDER) String sortOrder,
			@RequestParam(name = HmConstants.SKIP) int skip, @RequestParam(name = HmConstants.LIMIT) int limit) {
		return new ResponseEntity<>(
				foodService.getFood(userId, userType, filterKey, filterValue, sortBy, sortOrder, skip, limit),
				HttpStatus.OK);

	}

	@DeleteMapping("/add")
	public ResponseEntity<?> deleteFood(@RequestHeader(name = HmConstants.USERID) String userId,
			@RequestHeader(name = HmConstants.USER_TYPE) String userType,
			@RequestParam(name = HmConstants.FOOD_ID) String foodId) {
		return new ResponseEntity<>(foodService.deleteFood(userId, userType, foodId), HttpStatus.OK);
	}

	@PutMapping("/add")
	public ResponseEntity<?> updateFood(@RequestBody FoodItems foodItems,
			@RequestHeader(name = HmConstants.USERID) String userId,
			@RequestHeader(name = HmConstants.USER_TYPE) String userType,
			@RequestParam(name = HmConstants.FOOD_ID) String foodId) {

		return new ResponseEntity<>(foodService.updateFood(foodItems, userId, userType, foodId), HttpStatus.OK);

	}

}
