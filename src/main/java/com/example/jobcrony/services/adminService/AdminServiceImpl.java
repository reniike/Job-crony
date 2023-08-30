package com.example.jobcrony.services.adminService;

import com.example.jobcrony.data.repositories.AdminRepository;
import com.example.jobcrony.dtos.responses.AdminResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;
    private ModelMapper modelMapper;
    @Override
    public AdminResponse findByEmail(String email) {
        return null;
    }
}
