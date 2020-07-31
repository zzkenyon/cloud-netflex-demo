package com.pd.user.controller;

import com.pd.order.client.OrderServiceFeignClient;
import com.pd.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 13:59
 */
@RestController
public class UserOrderController {

    private OrderServiceFeignClient orderServiceFeignClient;
    @Autowired
    public void setOrderServiceFeignClient(OrderServiceFeignClient orderServiceFeignClient) {
        this.orderServiceFeignClient = orderServiceFeignClient;
    }

    @GetMapping("/orders/{uid}")
    public List<Order> queryOrderByUid(@PathVariable("uid")int uid){

        return orderServiceFeignClient.queryOrderByUid(uid);
    }

}
