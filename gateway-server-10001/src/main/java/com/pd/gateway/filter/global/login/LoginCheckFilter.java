package com.pd.gateway.filter.global.login;

import com.alibaba.fastjson.JSONObject;
import com.pd.gateway.filter.token.AccessToken;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/27 10:58
 */
@Component
public class LoginCheckFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request =exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        HttpCookie token = request.getCookies().getFirst("access_token");
        if(token == null ){
            // TODO 此处需要跳转到登录SSO页面
            JSONObject message = new JSONObject();// 响应消息内容对象
            message.put("code", -1);// 响应状态
            message.put("msg", "缺少凭证");// 响应内容
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);// 转换响应消息内容对象为字节
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);// 设置响应对象状态码 401
            // 设置响应对象内容并且指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        if(!isEffective(token)){

        }
        // 获取请求地址
        String beforePath = request.getPath().pathWithinApplication().value();
        // 获取响应状态码
        HttpStatus beforeStatusCode = response.getStatusCode();
        System.out.println("响应码：" + beforeStatusCode + "，请求路径：" + beforePath);
        // 请求前
        System.out.println("filter -> before");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // 获取请求地址
            String afterPath = request.getPath().pathWithinApplication().value();
            // 获取响应状态码
            HttpStatus afterStatusCode = response.getStatusCode();
            System.out.println("响应码：" + afterStatusCode + "，请求路径：" + afterPath);
            // 响应后
            System.out.println("filter -> after");
        }));

    }

    private boolean isEffective(HttpCookie token) {
        Assert.notNull(token,"待验证的token不能为null");
        AccessToken accessToken = new AccessToken(token.getValue());
        return accessToken.isValid();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
