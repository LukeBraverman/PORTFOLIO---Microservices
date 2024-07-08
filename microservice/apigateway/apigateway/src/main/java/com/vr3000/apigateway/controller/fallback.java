package com.vr3000.apigateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class fallback {

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        System.out.println("898 Fallback Triggered");
        return Mono.just("fallback");
    }
}
