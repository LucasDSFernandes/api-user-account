package com.lucasdsf.api.user.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Feign;
import feign.Request.Options;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

@Configuration
public class AuthServiceConfiguration {

	@Bean
	public Feign.Builder feignBuilder() {
		return Feign.builder()
				.logger(new Slf4jLogger(AuthServiceConfiguration.class))
	            .logLevel(feign.Logger.Level.FULL)
	            .options(new Options(6000, 6000))
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new StashErrorDecoder());
	}
}