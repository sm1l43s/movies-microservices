package com.movies.moviesservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "access-key", url = "http://localhost:8081/api/v1/key")
public interface AccessKeyClient {
    @GetMapping
    String getAccessKey();
}
