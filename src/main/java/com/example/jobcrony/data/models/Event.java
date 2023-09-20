package com.example.jobcrony.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long employerId;
    private String title;
    private String description;
    @OneToOne
    private Location location;
    private String virtualLink;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
}
