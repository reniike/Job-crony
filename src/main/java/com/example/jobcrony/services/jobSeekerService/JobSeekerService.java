package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.requests.UpdateProfileRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import org.springframework.http.ResponseEntity;

public interface JobSeekerService {
    ResponseEntity<GenericResponse<String>> initiateRegistration(PreRegistrationRequest preRegistrationRequest) throws UserAlreadyExistException, SendMailException, CompanyNotFoundException;

    ResponseEntity<GenericResponse<String>> completeRegistration(JobSeekerRegistrationRequest jobSeekerRegistrationRequest) throws VerificationFailedException;
    ResponseEntity<GenericResponse<String>> updateProfile(UpdateProfileRequest updateProfileRequest) throws UserNotAuthorizedException;

    JobSeeker getJobSeekerById(Long id) throws UserNotAuthorizedException;
}
