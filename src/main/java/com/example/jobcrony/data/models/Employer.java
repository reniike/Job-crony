package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @ManyToOne
    private Company company;
    private String password;
    @OneToMany
    private List<JobOpening> jobOpenings;
    @OneToOne
    private Location location;
    @OneToMany
    private List<Application> applicationList;

}
