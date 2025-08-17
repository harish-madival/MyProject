package com.hm.food.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@PropertySources({ @PropertySource("classpath:config.properties"),
		@PropertySource(value = "classpath:config-${spring.profiles.active}.properties", ignoreResourceNotFound = true) })
public class ConfigProperties {
	
	@Value(value = "${food.collection.name}")
	String foodCollectionName;

}
