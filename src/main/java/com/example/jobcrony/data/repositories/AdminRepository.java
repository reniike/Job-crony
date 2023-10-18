package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Admin;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmail(String email);
    List<Admin> findAll();
}
