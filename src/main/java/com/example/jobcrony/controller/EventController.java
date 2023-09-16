package com.example.jobcrony.controller;

import com.example.jobcrony.dtos.requests.EventCreationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.services.eventService.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {
    private EventService eventService;

    @PostMapping("/createEvent")
    public ResponseEntity<GenericResponse<String>> createEvent(@RequestBody EventCreationRequest request) throws UserNotAuthorizedException {
        return eventService.createEvent(request);
    }
}
