package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventAttendeeRegistrationRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Long eventId;
}
