package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.EventType;
import com.example.jobcrony.data.models.Location;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationRequest {
    private Long id;
    private String title;
    private String description;
    private Location location;
    private String virtualLink;
    private EventType eventType;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;

}
