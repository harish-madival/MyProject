package com.hotel.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.hotel.common.model.User;

@FeignClient(name = "hm-auth-service")
public interface AuthFeignClient {

	@PostMapping("/fosys/auth/validate")
	User validateToken(@RequestHeader("Authorization") String token);
}
