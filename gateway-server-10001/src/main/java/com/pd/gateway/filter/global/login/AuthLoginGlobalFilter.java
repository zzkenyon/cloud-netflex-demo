package com.pd.gateway.filter.global.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pd.client.IUserAuthFeignClient;
import com.pd.result.RequestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/11/4 14:58
 */
@Component
@Slf4j
@PropertySource(value = "classpath:loginFilter.properties")
public class AuthLoginGlobalFilter implements GlobalFilter, Ordered {
    @Value("#{'${jwt.ignoreUrls}'.split(',')}")
    List<String> ignoreUrls;

    private IUserAuthFeignClient userAuthFeignClient;
    @Autowired
    public void setUserAuthFeignClient(IUserAuthFeignClient userAuthFeignClient) {
        this.userAuthFeignClient = userAuthFeignClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(ignoreUrls!=null && ignoreUrls.contains(request.getURI().getPath())){
            return chain.filter(exchange);
        }
        HttpCookie cookie = request.getCookies().getFirst("access_token");
        if(cookie == null){
            return onError(exchange,"尚未登录");
        }
        String access_token = cookie.getValue();
        RequestResult<String> r = userAuthFeignClient.validToken(access_token);
        if(r.getCode() == 200){
            ServerHttpRequest shr = request.mutate().header("uid",r.getData()).build();
            return chain.filter(exchange.mutate().request(shr).build());
        }
        return onError(exchange,r.getMsg());
    }

    private Mono<Void> onError(ServerWebExchange exchange, String msg) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        RequestResult<Void> r = new RequestResult.Builder<Void>().buildCustomize(HttpStatus.UNAUTHORIZED.value(),msg);
        ObjectMapper objectMapper = new ObjectMapper();
        String rs = "";
        try {
            rs = objectMapper.writeValueAsString(r);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException" + e) ;
        }
        DataBuffer buffer = response.bufferFactory().wrap(rs.getBytes());
        return response.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
