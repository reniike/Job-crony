package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@DiscriminatorValue("JOBSEEKER")
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
