package com.pd.goods.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "goods-service")
public interface IGoodsFeignClient {
}
