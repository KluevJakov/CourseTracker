package ru.jafix.ct.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfiguration {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(e -> e.path("/api/auth/**", "/api/activate/**", "/api/users/**")
                        .uri("http://localhost:8081"))
                .route(e -> e.path("/api/courses/**", "/api/members/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
