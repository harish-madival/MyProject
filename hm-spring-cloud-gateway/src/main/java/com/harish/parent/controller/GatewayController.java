package com.harish.parent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harish.parent.service.ApiService;

import reactor.core.publisher.Mono;

@RestController
public class GatewayController {

	private final ApiService apiService;

	public GatewayController(ApiService apiService) {
		this.apiService = apiService;
	}

	// API to get data from any service dynamically
	@GetMapping("/gateway/api/{serviceName}/data")
	public Mono<String> getServiceData(@PathVariable String serviceName, @RequestParam String endpoint) {
		// Pass the service name and endpoint to the ApiService
		return apiService.getDataFromService(serviceName, endpoint);
	}
}
