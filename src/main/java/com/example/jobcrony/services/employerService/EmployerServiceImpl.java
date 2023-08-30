package com.example.jobcrony.services.employerService;

import com.example.jobcrony.data.repositories.EmployerRepository;
import com.example.jobcrony.dtos.responses.EmployerResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployerServiceImpl implements EmployerService{
    private EmployerRepository employerRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployerResponse findByEmail(String email) {
        return null;
    }
}
