package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
}
