package com.harish.parent.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ApiService {

	private final WebClient.Builder webClientBuilder;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    /**
     * Get data from the specified service.
     * @param serviceName The name of the service (as registered in Eureka).
     * @param endpoint The specific endpoint on the service.
     * @return Mono<String> containing the response.
     */
    public Mono<String> getDataFromService(String serviceName, String endpoint) {
        // Build WebClient dynamically based on the service name (via Eureka)
        WebClient webClient = webClientBuilder.baseUrl("Harish://" + serviceName).build();

        return webClient.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(String.class);
    }
}
