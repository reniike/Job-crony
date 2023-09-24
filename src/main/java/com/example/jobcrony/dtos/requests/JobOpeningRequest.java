package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class JobOpeningRequest {
    private Employer employer;
    private String jobTitle;
    private String jobDescription;
    private ExperienceLevel experienceLevel;

    @Enumerated(EnumType.STRING)
    private JobStyle jobStyle;

    @OneToMany
    private List<Skill> requiredSkills;

    @OneToMany
    private List<Application> applications;

    private String yearsOfExperience;
}
