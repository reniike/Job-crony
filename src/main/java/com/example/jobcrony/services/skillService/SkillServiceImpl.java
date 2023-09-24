package com.example.jobcrony.services.skillService;

import com.example.jobcrony.data.models.Skill;
import com.example.jobcrony.data.repositories.SkillRepository;
import com.example.jobcrony.dtos.responses.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements SkillService{
    private final SkillRepository repository;
    @Override
    public List<Skill> saveSkills(List<Skill> skills) {
        return repository.saveAll(skills);
    }
}
