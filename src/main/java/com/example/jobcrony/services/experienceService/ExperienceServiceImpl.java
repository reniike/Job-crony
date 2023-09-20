package com.example.jobcrony.services.experienceService;

import com.example.jobcrony.data.models.Experience;
import com.example.jobcrony.data.models.User;
import com.example.jobcrony.data.repositories.ExperienceRepository;
import com.example.jobcrony.dtos.requests.ExperienceRegistrationRequest;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService{
    private ExperienceRepository repository;
    private ModelMapper mapper;

    @Override
    public void save(List<ExperienceRegistrationRequest> request, User user) {
        List<Experience> experiences = request.stream().map(ex -> {
            Experience experience = mapper.map(ex, Experience.class);
            experience.setUser(user);
            return experience;
        }).toList();
        repository.saveAll(experiences);
    }
}
