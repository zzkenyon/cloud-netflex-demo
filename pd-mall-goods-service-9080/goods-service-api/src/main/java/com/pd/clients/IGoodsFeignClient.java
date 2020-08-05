package com.pd.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "goods-service")
public interface IGoodsFeignClient {
}
