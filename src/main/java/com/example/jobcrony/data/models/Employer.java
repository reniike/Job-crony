package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYER")
public class Employer extends User{
    @ManyToOne
    private Company company;

    @OneToMany
    private List<JobOpening> jobOpenings;

    @OneToOne(mappedBy = "user")
    private Location location;

    @OneToMany
    private List<Application> applicationList;

}
