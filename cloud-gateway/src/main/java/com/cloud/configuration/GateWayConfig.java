package com.cloud.configuration;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunhaidi on 2019-06-06.
 */
@Configuration
public class GateWayConfig {


//    @Bean
//    public RouteLocatorBuilder routeLocatorBuilder(ConfigurableApplicationContext context) {
//        return new RouteLocatorBuilder(context);
//    }

//    @Bean
//    public PathRoutePredicateFactory pathRoutePredicateFactory() {
//        return new PathRoutePredicateFactory();
//    }

    @Bean
    @RefreshScope
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("**/api/**")
                        .uri("http://127.0.0.1:8080"))
                .route(p -> p
                        .predicate(exchange -> exchange.getRequest().getPath().subPath(0).toString().startsWith(("/openhome/")))
                        .filters(f -> f.rewritePath("/openhome/(?<remaining>.*)", "/${remaining}")).uri("https://openhome.cc"))
                .build();
    }


}
