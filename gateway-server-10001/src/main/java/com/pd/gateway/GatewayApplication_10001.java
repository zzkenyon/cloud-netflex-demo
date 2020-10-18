package com.pd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/7/28 09:25
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication_10001 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication_10001.class,args);
    }
}
