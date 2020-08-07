package com.pd.order.client;

import com.pd.goods.dto.ItemDto;
import com.pd.order.dto.OrderDto;
import com.pd.result.RequestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:20
 */
@FeignClient(value = "order-service")
public interface OrderServiceFeignClient{

    @PostMapping(value = "/order")
    RequestResult createOrder(@Validated @RequestBody OrderDto orderDto, BindingResult bindingResult);
}
