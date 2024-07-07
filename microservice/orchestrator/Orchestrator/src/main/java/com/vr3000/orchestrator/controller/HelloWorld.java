package com.vr3000.orchestrator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorld {

    @GetMapping("/get")
    public ResponseEntity<String> helloWorld() {
        System.out.println("This API was called 090!");
        return ResponseEntity.ok("Hello, World!");
    }
}
