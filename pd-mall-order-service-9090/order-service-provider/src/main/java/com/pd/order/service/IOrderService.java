package com.pd.order.service;

import com.pd.order.dto.OrderDto;

public interface IOrderService {

    String createOrder(OrderDto orderDto);
}
