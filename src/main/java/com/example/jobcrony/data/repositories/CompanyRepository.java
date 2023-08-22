package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
