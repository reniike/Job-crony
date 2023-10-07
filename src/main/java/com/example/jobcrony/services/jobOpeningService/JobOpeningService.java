package com.example.jobcrony.services.jobOpeningService;

import com.example.jobcrony.data.models.Application;
import com.example.jobcrony.data.models.JobOpening;
import com.example.jobcrony.dtos.requests.JobOpeningRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import org.springframework.http.ResponseEntity;

public interface JobOpeningService {
    ResponseEntity<GenericResponse<String>> postJobOpening(JobOpeningRequest request) throws UserNotAuthorizedException;
    JobOpening findJobOpening(Long jobOpeningId);
    GenericResponse<String> verifyJobOpening(Long jobOpeningId);

}
