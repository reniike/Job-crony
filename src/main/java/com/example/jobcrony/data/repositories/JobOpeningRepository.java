package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {
}
