package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.dtos.responses.JobSeekerResponse;
import org.springframework.stereotype.Service;

public interface JobSeekerService {
    JobSeekerResponse findByEmail(String email);

}
