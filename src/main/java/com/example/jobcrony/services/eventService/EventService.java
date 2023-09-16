package com.example.jobcrony.services.eventService;

import com.example.jobcrony.data.models.Event;
import com.example.jobcrony.dtos.requests.EventCreationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.EventDoesntExistException;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import org.springframework.http.ResponseEntity;

public interface EventService {
    ResponseEntity<GenericResponse<String>> createEvent(EventCreationRequest request) throws UserNotAuthorizedException;
    Event findEventById(Long id) throws EventDoesntExistException;
}
