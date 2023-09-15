package com.example.jobcrony.services.employerService;

import com.example.jobcrony.data.repositories.EmployerRepository;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import com.example.jobcrony.services.companyService.CompanyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployerServiceImplTest {
    @Autowired
    private EmployerService employerService;
    private EmployerRegistrationRequest registrationRequest;
    private ResponseEntity<GenericResponse<String>> response;
    @Autowired
    private CompanyService companyService;
    private CompanyRegistrationRequest companyRegistrationRequest;

    @DisplayName("Employer registration test")
    @Test void  employerRegistrationTest() throws CompanyNotFoundException, LimitExceededException {
companyRegistrationRequest = CompanyRegistrationRequest.builder()
                .companyDescription("")
                .companyLogo("")
                .companyName("crony")
                .contactNumber("090")
                .website("")
                .industry("tech")
                .numberOfEmployees("10-45")
                .email("negibis726@gameszox.com")
                .location(null)
                .build();
       ResponseEntity<CompanyRegistrationResponse> companyRegistrationResponse = companyService.registerCompany(companyRegistrationRequest);
       assertNotNull(companyRegistrationResponse);
        registrationRequest = EmployerRegistrationRequest.builder()
                .company(CompanyRegistrationRequest.builder()
                        .companyCode("c1bbtyI1e972xygnC")
                        .build())
                .email("negibis726@gameszox.com")
                .roles(Collections.emptySet())
                .phoneNumber("090")
                .firstName("first")
                .lastName("last")
                .location(null)
                .password("eniola45@")
                .build();
        response = employerService.register(registrationRequest);
        assertNotNull(response);
    }
}