package com.example.jobcrony.services.experienceService;

import com.example.jobcrony.data.models.Experience;
import com.example.jobcrony.data.models.User;
import com.example.jobcrony.dtos.requests.ExperienceRegistrationRequest;

import java.util.List;

public interface ExperienceService {
    void save(List<ExperienceRegistrationRequest> request, User user);
}
