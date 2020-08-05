package com.pd.user.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.pd.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/31 09:53
 */
@RestController
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
    },fallbackMethod = "fallback",threadPoolKey = "order-service")
    @GetMapping(value = "/hystrix/order/{uid}")
    public List queryOrder(@PathVariable("uid") int uid){
        //restTemplate默认有一个请求超时时间
        return  restTemplate.getForObject("http://localhost:9090/orders/"+uid, List.class);
    }


    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "10")
    },fallbackMethod ="timeoutFallback",threadPoolKey = "order-service")
    @GetMapping("/hystrix/timeout/{oid}")
    public Order queryOrderTimeout(@PathVariable("oid") int oid){
        return  restTemplate.getForObject("http://localhost:9090/order/"+oid, Order.class);
    }

    public List fallback(int num){
        System.out.println("熔断导致服务降级了");
        return null;
    }

    public Order timeoutFallback(int oid){
        System.out.println("超时导致服务降级了");
        return null;
    }
}
