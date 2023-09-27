package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.JobSeeker;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ApplicationRequest {
    private JobSeeker jobSeeker;
    private String resume;
}
