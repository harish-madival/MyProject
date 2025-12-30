package com.hotel.auth.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hotel.auth.model.User;
import com.hotel.auth.model.UserWithToken;

@Repository
public class UserRepository {
	private final MongoTemplate mongoTemplate;

	@Value("${jwt.expiration}")
	private long expirationMillis;

	public UserRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public Optional<User> findUser(String userId) {
		Query query = new Query(new Criteria().orOperator(Criteria.where("mobileNumber").is(userId),
				Criteria.where("userName").is(userId), Criteria.where("email").is(userId)));
		User user = mongoTemplate.findOne(query, User.class);
		return Optional.ofNullable(user);
	}

	public User save(User user) {
		return mongoTemplate.save(user);
	}

	public void saveTokenWithUserId(User user, String token) {
		Query query = new Query(Criteria.where("user.id").is(user.getId()));
		Update update = new Update().set("user", user).set("userToken", token).set("expiryTime",
				LocalDateTime.now().plusSeconds(expirationMillis));

		mongoTemplate.upsert(query, update, UserWithToken.class);

	}

	public UserWithToken getUserByToken(String token) {
		return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("userToken").is(token)),
				UserWithToken.class);
	}

}
