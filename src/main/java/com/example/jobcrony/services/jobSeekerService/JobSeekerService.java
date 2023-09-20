package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserAlreadyExistException;
import com.example.jobcrony.exceptions.VerificationFailedException;
import org.springframework.http.ResponseEntity;

public interface JobSeekerService {
    ResponseEntity<GenericResponse<String>> initiateRegistration(PreRegistrationRequest preRegistrationRequest) throws UserAlreadyExistException;
    ResponseEntity<GenericResponse<String>> completeRegistration(JobSeekerRegistrationRequest jobSeekerRegistrationRequest) throws VerificationFailedException;
}
