package com.example.jobcrony.utilities;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobCronyMapper {
    private PasswordEncoder passwordEncoder;

    public Employer map(EmployerRegistrationRequest request, Company company){
        return Employer.builder()
                .email(request.getEmail())
                .roles(request.getRoles())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .company(company)
                .build();
    }

    public JobSeeker map(JobSeekerRegistrationRequest request, String email){
        return JobSeeker.builder()
                .profilePicture(request.getProfilePicture())
                .email(email)
                .roles(request.getRoles())
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

}
