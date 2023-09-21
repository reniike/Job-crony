package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String  companyCode;

    private String companyName;

    @Column(unique = true)
    private String companyEmail;
    private String companyWebsiteUrl;
    private String companyIndustry;

    @OneToOne
    private Location location;

    private String companyDescription;
    private String companyLogo;
    private String numberOfEmployees;
    private String contactNumber;
}

