package com.hotel.auth.service;

import java.util.Optional;

import com.hotel.auth.model.User;
import com.hotel.auth.model.UserRequest;
import com.hotel.auth.model.UserWithToken;

public interface UserService {

	Optional<User> findUser(String mobileNumber);

	void saveTokenWithUserId(User user, String token);

	UserWithToken validateToken(String token);

	Object save(UserRequest userRequest);

}
