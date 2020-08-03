package com.pd.order.controller;

import com.pd.order.dto.OrderService;
import com.pd.order.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:25
 */
@RestController
public class OrderController implements OrderService {
    @Override
    public Order queryOrderById(String oid) {
        Order res = new Order();
        res.setOId("12313123123144");
        res.setTimeStamp(new Date());
        return res;
    }

    @Override
    public Integer insertOrders(List<Order> orderList) {
        return null;
    }

    @Override
    public List<Order> queryOrderByUid(int uid) {
        Order order = new Order();
        order.setOId("1");
        order.setTimeStamp(new Date());
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        order.setOId("2");
        orders.add(order);
        return orders;
    }
}
