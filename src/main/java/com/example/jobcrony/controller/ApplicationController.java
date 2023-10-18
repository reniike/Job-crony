package com.example.jobcrony.controller;

import com.example.jobcrony.data.models.Application;
import com.example.jobcrony.dtos.requests.ApplicationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.ApplicationAlreadyExistsException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.services.applicationService.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-crony/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<GenericResponse<String>> apply(@RequestBody ApplicationRequest request) throws UserNotFoundException, UserNotAuthorizedException, ApplicationAlreadyExistsException {
        return applicationService.initiateJobApplication(request);
    }

    @GetMapping("/getAllApplications")
    public ResponseEntity<List<Application>> getAllApplication(@RequestBody Long jobOpeningId) {
        List<Application> applications = applicationService.getAllApplications(jobOpeningId);
        return ResponseEntity.ok().body(applications);
    }

    @PostMapping("/withdrawApplication")
    public ResponseEntity<GenericResponse<String>> withdrawApplication(@RequestBody Long applicationId) throws UserNotAuthorizedException {
        return applicationService.withdrawApplication(applicationId);
    }

    @PostMapping("/reviewApplication")
    public ResponseEntity<GenericResponse<String>> reviewApplication(@RequestBody Long applicationId) throws UserNotAuthorizedException {
        return applicationService.reviewApplication(applicationId);
    }

    @PostMapping("/acceptApplication")
    public ResponseEntity<GenericResponse<String>> acceptApplication(@RequestBody Long applicationId) throws SendMailException, UserNotAuthorizedException {
        return applicationService.acceptApplication(applicationId);
    }

    @PostMapping("/rejectApplication")
    public ResponseEntity<GenericResponse<String>> rejectApplication(@RequestBody Long applicationId) throws SendMailException, UserNotAuthorizedException {
        return applicationService.rejectApplication(applicationId);
    }

    @PostMapping("/getApplication/{id}")
    public ResponseEntity<GenericResponse<?>> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(applicationService.getApplicationById(id));
    }
}
