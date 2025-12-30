package com.hotel.common.model;

import java.util.Date;

import com.hotel.common.util.FosysConstants.UserType;

import lombok.Data;

@Data
public class UserEntity {

	private String id;

	private UserType userType;

	private String mobileNumber;

	private String roles;

	private String username;

	private String password;

	private String email;

	private Date createdDate;

	private String createdBy;

	private Date modifiedDate;

	private String modifiedBy;
}
