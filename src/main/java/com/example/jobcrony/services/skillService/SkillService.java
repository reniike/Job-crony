package com.example.jobcrony.services.skillService;

import com.example.jobcrony.data.models.Skill;
import com.example.jobcrony.dtos.responses.GenericResponse;
import org.springframework.http.ResponseEntity;

import java.awt.geom.GeneralPath;
import java.util.List;

public interface SkillService {
    List<Skill>  saveSkills(List<Skill> skills);
}
