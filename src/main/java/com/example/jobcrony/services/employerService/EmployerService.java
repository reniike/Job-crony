package com.example.jobcrony.services.employerService;

import com.example.jobcrony.dtos.responses.EmployerResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import org.springframework.http.ResponseEntity;

public interface EmployerService {
    EmployerResponse findByEmail(String email);

}
