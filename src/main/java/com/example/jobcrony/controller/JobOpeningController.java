package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.JobOpeningRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.services.jobOpeningService.JobOpeningService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/job/")
@AllArgsConstructor
public class JobOpeningController {
    private JobOpeningService jobOpeningService;

    @PostMapping("postJobOpening")
    private ResponseEntity<GenericResponse<String>> postJobOpening(@RequestBody JobOpeningRequest request) throws UserNotAuthorizedException {
        return jobOpeningService.postJobOpening(request);
    }

}

