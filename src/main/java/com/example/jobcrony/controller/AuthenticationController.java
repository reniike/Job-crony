package com.example.jobcrony.controller;

import com.example.jobcrony.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


//    @PostMapping("/register")
//    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegistrationRequest registerRequest){
//        return ResponseEntity.ok(authenticationService.register(registerRequest));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
//        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
//    }
}
