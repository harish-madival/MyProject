package com.hm.food.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "spring.data.mongodb")
@Getter
@Setter
public class MongoProperties {
	private String hosts;
	private String database;
	private String username;
	private String password;
	private String replicaSet;
	private boolean ssl;
}