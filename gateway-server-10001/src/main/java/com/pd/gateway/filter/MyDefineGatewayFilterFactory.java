package com.pd.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhaozhengkang
 * @description
 * @date 2020/10/18 20:40
 */
public class MyDefineGatewayFilterFactory extends AbstractGatewayFilterFactory<MyDefineGatewayFilterFactory.GpConfig> {

    private static final String NAME_KEY="name";

    Logger logger= LoggerFactory.getLogger(MyDefineGatewayFilterFactory.class);

    public MyDefineGatewayFilterFactory() {
        super(GpConfig.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY);
    }

    @Override
    public GatewayFilter apply(GpConfig config) {
        //Filter pre  post
        return ((exchange,chain)->{
            logger.info("[pre] Filter Request, name:"+config.getName());
            //TODO
            // 这句的意思是执行完过滤器连之后，执行Runnable中的逻辑，完了发射产生的Mono
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                //TODO
                logger.info("[post]: Response Filter");
            }));
        });
    }

    public static class GpConfig{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}



