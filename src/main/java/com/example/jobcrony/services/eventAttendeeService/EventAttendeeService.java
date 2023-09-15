package com.example.jobcrony.services.eventAttendeeService;

import com.example.jobcrony.data.models.EventAttendee;
import com.example.jobcrony.dtos.requests.EventAttendeeRegistrationRequest;
import com.example.jobcrony.dtos.responses.EventAttendeeResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.EventDoesntExistException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface EventAttendeeService {
    EventAttendeeResponse findByEmail(String email);
    ResponseEntity<GenericResponse<String>> registerEventAttendee(EventAttendeeRegistrationRequest request) throws EventDoesntExistException;

}
