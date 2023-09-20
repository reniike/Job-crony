package com.example.jobcrony.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class EducationRegistrationRequest {
    private String schoolName;
    private String degreeName;
    private String fieldOfStudy;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
