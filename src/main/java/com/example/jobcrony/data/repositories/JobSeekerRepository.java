package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {
}
