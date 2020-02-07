package com.lucasdsf.api.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lucasdsf.api.user.client.config.AuthServiceConfiguration;

@FeignClient(name = "${auth.api.name}", url = "${auth.api.url}", configuration = AuthServiceConfiguration.class)
public interface AuthService {

    @RequestMapping(path = "/login/refresh", method = RequestMethod.GET)
    void getPrincipal(@RequestHeader("Authorization") String authorization);
}