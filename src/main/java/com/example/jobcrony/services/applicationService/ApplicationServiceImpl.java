package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.Application;
import com.example.jobcrony.data.repositories.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository repository;
    @Override
    public List<Application> saveApplications(List<Application> applications) {
        return repository.saveAll(applications);
    }
}
