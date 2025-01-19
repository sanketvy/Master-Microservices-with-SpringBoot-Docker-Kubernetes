package com.learning.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

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
                .route(p-> p
                    .path("/api/loans/**")
                    .uri("lb://LOANS")
                ).build();
    }
}
