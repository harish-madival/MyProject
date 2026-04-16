package com.hotel.auth.service;

import java.util.Optional;

import com.hotel.common.model.User;
import com.hotel.common.model.UserRequest;
import com.hotel.common.model.UserWithToken;

public interface UserService {

	Optional<User> findUser(String mobileNumber);

	void saveTokenWithUserId(User user, String token);

	UserWithToken validateToken(String token);

	User save(UserRequest userRequest);
}
