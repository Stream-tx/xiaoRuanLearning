package com.example.gateway.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Classname KeyResolverConfiguration
 * @Description TODO
 * @Author asus
 * @Date 2021-12-25 20:35
 * @Version 1.0
 **/
@Configuration
public class KeyResolverConfiguration {

    private Logger logger = LoggerFactory.getLogger(KeyResolverConfiguration.class);

    @Bean
    public KeyResolver uriKeyResolver() {
        // return exchange -> Mono.just(exchange.getRequest().getURI().getPath());

        return new KeyResolver() {

            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                ServerHttpRequest request = exchange.getRequest();

                logger.info(request.getPath().toString());

                return Mono.just(request.getPath().toString());
            }

        };
    }
}
