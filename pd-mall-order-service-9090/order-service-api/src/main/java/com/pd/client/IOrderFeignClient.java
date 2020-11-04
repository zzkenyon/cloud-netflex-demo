package com.pd.client;

import com.pd.order.dto.OrderDto;
import com.pd.result.RequestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:20
 */
@FeignClient(value = "order-service")
public interface IOrderFeignClient {

    @PostMapping(value = "/order")
    RequestResult createOrder(@RequestBody OrderDto orderDto);

    @GetMapping(value = "/orders")
    RequestResult queryOrders();
}
