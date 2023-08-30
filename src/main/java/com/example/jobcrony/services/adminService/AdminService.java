package com.example.jobcrony.services.adminService;

import com.example.jobcrony.dtos.responses.AdminResponse;
import org.springframework.stereotype.Service;

public interface AdminService {
    AdminResponse findByEmail(String email);
}
