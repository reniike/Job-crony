package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.ResetPasswordRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.services.userService.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-crony-user")
public class UserController {
    private UserService userService;

    public ResponseEntity<GenericResponse<String>> resetPassword(@RequestBody ResetPasswordRequest request){
        return userService.resetPassword(request);
    }
}
