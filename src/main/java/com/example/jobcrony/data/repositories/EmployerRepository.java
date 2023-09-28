package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
    int countEmployersByCompany_Id(Long companyId);
}
