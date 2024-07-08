package com.vr3000.vodmicroservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {

    @GetMapping("/vod")
    public ResponseEntity<String> helloWorld() {
        System.out.println("This API was called VOD OD VOD!");
        return ResponseEntity.ok("Hello, World from vod!");
    }
}
