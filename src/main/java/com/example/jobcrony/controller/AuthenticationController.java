package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.AuthenticationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.security.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<GenericResponse<String>> login(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.authenticate(authenticationRequest);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<GenericResponse<String>> logout(){
////        return authenticationService.logout();
//    }
}
