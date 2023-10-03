package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Application;
import com.example.jobcrony.data.models.JobOpening;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobOpeningRepository extends JpaRepository<JobOpening, Long> {
}
