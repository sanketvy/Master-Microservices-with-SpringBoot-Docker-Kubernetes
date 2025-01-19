package com.learning.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator routeConfig(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p-> p
                    .path("/api/account/**")
                    .filters(f -> f.addResponseHeader("X-Response-Time",LocalDateTime.now().toString())
                            .addRequestHeader("X-Correlation-Id", "ABED")
                            .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                    .setFallbackUri("forward:/error-url")
                            )

                    )
                    .uri("lb://ACCOUNTS")
                    )
//                .route(p-> p
//                    .path("/api/loans/**")
//                        .filters(f -> f.retry(retryConfig -> retryConfig
//                                        .setRetries(3)
//                                        .setMethods(HttpMethod.GET)
//                                        .setBackoff(Duration.ofSeconds(1L),Duration.ofSeconds(3L),2, true))
//                                )
//                    .uri("lb://LOANS")
//                ).build();
                .route(p-> p
                        .path("/api/loan/**")
                        .filters(f -> f.requestRateLimiter(config -> config.setRateLimiter(rateLimiter()).setKeyResolver(keyResolver()))
                        )
                        .uri("lb://LOANS")
                ).build();
    }

    @Bean
    public RedisRateLimiter rateLimiter(){
        return new RedisRateLimiter(1,1);
    }

    @Bean
    public KeyResolver keyResolver(){
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("X_User"))
                .defaultIfEmpty("anonymous");
    }

}
