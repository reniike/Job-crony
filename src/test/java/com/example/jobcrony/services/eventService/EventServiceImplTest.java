package com.example.jobcrony.services.eventService;

import com.example.jobcrony.data.models.EventType;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.dtos.requests.EventCreationRequest;
import com.example.jobcrony.dtos.requests.LocationRequest;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventServiceImplTest {

    @Autowired
    private EventService eventService;

    @Test
    @WithMockUser(username = "yagnugordo@gufum.com", roles = "EMPLOYER")
    void createEvent() throws UserNotAuthorizedException {
        LocationRequest locationRequest = LocationRequest.builder()
                .city("Lagos")
                .country("Lagos")
                .postalCode("00000")
                .state("Lagos")
                .build();

        EventCreationRequest request = EventCreationRequest.builder()
                .location(locationRequest)
                .description("event")
                .eventType(EventType.VIRTUAL)
                .virtualLink("event link.com")
                .startDateAndTime(LocalDateTime.of(LocalDate.now(), LocalTime.NOON))
                 .build();

        var response = eventService.createEvent(request).getBody();
        assertNotNull(response);
    }
}