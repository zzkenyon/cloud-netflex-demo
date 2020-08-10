package com.pd.user.configtest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/8/3 16:42
 */
@Data
@Component
@RefreshScope
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class GitConfig implements Serializable {

    @Value("${data.env}")
    private String env;

    @Value("${data.user.username}")
    private String username;

    @Value("${data.user.password}")
    private String password;

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

}
