package com.example.jobcrony.dtos.requests;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ApplicationRequest {
    private String resume;
    private String coverLetter;
    private Long jobOpeningId;
}
