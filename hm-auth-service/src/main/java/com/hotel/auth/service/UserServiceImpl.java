package com.hotel.auth.service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.auth.dao.UserRepository;
import com.hotel.auth.model.User;
import com.hotel.auth.model.UserRequest;
import com.hotel.auth.model.UserWithToken;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Optional<User> findUser(String mobileNumber) {
		return userRepository.findUser(mobileNumber);
	}

	@Override
	public void saveTokenWithUserId(User user, String token) {
		userRepository.saveTokenWithUserId(user, token);
	}

	@Override
	public UserWithToken validateToken(String token) {
		return userRepository.getUserByToken(token);
	}

	@Override
	public User save(UserRequest userRequest) {
		User newUser = new User();
		newUser.setMobileNumber(userRequest.getMobileNumber());
		newUser.setUsername(userRequest.getMobileNumber());
		newUser.setCreatedBy(userRequest.getMobileNumber());
		newUser.setCreatedDate(Date.from(ZonedDateTime.now().toInstant()));
		newUser.setRoles("ENTERPRISE_ROLE");
		newUser.setPassword(userRequest.getPassword());
		newUser.setUserType(userRequest.getUserType());
		newUser.setCreatedBy(userRequest.getMobileNumber());
		newUser.setCreatedDate(Date.from(Instant.now()));
		return userRepository.save(newUser);
	}
}
