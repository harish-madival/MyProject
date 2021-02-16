package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByEmailidAndUserpassword(String emailid, String userpassword);

	User findByUserId(int uid);	

	
}
