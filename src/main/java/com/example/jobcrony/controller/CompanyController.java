package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.UpdateCompanyDetailRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.services.companyService.CompanyService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/job-crony/company")
public class CompanyController {

    private final CompanyService companyService;

    @PutMapping("/updateCompanyDetails")
    public ResponseEntity<GenericResponse<String>> updateCompanyDetails(@RequestBody UpdateCompanyDetailRequest request) throws CompanyNotFoundException, UserNotAuthorizedException {
        System.out.println("Received request" + request);
        return companyService.updateCompanyDetails(request);
    }
}
