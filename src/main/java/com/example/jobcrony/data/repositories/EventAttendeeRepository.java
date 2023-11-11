package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.EventAttendee;
import com.example.jobcrony.dtos.responses.EventAttendeeResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
    Optional<EventAttendee> findEventAttendeeByEmail(String email);
}
