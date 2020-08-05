package com.pd.order.client;

import com.pd.order.dto.OrderService;
import com.pd.order.model.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/22 11:20
 */
@FeignClient(value = "order-service",fallback = OrderServiceFeignClient.OrderServiceFallBack.class)
public interface OrderServiceFeignClient extends OrderService{

    @Component
    class OrderServiceFallBack implements OrderServiceFeignClient{
        @Override
        public Order queryOrderById(String oid) {
            return null;
        }

        @Override
        public Integer insertOrders(List<Order> orderList) {
            return null;
        }

        @Override
        public List<Order> queryOrderByUid(int uid) {
            return null;
        }
    }

}
