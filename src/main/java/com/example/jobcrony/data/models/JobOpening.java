package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class JobOpening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
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
    private boolean isVerified;
    private boolean isClosed;
    private final LocalDateTime datePosted = LocalDateTime.now();
}

