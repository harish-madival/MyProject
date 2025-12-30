package com.hotel.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.auth.config.ConfigProperties;
import com.hotel.auth.model.HmResponse;
import com.hotel.auth.model.LogInDetails;
import com.hotel.auth.model.User;
import com.hotel.auth.model.UserRequest;
import com.hotel.auth.security.JwtTokenUtil;
import com.hotel.auth.service.OtpService;
import com.hotel.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private OtpService otpService;

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	ConfigProperties configProperties;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/send-otp")
	public ResponseEntity<?> sendOtp(@RequestBody LogInDetails loginDetails) {
		String mobileNumber = loginDetails.getMobileNumber();

		if (!isValidMobileNumber(mobileNumber)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Invalid MobileNumber");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HmResponse("failure", response));
		}

		String otp = otpService.generateOtp();
		otpService.saveOtp(mobileNumber, otp);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "OTP Sent to your mobile Number");

		if (configProperties.isOtpRequiredInConsole()) {
			response.put("otp", otp);
			return new ResponseEntity<>(new HmResponse("SUCCESS", response), HttpStatus.OK);
		}

		return new ResponseEntity<>(new HmResponse("SUCCESS", "Otp sent to mobile"), HttpStatus.OK);
	}

	@PostMapping("/verify-login")
	public ResponseEntity<?> verifyLogin(@RequestBody LogInDetails loginDetails) {
		String mobileNumber = loginDetails.getMobileNumber();
		String otp = loginDetails.getOtp();

		if (!otpService.validateOtp(mobileNumber, otp)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Invalid OTP");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HmResponse("failure", response));
		}

		Optional<User> user = userService.findUser(mobileNumber);

		if (!user.isPresent()) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "User does not exists");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HmResponse("failure", response));
		}
		String token = jwtTokenUtil.generateToken(mobileNumber);
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("mobileNumber", mobileNumber);
		userService.saveTokenWithUserId(user.get(), token);
		return new ResponseEntity<>(new HmResponse("success", response), HttpStatus.OK);
	}

	private boolean isValidMobileNumber(String mobileNumber) {
		return mobileNumber != null && mobileNumber.matches("^\\d{10,15}$");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LogInDetails loginDetails) {
		String userName = loginDetails.getUserName();
		String password = loginDetails.getPassword();

		Optional<User> user = userService.findUser(userName);

		if (!user.isPresent()) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "User does not exists");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new HmResponse("failure", response));
		}
		if (loginDetails.getPassword().equalsIgnoreCase(password)) {
			String token = jwtTokenUtil.generateToken(user.get().getMobileNumber());
			Map<String, Object> response = new HashMap<>();
			response.put("token", token);
			response.put("mobileNumber", userName);
			userService.saveTokenWithUserId(user.get(), token);
			return new ResponseEntity<>(new HmResponse("success", response), HttpStatus.OK);
		} else {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Invalid password");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new HmResponse("failure", response));
		}

	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(new HmResponse("success", userService.save(userRequest)), HttpStatus.OK);
	}
}
