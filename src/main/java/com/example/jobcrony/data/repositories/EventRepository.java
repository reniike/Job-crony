package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
