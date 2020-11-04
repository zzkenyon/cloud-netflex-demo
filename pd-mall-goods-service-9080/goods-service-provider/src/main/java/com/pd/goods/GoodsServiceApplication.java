package com.pd.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GoodsServiceApplication {
    public static void main( String[] args ) {
        SpringApplication.run(GoodsServiceApplication.class,args);
    }
}
