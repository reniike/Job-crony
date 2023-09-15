package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventById(Long id);
}
