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
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
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
