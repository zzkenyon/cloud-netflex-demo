package com.pd.order.controller;

import com.pd.order.converter.OrderConverter;
import com.pd.order.client.OrderServiceFeignClient;
import com.pd.order.dto.OrderDto;
import com.pd.order.service.impl.OrderService;
import com.pd.result.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:25
 */
@RestController
@Slf4j
public class OrderController implements OrderServiceFeignClient {

    @Autowired
    private OrderService service;

    @Override
    public RequestResult createOrder(OrderDto orderDto,BindingResult bindingResult) {
        orderDto.validData(bindingResult);
        String orderId=service.createOrder(orderDto);
        return new RequestResult.Builder<>().data(orderId).Ok();
    }

    @Override
    public RequestResult queryOrders() {
        List<String> orderIds = service.queryIds();
        return new RequestResult.Builder<>().data(orderIds).Ok();
    }
}
