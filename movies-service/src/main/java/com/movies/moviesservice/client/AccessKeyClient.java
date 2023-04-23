package com.movies.moviesservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "access-key-service")
public interface AccessKeyClient {
    @GetMapping(value = "/api/v1/keys")
    String getAccessKey();
}
