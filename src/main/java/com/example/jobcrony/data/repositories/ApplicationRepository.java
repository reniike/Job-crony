package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Application;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findApplicationsByJobOpening_Id(Long jobOpeningId);
    Optional<Application> findApplicationByJobOpening_Id(Long jobOpeningId);

    boolean existsByJobOpening_IdAndJobSeeker_Id(Long jobOpeningId, Long jobSeekerId);

}
