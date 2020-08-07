package com.pd.user.hystrix;

import com.pd.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/31 10:28
 */
@RestController
public class HystrixCommandController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hystrix/command/{id}")
    public Order hystrixCommand(@PathVariable("id") int id){
        HystrixCommandService commandService = new HystrixCommandService(id,restTemplate);
        return commandService.execute();
    }
}
