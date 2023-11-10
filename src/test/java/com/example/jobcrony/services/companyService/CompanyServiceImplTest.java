package com.example.jobcrony.services.companyService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.requests.LocationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.CompanyExistsException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.services.locationService.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyServiceImplTest {
    @Autowired
    private CompanyService companyService;

    @Test
    void createCompany() throws CompanyExistsException, SendMailException {
        LocationRequest locationRequest = LocationRequest.builder()
                .city("Lagos")
                .country("Lagos")
                .postalCode("00000")
                .state("Lagos")
                .build();

        CompanyRegistrationRequest request = CompanyRegistrationRequest.builder()
                .companyName("Cultify")
                .email("renikecodes@gmail.com")
                .companyDescription("Farm investment platform")
                .website("cultify.com")
                .companyLogo("cultify logo")
                .industry("fintech")
                .location(locationRequest)
                .numberOfEmployees("9-67")
                .build();

        Company company =  companyService.createCompany(request);
        assertNotNull(company.getId());
    }
}