package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("JOB_SEEKER")
public class JobSeeker extends User {
    private String profilePicture;
    private String resume;
    @OneToOne
    private Location location;
    @OneToMany
    private List<Education> educationList;
    @OneToMany
    private List<Experience> experienceList;
    @OneToMany
    private List<Skill> skills;
}
