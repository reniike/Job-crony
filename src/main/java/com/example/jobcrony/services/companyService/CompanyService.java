package com.example.jobcrony.services.companyService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CompanyService {
    Optional<Company> findByCompanyCode(String companyCode) throws CompanyNotFoundException;
    String generateCompanyUniqueCode(Company company);
    void validateCompany(String code) throws LimitExceededException, CompanyNotFoundException;
    ResponseEntity<CompanyRegistrationResponse> registerCompany(CompanyRegistrationRequest companyRegistrationRequest);

    Company createCompany(CompanyRegistrationRequest request);
}
