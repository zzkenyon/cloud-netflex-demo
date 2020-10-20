package com.pd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/28 09:25
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class GatewayApplication_10001 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication_10001.class,args);
    }

    @GetMapping(value = "hello")
    public Mono<String> hello(){
        return Mono.just("hello world");
    }
}
