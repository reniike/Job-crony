package com.example.jobcrony.controller;

import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.requests.UpdateProfileRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import com.example.jobcrony.services.jobSeekerService.JobSeekerService;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-seeker")
public class JobSeekerController {
    private JobSeekerService jobSeekerService;

    @PostMapping("/initiateRegistration")
    public ResponseEntity<GenericResponse<String>> initiateRegistration(@RequestBody PreRegistrationRequest preRegistrationRequest) throws UserAlreadyExistException, SendMailException, CompanyNotFoundException {
        return jobSeekerService.initiateRegistration(preRegistrationRequest);
    }

    @PostMapping("/completeRegistration")
    public ResponseEntity<GenericResponse<String>> completeRegistration(@RequestBody JobSeekerRegistrationRequest jobSeekerRegistrationRequest) throws VerificationFailedException {
        return jobSeekerService.completeRegistration(jobSeekerRegistrationRequest);
    }

    @PutMapping("/updateProfileDetails")
    public ResponseEntity<GenericResponse<String>> updateProfile(@RequestBody UpdateProfileRequest request) throws UserNotAuthorizedException {
        return jobSeekerService.updateProfile(request);
    }

    @GetMapping("/getJobSeeker/{id}")
    public ResponseEntity<JobSeeker> getJobSeeker(@PathVariable Long id) throws UserNotAuthorizedException {
        return ResponseEntity.ok().body(jobSeekerService.getJobSeekerById(id));
    }

    @GetMapping("/getJobSeeker")
    public ResponseEntity<JobSeeker> getJobSeeker(){
        return ResponseEntity.ok().body(jobSeekerService.getJobSeeker());
    }
}
