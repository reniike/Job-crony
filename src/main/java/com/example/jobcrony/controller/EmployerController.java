package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.CompanyExistsException;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.services.employerService.EmployerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employer")
public class EmployerController {
    private EmployerService employerService;

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<String>> register(@RequestBody EmployerRegistrationRequest request) throws CompanyNotFoundException, LimitExceededException, CompanyExistsException, SendMailException {
        return employerService.register(request);
    }
}
