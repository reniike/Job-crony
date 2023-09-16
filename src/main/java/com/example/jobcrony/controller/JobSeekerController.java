package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserAlreadyExistException;
import com.example.jobcrony.exceptions.VerificationFailedException;
import com.example.jobcrony.services.jobSeekerService.JobSeekerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-seeker")
public class JobSeekerController {
    private JobSeekerService jobSeekerService;

    @PostMapping("/initiateRegistration")
    public ResponseEntity<GenericResponse<String>> initiateRegistration(@RequestBody PreRegistrationRequest preRegistrationRequest) throws UserAlreadyExistException {
        return jobSeekerService.initiateRegistration(preRegistrationRequest);
    }

    @PostMapping("/completeRegistration")
    public ResponseEntity<GenericResponse<String>> completeRegistration(@RequestBody JobSeekerRegistrationRequest jobSeekerRegistrationRequest) throws VerificationFailedException {
        return jobSeekerService.completeRegistration(jobSeekerRegistrationRequest);
    }
}
