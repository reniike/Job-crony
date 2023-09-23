package com.example.jobcrony.services.companyService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.exceptions.CompanyExistsException;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import com.example.jobcrony.exceptions.SendMailException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CompanyService {
    ResponseEntity<CompanyRegistrationResponse> registerCompany(CompanyRegistrationRequest companyRegistrationRequest) throws CompanyExistsException, SendMailException;
    Company createCompany(CompanyRegistrationRequest request) throws CompanyExistsException, SendMailException;
    Company findByCompanyCode(String companyCode) throws CompanyNotFoundException;
    String generateCompanyUniqueCode(Company company);
    Company findByEmail(String emailAddress) throws CompanyNotFoundException;
}
