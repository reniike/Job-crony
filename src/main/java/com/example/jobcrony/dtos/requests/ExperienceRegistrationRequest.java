package com.example.jobcrony.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@Setter
public class ExperienceRegistrationRequest {
    private String companyName;
    private String jobTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
