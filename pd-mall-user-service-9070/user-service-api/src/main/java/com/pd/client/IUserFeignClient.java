package com.pd.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("user-service")
public interface IUserFeignClient {

}
