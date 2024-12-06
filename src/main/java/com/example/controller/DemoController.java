package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.services.DemoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DemoService demoService;
    
    @GetMapping
    public ResponseEntity<String> demo() {
        try {
            String jsonData = objectMapper.writeValueAsString(demoService.getDemo());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
            return new ResponseEntity<>(jsonData, headers, HttpStatus.OK);
          
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
