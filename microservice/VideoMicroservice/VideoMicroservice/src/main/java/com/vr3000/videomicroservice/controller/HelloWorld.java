package com.vr3000.videomicroservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {

    @GetMapping("/video")
    public ResponseEntity<String> helloWorld() {
        System.out.println("This API was called 090 HELO VIDeO!");
        return ResponseEntity.ok("Hello, World VIDEOOO!");
    }

}
