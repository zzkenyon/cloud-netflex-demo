package com.pd.user;

import com.pd.result.RequestResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationAutoConfiguration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistryAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/30 15:45
 */
@SpringBootApplication(exclude = {ServiceRegistryAutoConfiguration.class, AutoServiceRegistrationAutoConfiguration.class})
@RestController
@EnableDiscoveryClient
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }

}
