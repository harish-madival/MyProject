package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hotel.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{
	
	@Query(value = "select * from Cart where user_userId=:n", nativeQuery = true)
	List<Cart> getCartedData(@Param("n") int uid);
	
}
