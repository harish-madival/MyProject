package com.hm.onboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HmOnboardingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmOnboardingServiceApplication.class, args);
	}

}
