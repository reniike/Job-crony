package com.example.jobcrony.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendeeRegistrationRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Long eventId;
}
