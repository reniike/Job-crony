package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
    Optional<JobSeeker> findJobSeekerByEmail(String email);

    Optional<JobSeeker> findJobSeekerById(Long id);

}
