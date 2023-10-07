package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.Application;
import com.example.jobcrony.data.models.JobOpening;
import com.example.jobcrony.dtos.requests.ApplicationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.ApplicationAlreadyExistsException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {
    ResponseEntity<GenericResponse<String>> initiateJobApplication(ApplicationRequest request) throws UserNotAuthorizedException, UserNotFoundException, ApplicationAlreadyExistsException;
    ResponseEntity<GenericResponse<String>> withdrawApplication(Long applicationId) throws UserNotAuthorizedException;
    ResponseEntity<GenericResponse<String>>  reviewApplication(Long applicationId) throws UserNotAuthorizedException;
    ResponseEntity<GenericResponse<String>>  acceptApplication(Long applicationId) throws SendMailException, UserNotAuthorizedException;
    ResponseEntity<GenericResponse<String>>  rejectApplication(Long applicationId) throws SendMailException, UserNotAuthorizedException;
    List<Application> getAllApplications(Long jobOpeningId);
    List<Application> saveApplications(List<Application> applications);
}

