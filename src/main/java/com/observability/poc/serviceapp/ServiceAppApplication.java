package com.observability.poc.serviceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;


@SpringBootApplication
public class ServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAppApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@RestController
	class HelloController {

		private final RestTemplate restTemplate;

		HelloController(RestTemplate restTemplate) {
			this.restTemplate = restTemplate;
		}

		@GetMapping("/hello")
		public String hello() {
			ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(
				"https://httpbin.org/post", "Hello Cloud!", String.class);
			return responseEntity.getBody();
		}
		
	}

}
