package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{

	List<Cart> findByUserid(int uid);
	
}
