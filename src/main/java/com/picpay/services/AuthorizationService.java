package com.picpay.services;

import com.picpay.view.AuthorizationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "authorization-service", url = "https://run.mocky.io/v3/55badc16-62d4-41dd-8b10-6749db929048")
public interface AuthorizationService {

    @GetMapping
    AuthorizationResponse checkAuthorization();
}

