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
public class JobOpening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
    private Long employerId;
    private String jobTitle;
    private String jobDescription;
    private String experience;
    @Enumerated(EnumType.STRING)
    private JobStyle jobStyle;
    @OneToMany
    private List<Skill> requiredSkills;
    @OneToMany
    private List<Application> applications;
    private String yearsOfExperience;
    private boolean isVerified;
    private boolean isClosed;
    private LocalDateTime datePosted = LocalDateTime.now();
}

