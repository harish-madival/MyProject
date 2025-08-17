package com.hotel.auth.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;

import com.hotel.auth.model.OtpVerification;
import com.hotel.auth.model.User;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig {

	@Value("${hotel.mongodb.urls}")
	public String mongoUrls;

	@Value("${hotel.mongodb.database}")
	public String dataBase;

	@Bean
	MongoClient mongoClient() {
		StringBuilder urls = new StringBuilder(String.format("mongodb://%s", mongoUrls));
		ConnectionString connString = new ConnectionString(urls.toString());
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString)
				.applyToConnectionPoolSettings(
						builder -> builder.minSize(5).maxSize(100).maxWaitTime(2000, TimeUnit.MILLISECONDS))
				.build();
		return MongoClients.create(settings);
	}

	@Bean
	MongoTemplate mongoTemplate() {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), "auth_service");
		initIndexes(mongoTemplate);
		return mongoTemplate;
	}

	private void initIndexes(MongoTemplate mongoTemplate) {
		// TTL index for automatic OTP expiration
		IndexOperations otpIndexOps = mongoTemplate.indexOps(OtpVerification.class);
		Index ttlIndex = new Index().on("expiryTime", Sort.Direction.ASC).expire(0L, TimeUnit.SECONDS); // Expire
																										// immediately
																										// after time
																										// passes
		otpIndexOps.ensureIndex(ttlIndex);

		// Unique index for mobile number in users collection
		IndexOperations userIndexOps = mongoTemplate.indexOps(User.class);
		Index mobileIndex = new Index().on("mobileNumber", Sort.Direction.ASC).unique();
		userIndexOps.ensureIndex(mobileIndex);
	}
}
