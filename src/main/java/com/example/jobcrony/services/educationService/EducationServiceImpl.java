package com.example.jobcrony.services.educationService;

import com.example.jobcrony.data.models.Education;
import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.data.repositories.EducationRepository;
import com.example.jobcrony.dtos.requests.EducationRegistrationRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService{
    private ModelMapper mapper;
    private EducationRepository repository;

    @Override
    public void save(List<EducationRegistrationRequest> request, JobSeeker jobSeeker) {
        List<Education> educations = request.stream().map(educationRegistrationRequest -> {
            Education education = mapper.map(educationRegistrationRequest, Education.class);
            education.setUser(jobSeeker);
            return education;
        }).toList();
        repository.saveAll(educations);
    }
}
