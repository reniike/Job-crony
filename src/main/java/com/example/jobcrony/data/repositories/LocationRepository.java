package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
