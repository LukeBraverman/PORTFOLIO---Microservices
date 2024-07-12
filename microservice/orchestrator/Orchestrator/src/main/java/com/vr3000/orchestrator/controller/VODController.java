package com.vr3000.orchestrator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr3000.orchestrator.model.VodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/Vod")
@RestController
public class VODController {

    @Autowired
    RestTemplate restTemplate;


    private final String vodUrl = "http://localhost:8086/vod";
    private final String videoUrl = "http://localhost:8098";

    @PostMapping()
    public ResponseEntity<String> saveVod(@RequestPart("vodDTO") String vodDTOJson,
                                          @RequestParam("file") MultipartFile file,
                                          @RequestParam("userId") String userId,
                                          @RequestParam("filePath") String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        VodDTO vodDTO = objectMapper.readValue(vodDTOJson, VodDTO.class);

        System.out.println("Called Hi");
        String result = restTemplate.postForObject(vodUrl, vodDTO, String.class);
        System.out.println("Called Point two");

        // Convert MultipartFile to Resource
        ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("file", fileAsResource);
        formData.add("userId", userId);
        formData.add("filePath", filePath);


        String result2 = restTemplate.postForObject(videoUrl+"/api/files/upload",formData , String.class);
        System.out.println("Called Point Three");

        return ResponseEntity.ok("Hello, World!");
    }

}

