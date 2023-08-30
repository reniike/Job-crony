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
    private Long companyCode;
    private String companyName;
    private String companyEmail;
    private String companyWebsiteUrl;
    private String companyIndustry;
}
