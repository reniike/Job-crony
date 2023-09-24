package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.Application;

import java.util.List;

public interface ApplicationService {

    List<Application> saveApplications(List<Application> applications);
}
