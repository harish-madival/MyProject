package com.hotel.auth.model;

import com.hotel.common.util.FosysConstants.UserType;

import lombok.Data;

@Data
public class UserRequest {

	private UserType userType;

	private String mobileNumber;

	private String roles;

	private String username;

	private String password;

	private String email;
}
