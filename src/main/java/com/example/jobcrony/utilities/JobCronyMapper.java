package com.example.jobcrony.utilities;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.dtos.requests.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobCronyMapper {
    private PasswordEncoder passwordEncoder;

    public Employer map(EmployerRegistrationRequest request, Company company) {
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

    public JobSeeker map(JobSeekerRegistrationRequest request, String email) {
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

    public Application map(ApplicationRequest request, JobOpening jobOpening, JobSeeker jobSeeker) {
        return Application.builder()
                .jobSeeker(jobSeeker)
                .jobOpening(jobOpening)
                .resume(request.getResume())
                .skills(jobSeeker.getSkills())
                .experiences(jobSeeker.getExperienceList())
                .educationList(jobSeeker.getEducationList())
                .coverLetter(request.getCoverLetter())
                .applicationStatus(ApplicationStatus.PENDING)
                .build();
    }

    public void map(Company foundCompany, UpdateCompanyDetailRequest request) {
        foundCompany.setCompanyDescription(request.getCompanyDescription());
        foundCompany.setCompanyName(request.getCompanyName());
        foundCompany.setCompanyLogo(request.getCompanyLogo());
        foundCompany.setCompanyIndustry(request.getIndustry());
        foundCompany.setContactNumber(request.getContactNumber());
        foundCompany.setNumberOfEmployees(request.getNumberOfEmployees());
        foundCompany.setCompanyWebsiteUrl(request.getWebsite());
    }


    public void map(LocationRequest requestLocation, Company foundCompany) {
        Location location = foundCompany.getLocation();
        location.setCity(requestLocation.getCity());
        location.setCountry(requestLocation.getCountry());
        location.setPostalCode(requestLocation.getPostalCode());
        location.setState(requestLocation.getState());
        location.setCompany(foundCompany);
    }
}
