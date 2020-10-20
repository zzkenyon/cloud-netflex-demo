package com.pd.gateway.filter;

import org.springframework.cloud.gateway.support.ipresolver.RemoteAddressResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.InetSocketAddress;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/20 10:43
 */
public class CustomRemoteAddressResolver implements RemoteAddressResolver {
    @Override
    public InetSocketAddress resolve(ServerWebExchange exchange) {
        return null;
    }
}
