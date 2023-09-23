package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.EventType;
import com.example.jobcrony.data.models.Location;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventCreationRequest {
    private Long employerId;
    private String title;
    private String description;
    private LocationRequest location;
    private String virtualLink;
    private EventType eventType;
    private LocalDateTime startDateAndTime;
    private LocalDateTime endDateAndTime;

}
