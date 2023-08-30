package com.example.jobcrony.services.employerService;

import com.example.jobcrony.dtos.responses.EmployerResponse;

public interface EmployerService {
    EmployerResponse findByEmail(String email);

}
