package com.example.jobcrony.services.employerService;

import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.responses.EmployerResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.CompanyExistsException;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import com.example.jobcrony.exceptions.SendMailException;
import org.springframework.http.ResponseEntity;

public interface EmployerService {
    ResponseEntity<GenericResponse<String>> register(EmployerRegistrationRequest request) throws CompanyNotFoundException, LimitExceededException, CompanyExistsException, SendMailException;
    int countEmployersByCompanyId(Long companyId);
}
