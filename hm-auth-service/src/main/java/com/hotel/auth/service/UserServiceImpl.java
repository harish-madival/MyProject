package com.hotel.auth.service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.auth.dao.UserRepository;
import com.hotel.common.model.User;
import com.hotel.common.model.UserRequest;
import com.hotel.common.model.UserWithToken;

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
		newUser.setCreatedBy(userRequest.getMobileNumber());
		newUser.setCreatedDate(Date.from(ZonedDateTime.now().toInstant()));
		if (userRequest.getUserType().name().equalsIgnoreCase("ADMIN")) {
			newUser.setRoles("ADMIN_ROLE");
		} else if (userRequest.getUserType().name().equalsIgnoreCase("PARTNER")) {
			newUser.setRoles("PARTNER_ROLE");
		} else {
			newUser.setRoles("ENTERPRISE_ROLE");
		}
		
		newUser.setPassword(userRequest.getPassword());
		newUser.setUserType(userRequest.getUserType());
		newUser.setCreatedBy(userRequest.getMobileNumber());
		newUser.setCreatedDate(Date.from(Instant.now()));
		newUser.setFirstName(userRequest.getFirstName());
		newUser.setLastName(userRequest.getLastName());
		newUser.setUserName(userRequest.getUserName());
		newUser.setPassword(userRequest.getPassword());
		if (!userRequest.getPassword().equalsIgnoreCase(userRequest.getConfirmPassword())) {
			throw new ValidationException("Password Not Matching");
		}
		newUser.setConfirmPassword(userRequest.getConfirmPassword());
		return userRepository.save(newUser);
	}
}
