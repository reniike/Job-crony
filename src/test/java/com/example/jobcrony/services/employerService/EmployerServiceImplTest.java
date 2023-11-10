package com.example.jobcrony.services.employerService;

import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.requests.LocationRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
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
    @Test
    void employerRegistrationTest() throws CompanyNotFoundException, LimitExceededException, CompanyExistsException, SendMailException, UserAlreadyExistException {
        LocationRequest locationRequest = LocationRequest.builder()
                .city("Lagos")
                .country("Lagos")
                .postalCode("00000")
                .state("Lagos")
                .build();

        registrationRequest = EmployerRegistrationRequest.builder()
                .company(CompanyRegistrationRequest.builder()
                        .companyCode("5xRWJBTn7ikwvnUZn")
                        .build())
                .email("negibis726@gameszox.com")
                .roles(Collections.emptySet())
                .phoneNumber("090")
                .firstName("first")
                .lastName("last")
                .location(locationRequest)
                .password("eniola45@")
                .build();
        response = employerService.register(registrationRequest);
        assertNotNull(response);
    }
}