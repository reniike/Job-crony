package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.ExperienceLevel;
import com.example.jobcrony.data.models.JobStyle;
import com.example.jobcrony.data.models.Skill;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class JobOpeningRequest {
    private String jobTitle;
    private String jobDescription;
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    private JobStyle jobStyle;

    private List<Skill> requiredSkills;

    private String yearsOfExperience;
}
