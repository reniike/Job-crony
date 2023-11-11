package com.example.jobcrony.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendeeRegistrationRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Long eventId;
}
