package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
