package com.mytest.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderApplication is the main entry point for the Order service. It
 * initializes the Spring Boot application context and starts the application.
 */
@SpringBootApplication
@Slf4j
public class OrderApplication {
	public static void main(String[] args) {
		log.info("Starting Order Application");
		SpringApplication.run(OrderApplication.class, args);
	}
}