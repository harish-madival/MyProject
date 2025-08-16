package com.hotel.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HotelAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelAuthServiceApplication.class, args);
	}
}
