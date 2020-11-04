package com.pd.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
//@SpringBootApplication(exclude = {ServiceRegistryAutoConfiguration.class,AutoServiceRegistrationAutoConfiguration.class})
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.pd.client"})
@EnableDiscoveryClient
public class OrderServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(OrderServiceApplication.class,args);
    }
}
