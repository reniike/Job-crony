package com.example.jobcrony.data.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class JobOpening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;
    private Long employerId;
    private String jobTitle;
    private String jobDescription;
    private String experience;
    @Enumerated(EnumType.STRING)
    private JobStyle jobStyle;
    @OneToMany
    private List<Skill> requiredSkills;
    @OneToMany
    private List<Application> applications;
    private String yearsOfExperience;
    private boolean isVerified;
    private boolean isClosed;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime datePosted = LocalDateTime.now();
}

