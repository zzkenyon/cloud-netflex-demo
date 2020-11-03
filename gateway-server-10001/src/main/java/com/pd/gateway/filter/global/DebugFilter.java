package com.pd.gateway.filter.global;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/20 16:02
 */

/**
 * 可以在a/b测试中使用这样的过滤器
 */
@Order(101010)
public class DebugFilter implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI requestUUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);
        try {
            // 修改路由
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR,new URI("https://www.baidu.com"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return chain.filter(exchange);
    }
}
