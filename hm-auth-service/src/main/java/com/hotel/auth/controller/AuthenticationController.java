package com.hotel.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.auth.service.UserService;
import com.hotel.common.model.UserWithToken;

@RestController
public class AuthenticationController {

	@Autowired
	UserService userService;

	@PostMapping("/validate-token")
	public UserWithToken validateToken(@RequestHeader("Authorization") String token) {
		UserWithToken validateToken = userService.validateToken(token);
		return validateToken;
	}

}
