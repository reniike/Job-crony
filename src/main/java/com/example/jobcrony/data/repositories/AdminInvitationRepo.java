package com.example.jobcrony.data.repositories;

import com.example.jobcrony.data.models.AdminInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminInvitationRepo extends JpaRepository<AdminInvitation, Long> {
    Optional<AdminInvitation> findAdminInvitationByEmailAndToken(String email, String token);
}
