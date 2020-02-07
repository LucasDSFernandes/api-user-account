package com.lucasdsf.api.user.client.config;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasdsf.api.user.exception.ErrorException;
import com.lucasdsf.api.user.rest.response.ResponseError;

import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

public class StashErrorDecoder implements ErrorDecoder {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.body() != null) {
			if (response.status() >= 400 && response.status() <= 500) {
				try {
					String body = Util.toString(response.body().asReader());
					ObjectMapper objectMapper = new ObjectMapper();
					objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
				
					ResponseError errorMessage = objectMapper.readValue(body, ResponseError.class);
					
					if (Objects.nonNull(errorMessage.getError()) && !errorMessage.getError().isEmpty()) {
						throw new ErrorException("Erro ao consultar acesso a token");
					}						
				} catch (IOException e) {
					logger.error("{} {}: {}", e.getClass().getName(), "Erro ao deserializar o body", e.getMessage());
					logger.error("{} {}: {}", e.getClass().getName(), "Erro ao deserializar o body", e);
				}
			}
		}
		
		return FeignException.errorStatus(methodKey, response);
	}
}