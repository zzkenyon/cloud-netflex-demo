package com.pd.user.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.pd.order.model.Order;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/31 10:30
 */
public class HystrixCommandService extends HystrixCommand<Order> {

    int id;
    RestTemplate restTemplate;
    public HystrixCommandService(int id, RestTemplate restTemplate) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("order-service")).
                andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        .withCircuitBreakerEnabled(true)
                        .withCircuitBreakerRequestVolumeThreshold(5)
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        .withCircuitBreakerSleepWindowInMilliseconds(5)
                ));
        this.id = id;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Order run() throws Exception {
        return restTemplate.getForObject("http://localhost:9090/order/"+id, Order.class);
    }

    @Override
    protected Order getFallback() {
        System.out.println("请求被降级了");
        return null;
    }
}
