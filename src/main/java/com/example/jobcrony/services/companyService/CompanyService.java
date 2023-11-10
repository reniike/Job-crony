package com.example.jobcrony.services.companyService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.requests.UpdateCompanyDetailRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CompanyService {
    Company createCompany(CompanyRegistrationRequest request) throws CompanyExistsException, SendMailException;
    Company findByCompanyCode(String companyCode) throws CompanyNotFoundException;
    String generateCompanyUniqueCode(Company company);
    Optional<Company> findByEmail(String emailAddress) throws CompanyNotFoundException;
    ResponseEntity<GenericResponse<String>> updateCompanyDetails(UpdateCompanyDetailRequest request) throws CompanyNotFoundException, UserNotAuthorizedException;
}
