package com.pd.client;

import com.pd.result.RequestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface IUserAuthFeignClient {

    @GetMapping(value = "/token",consumes = MediaType.APPLICATION_JSON_VALUE)
    RequestResult<String> validToken(@RequestParam("token") String token) ;
}
