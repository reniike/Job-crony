package com.example.jobcrony.services.eventAttendeeService;

import com.example.jobcrony.data.models.EventAttendee;
import com.example.jobcrony.dtos.responses.EventAttendeeResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


public interface EventAttendeeService {
    EventAttendeeResponse findByEmail(String email);

}
