package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.Application;
import com.example.jobcrony.dtos.requests.ApplicationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApplicationService {
    List<Application> saveApplications(List<Application> applications);
    ResponseEntity<GenericResponse<String>> initiateJobApplication(ApplicationRequest request) throws UserNotAuthorizedException, UserNotFoundException;
    ResponseEntity<GenericResponse<String>> withdrawApplication(Long applicationId);

}

