package com.pd.order.service;

import com.pd.order.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    String createOrder(OrderDto orderDto);

    List<String> queryIds();

}
