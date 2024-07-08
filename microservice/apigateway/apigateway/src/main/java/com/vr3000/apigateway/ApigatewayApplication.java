package com.vr3000.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("Hello", "World")
								.circuitBreaker(config -> config
										.setName("mycmd")
										.setFallbackUri("forward:/fallback")
										.setRouteId("fallbackRoute")))
						.uri("http://localhost:8099"))
				.build();
	}

//	@Bean
//	public RouteLocator fallbackRoute(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/fallback")
//						.uri("http://localhost:8099/fallback"))
//				.build();
//	}
}
