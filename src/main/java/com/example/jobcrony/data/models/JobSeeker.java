package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("JOB_SEEKER")
public class JobSeeker extends User {
    private String profilePicture;
    private String resume;

    @OneToOne(mappedBy = "user")
    private Location location;

    @OneToMany
    private List<Education> educationList;

    @OneToMany(mappedBy = "user")
    private List<Experience> experienceList;

    @OneToMany
    private List<Skill> skills;
}
