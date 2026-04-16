package com.hotel.auth.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.hotel.common.model.User;
import com.hotel.common.model.UserWithToken;
import com.hotel.common.util.FosysConstants;

@Repository
public class UserRepository {
	private final MongoTemplate mongoTemplate;

	@Value("${jwt.expiration}")
	private long expirationMillis;

	public UserRepository(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public Optional<User> findUser(String userId) {
		Query query = new Query(new Criteria().orOperator(Criteria.where(FosysConstants.MOBILENUMBER).is(userId),
				Criteria.where(FosysConstants.USERID).is(userId), Criteria.where(FosysConstants.EMAIL).is(userId)));
		User user = mongoTemplate.findOne(query, User.class);
		return Optional.ofNullable(user);
	}

	public User save(User user) {
		return mongoTemplate.save(user);
	}

	public void saveTokenWithUserId(User user, String token) {
		Query query = new Query(Criteria.where("user.userId").is(user.getUserId()));
		Update update = new Update().set(FosysConstants.USER, user).set(FosysConstants.USER_TOKEN, token)
				.set(FosysConstants.EXPIRE_TIME, LocalDateTime.now().plusSeconds(expirationMillis));

		mongoTemplate.upsert(query, update, UserWithToken.class);

	}

	public UserWithToken getUserByToken(String token) {
		return mongoTemplate.findOne(new Query().addCriteria(Criteria.where(FosysConstants.USER_TOKEN).is(token)),
				UserWithToken.class);
	}

}
