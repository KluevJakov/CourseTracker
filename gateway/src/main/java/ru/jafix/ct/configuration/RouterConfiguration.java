package ru.jafix.ct.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouterConfiguration {

    @Value("${client.auth.url}")
    private String CLIENT_AUTH_URL;
    @Value("${client.course.url}")
    private String CLIENT_COURSE_URL;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(e -> e.path("/api/auth/**", "/api/activate/**", "/api/users/**")
                        .uri(CLIENT_AUTH_URL))
                .route(e -> e.path("/api/courses/**", "/api/members/**")
                        .uri(CLIENT_COURSE_URL))
                .build();
    }
}
