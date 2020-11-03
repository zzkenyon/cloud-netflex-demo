package com.pd.gateway.filter.global.log;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/27 09:22
 */
public interface BodyHandlerFunction
        extends BiFunction<ServerHttpResponse, Publisher<? extends DataBuffer>, Mono<Void>> {
}
