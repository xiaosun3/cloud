package com.cloud.configuration;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.handler.predicate.PathRoutePredicateFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by sunhaidi on 2019-06-06.
 */
@Configuration
public class GateWayConfig {

    @Bean
    @RefreshScope
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("cloud-api", r -> r.path("/cloud-api/**")
                        .filters(f -> {
                            f.addRequestHeader("headerName1", "headerValue1");
                            f.redirect(300,"http://www.baidu.com");
                            return f;
                        })
                        .uri("http://127.0.0.1:8080"))

                .build();
    }


}
