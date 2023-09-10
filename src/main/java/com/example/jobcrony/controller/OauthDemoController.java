package com.example.jobcrony.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class OauthDemoController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello world");
    }
}
