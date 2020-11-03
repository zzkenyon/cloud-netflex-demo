package com.pd.gateway.filter.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/20 09:24
 */
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Global pre filter");
        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            log.info("Global post filter");
        }));
    }

    @Override
    public int getOrder() {
        return 100000;
    }
}
