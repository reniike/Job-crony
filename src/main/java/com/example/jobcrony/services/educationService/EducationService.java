package com.example.jobcrony.services.educationService;

import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.dtos.requests.EducationRegistrationRequest;

import java.util.List;

public interface EducationService {
    void save(List<EducationRegistrationRequest> educationList, JobSeeker jobSeeker);
}
