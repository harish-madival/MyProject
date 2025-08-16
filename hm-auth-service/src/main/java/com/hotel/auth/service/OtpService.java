package com.hotel.auth.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hotel.auth.model.OtpVerification;

@Service
public class OtpService {
	private static final int OTP_LENGTH = 6;
	private static final int OTP_VALID_MINUTES = 5;

	private final MongoTemplate mongoTemplate;

	public OtpService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public String generateOtp() {
		return String.format("%06d", new Random().nextInt(999999));
	}

	public void saveOtp(String mobileNumber, String otp) {
		Query query = new Query(Criteria.where("mobileNumber").is(mobileNumber));
		Update update = new Update().set("otp", otp).set("expiryTime",
				LocalDateTime.now().plusMinutes(OTP_VALID_MINUTES));

		mongoTemplate.upsert(query, update, OtpVerification.class);
	}

	public boolean validateOtp(String mobileNumber, String otp) {
		Query query = new Query(Criteria.where("mobileNumber").is(mobileNumber).and("otp").is(otp).and("expiryTime")
				.gt(LocalDateTime.now()));

		return mongoTemplate.exists(query, OtpVerification.class);
	}

	@Scheduled(fixedRate = 300000) // Clean every 5 minutes
	public void cleanupExpiredOtps() {
		Query query = new Query(Criteria.where("expiryTime").lt(LocalDateTime.now()));
		mongoTemplate.remove(query, OtpVerification.class);
	}
}