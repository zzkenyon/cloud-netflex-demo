package com.pd.order.dto;

import com.pd.order.model.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrderService {
    @GetMapping(value = "/order/{oid}")
    Order queryOrderById(@PathVariable("oid") String oid);

    @PostMapping(value = "/order")
    Integer insertOrders(@RequestBody List<Order> orderList);

    @GetMapping(value = "/orders/{uid}")
    List<Order> queryOrderByUid(@PathVariable("uid") int uid);
}
