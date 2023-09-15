package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.EventType;
import com.example.jobcrony.data.models.Location;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class EventRegistrationRequest {
    private Long id;
    private String title;
    private String description;
    private Location location;
    private String virtualLink;
    private EventType eventType;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;
}
