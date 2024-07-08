package com.vr3000.orchestrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class HelloWorld {

    @Autowired
    RestTemplate restTemplate;

    private final String vodUrl = "http://localhost:8086/vod";
    private final String videoUrl = "http://localhost:8098/video";


    @GetMapping("/get")
    public ResponseEntity<String> helloWorld() {
        System.out.println("This API was called 090!");
        String result = restTemplate.getForObject(vodUrl, String.class);
        System.out.println("Vod url called: "+ result);
        String result2 = restTemplate.getForObject(videoUrl, String.class);
        System.out.println("Video url called: "+ result2);

        return ResponseEntity.ok("Hello, World!");
    }
}
