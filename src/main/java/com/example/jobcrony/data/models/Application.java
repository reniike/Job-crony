package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long employerId;

    private Long jobSeekerId;

    @ManyToOne
    private JobOpening jobOpening;

    private String resume;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    private LocalDateTime applicationDate = LocalDateTime.now();
}
