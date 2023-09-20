package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreRegistrationRepository extends JpaRepository<JobSeekerPreRegistration, Long> {
    Optional<JobSeekerPreRegistration> findJobSeekerPreRegistrationByToken(String token);
    Optional<JobSeekerPreRegistration> findJobSeekerPreRegistrationByEmail(String email);
}
