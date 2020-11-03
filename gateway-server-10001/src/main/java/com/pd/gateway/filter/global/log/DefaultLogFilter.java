package com.pd.gateway.filter.global.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.cloud.gateway.support.DefaultServerRequest;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/27 09:21
 */
public class DefaultLogFilter implements GlobalFilter, Ordered {
    /**
     * 日志信息
     */
    private static final Logger logger = LoggerFactory.getLogger(DefaultLogFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long startTime = System.currentTimeMillis();
        String trace = exchange.getRequest().getHeaders().getFirst("trace");
        ServerRequest serverRequest = new DefaultServerRequest(exchange);
        Mono<String> resMono = serverRequest.bodyToMono(String.class);
        return resMono.flatMap(reqBody -> {
            //重写原始请求
            ServerHttpRequestDecorator decorator = new ServerHttpRequestDecorator(exchange.getRequest()) {
                @Override
                public HttpHeaders getHeaders() {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.putAll(super.getHeaders());
                    return httpHeaders;
                }

                @Override
                public Flux<DataBuffer> getBody() {
                    //打印原始请求日志
                    logger.info("[Trace:{}]-gateway request:headers=[{}],body=[{}]", trace, getHeaders(), reqBody);
                    return Flux.just(reqBody).map(bx -> exchange.getResponse().bufferFactory().wrap(bx.getBytes()));
                }
            };
            //重写原始响应
            BodyHandlerServerHttpResponseDecorator responseDecorator = new BodyHandlerServerHttpResponseDecorator(
                    initBodyHandler(exchange, startTime), exchange.getResponse());

            return chain.filter(exchange.mutate().request(decorator).response(responseDecorator).build());
        });
    }

    @Override
    public int getOrder() {
        //在NettyWriteResponseFilter之前
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 20;
    }

    /**
     * 响应body处理，添加响应的打印
     *
     * @param exchange
     * @param startTime
     * @return
     */
    protected BodyHandlerFunction initBodyHandler(ServerWebExchange exchange, long startTime) {
        return (resp, body) -> {
            //拦截
            String trace = exchange.getRequest().getHeaders().getFirst("trace");
            MediaType originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(originalResponseContentType);
            DefaultClientResponseAdapter clientResponseAdapter = new DefaultClientResponseAdapter(body, httpHeaders);
            Mono<String> bodyMono = clientResponseAdapter.bodyToMono(String.class);
            return bodyMono.flatMap((respBody) -> {
                //打印返回响应日志
                logger.info("[Trace:{}]-gateway response:ct=[{}], status=[{}],headers=[{}],body=[{}]", trace,
                        System.currentTimeMillis() - startTime, resp.getStatusCode(), resp.getHeaders(), respBody);
                return resp.writeWith(Flux.just(respBody).map(bx -> resp.bufferFactory().wrap(bx.getBytes())));
            }).then();
        };
    }
}
