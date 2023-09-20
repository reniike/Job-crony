package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder

public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private User user;
    private String schoolName;
    private String degreeName;
    private String fieldOfStudy;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
