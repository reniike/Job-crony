package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("EMPLOYER")
public class Employer extends User{
    @ManyToOne
    private Company company;
    @OneToMany
    private List<JobOpening> jobOpenings;
    @OneToOne
    private Location location;
    @OneToMany
    private List<Application> applicationList;

}
