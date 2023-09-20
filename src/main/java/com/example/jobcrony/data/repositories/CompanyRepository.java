package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findCompanyByCompanyCode(String code);
    Optional<Company> findCompanyByCompanyEmail(String email);
//    Optional<Company>

}
