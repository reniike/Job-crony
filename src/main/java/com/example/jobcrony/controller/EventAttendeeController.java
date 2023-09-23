package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.EventAttendeeRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.EventDoesntExistException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.services.eventAttendeeService.EventAttendeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/eventAttendee")
public class EventAttendeeController {
    private EventAttendeeService attendeeService;

    @PostMapping("/attendEvent")
    public ResponseEntity<GenericResponse<String>> registerAttendee(@RequestBody EventAttendeeRegistrationRequest request) throws EventDoesntExistException, SendMailException {
        return attendeeService.registerEventAttendee(request);
    }
}
