package com.example.jobcrony.services.employerService;

import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import org.springframework.http.ResponseEntity;

public interface EmployerService {
    ResponseEntity<GenericResponse<String>> register(EmployerRegistrationRequest request) throws CompanyNotFoundException, LimitExceededException, CompanyExistsException, SendMailException, UserAlreadyExistException;
    int countEmployersByCompanyId(Long companyId);
    Employer findEmployer(Long employerId) throws UserNotFoundException;
}
