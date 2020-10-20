package com.pd.gateway.filter.global;

import com.alibaba.fastjson.JSON;
import com.pd.result.RequestResult;
import io.micrometer.core.instrument.util.JsonUtils;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/20 15:31
 *
 * 过滤指定的ip地址
 * ip黑名单可以存在redis中
 */
@Component
@Order(0)
public class IpCheckFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        if(getIp(headers)!= null && getIp(headers).equals("127.0.0.1")){
            ServerHttpResponse response = exchange.getResponse();
            RequestResult res = new RequestResult.Builder<>().code(401).meg("非法请求").build();
            byte[] datas = JSON.toJSONBytes(res);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }
    // 这边从请求头中获取用户的实际IP,根据Nginx转发的请求头获取
    private String getIp(HttpHeaders headers) {
        List<String> ip = headers.get("RemoteIpAddress");
        if(ip != null){
            return ip.get(0);
        }
        return null;
    }
}
