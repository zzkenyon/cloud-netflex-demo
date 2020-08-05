package com.pd.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class OrderServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(OrderServiceApplication.class,args);
    }
}
