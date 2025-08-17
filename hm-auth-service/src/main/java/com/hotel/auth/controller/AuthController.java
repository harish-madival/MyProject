package com.hotel.auth.controller;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.auth.config.ConfigProperties;
import com.hotel.auth.dao.UserRepository;
import com.hotel.auth.model.HmResponse;
import com.hotel.auth.model.LogInDetails;
import com.hotel.auth.model.Roles;
import com.hotel.auth.model.User;
import com.hotel.auth.security.JwtTokenUtil;
import com.hotel.auth.service.OtpService;

@RestController
@RequestMapping("/login")
public class AuthController {

	@Autowired
	private OtpService otpService;

	@Autowired
	private UserRepository userRepository;

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
			return ResponseEntity.badRequest().body("Invalid mobile number");
		}

		String otp = otpService.generateOtp();
		otpService.saveOtp(mobileNumber, otp);

		Map<String, Object> response = new HashMap<>();
		response.put("message", "OTP Sent to your mobile Number");

		if (configProperties.isOtpRequiredInConsole()) {
			response.put("otp", otp);
			return new ResponseEntity<>(new HmResponse("success", response), HttpStatus.OK);
		}

		return new ResponseEntity<>(new HmResponse("success", response), HttpStatus.OK);
	}

	@PostMapping("/verify-login")
	public ResponseEntity<?> verifyLogin(@RequestBody LogInDetails loginDetails) {
		String mobileNumber = loginDetails.getMobileNumber();
		String otp = loginDetails.getOtp();

		if (!otpService.validateOtp(mobileNumber, otp)) {
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Invalid OTP");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new HmResponse("failure", response));
		}

		User user = userRepository.findUser(mobileNumber).orElseGet(() -> {
			User newUser = new User();
			newUser.setMobileNumber(mobileNumber);
			newUser.setUsername(mobileNumber);
			newUser.setCreatedBy(mobileNumber);
			newUser.setCreatedDate(Date.from(ZonedDateTime.now().toInstant()));
			newUser.setRoles(Roles.ENTERPRISE_ROLE);
			return userRepository.save(newUser);
		});

		String token = jwtTokenUtil.generateToken(mobileNumber);
		Map<String, Object> response = new HashMap<>();
		response.put("token", token);
		response.put("mobileNumber", mobileNumber);
//		response.put("roles", user.getRoles());
		return new ResponseEntity<>(new HmResponse("success", response), HttpStatus.OK);
	}

	private boolean isValidMobileNumber(String mobileNumber) {
		return mobileNumber != null && mobileNumber.matches("^\\d{10,15}$");
	}
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LogInDetails loginDetails) {
		String userName = loginDetails.getUserName();
		String password = loginDetails.getPassword();
		
		User user = userRepository.findUser(userName).get();

		Map<String, Object> response = new HashMap<>();
		response.put("message", "OTP Sent to your mobile Number");

		try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password)
            );
        } catch (Exception e) {
            return null;
        }
		return null;
	}
}
