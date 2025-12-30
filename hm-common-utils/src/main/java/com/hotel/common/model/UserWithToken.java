package com.hotel.common.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserWithToken {

	private UserEntity user;

	private String userToken;

	private LocalDateTime expiryTime;
}
