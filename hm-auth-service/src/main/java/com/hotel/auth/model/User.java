package com.hotel.auth.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	@Id
	private String id;

	private UserType userType;

	@Indexed(unique = true)
	private String mobileNumber;

	private Roles roles;

	@Indexed(unique = true)
	private String username;

	private String password;

	@Indexed(unique = true)
	private String email;

	private Date createdDate;

	private String createdBy;

	private Date modifiedDate;

	private String modifiedBy;
}
