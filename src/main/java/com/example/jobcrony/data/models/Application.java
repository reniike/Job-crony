package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private JobSeeker jobSeeker;

    @ManyToOne
    private JobOpening jobOpening;

    private String resume;

    @ManyToMany
    private List<Skill> skills;

    @ManyToMany
    private List<Education> educationList;

    @ManyToMany
    private List<Experience> experiences;

    private String coverLetter;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private final LocalDateTime applicationDate = LocalDateTime.now();


}
