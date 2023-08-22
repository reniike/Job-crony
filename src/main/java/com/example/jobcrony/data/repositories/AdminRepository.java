package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
