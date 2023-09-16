package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.AdminInvitationRequest;
import com.example.jobcrony.dtos.requests.AdminRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.AdminExistException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.services.adminService.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<String>> register(@RequestBody AdminRegistrationRequest request) throws UserNotFoundException, AdminExistException {
        return adminService.registerAdmin(request);
    }

    @PostMapping("/sendInvitationLink")
    public ResponseEntity<GenericResponse<String>> sendLink(@RequestBody AdminInvitationRequest request) throws AdminExistException {
        return adminService.sendInvitationLink(request);
    }
}
