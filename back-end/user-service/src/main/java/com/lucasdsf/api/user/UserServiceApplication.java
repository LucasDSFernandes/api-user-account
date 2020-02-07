package com.lucasdsf.api.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.lucasdsf.api",
							 "com.lucasdsf.api.user",
								"com.lucasdsf.api.user.rest.controllers"})
@EnableFeignClients(basePackages = {"com.lucasdsf.api.user.client"})
@EnableCaching
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
