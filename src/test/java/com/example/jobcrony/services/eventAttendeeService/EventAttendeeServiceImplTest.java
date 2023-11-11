package com.example.jobcrony.services.eventAttendeeService;

import com.example.jobcrony.dtos.requests.EventAttendeeRegistrationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventAttendeeServiceImplTest {

    @Autowired
    private EventAttendeeService service;

    @Test
    void registerEventAttendee() {
        EventAttendeeRegistrationRequest request = EventAttendeeRegistrationRequest.builder()
                .build();
    }
}