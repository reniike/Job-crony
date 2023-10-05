package com.example.jobcrony.services.companyService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.repositories.CompanyRepository;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.requests.LocationRequest;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.dtos.requests.UpdateCompanyDetailRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.employerService.EmployerService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.mailService.MailService;
import com.example.jobcrony.utilities.JobCronyMapper;
import com.example.jobcrony.utilities.MailUtility;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository repository;
    private ModelMapper mapper;
    private JobCronyMapper cronyMapper;
    private MailUtility mailUtility;
    private LocationService locationService;

    @Override
    public Company createCompany(CompanyRegistrationRequest request) throws SendMailException, CompanyExistsException {
        validateCompany(request.getEmail());
        Company company = mapper.map(request, Company.class);
        String companyCode = generateCompanyUniqueCode(company);
        company.setCompanyCode(companyCode);

        Location location = mapper.map(request.getLocation(), Location.class);
        location.setCompany(company);
        locationService.save(location);

        company.setLocation(location);
        repository.save(company);

        mailUtility.sendCompanyWelcomeEmail(company);
        return company;
    }

    private void validateCompany(String email) throws CompanyExistsException {
        if (repository.findCompanyByCompanyEmail(email).isPresent()) throw new CompanyExistsException(COMPANY_EXISTS);
    }


    @Override
    public Company findByCompanyCode(String companyCode) throws CompanyNotFoundException {
        return repository.findCompanyByCompanyCode(companyCode).orElseThrow(() -> new CompanyNotFoundException(COMPANY_DOESNT_EXIST));
    }

    @Override
    public String generateCompanyUniqueCode(Company company) {
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(COMPANY_CODE_LENGTH, useLetters, useNumbers);
    }

    @Override
    public Company findByEmail(String emailAddress) throws CompanyNotFoundException {
        return repository.findCompanyByCompanyEmail(emailAddress).get();
    }

    @Override
    public ResponseEntity<GenericResponse<String>> updateCompanyDetails(UpdateCompanyDetailRequest request) throws CompanyNotFoundException, UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JobCronyUserDetails userDetails = (JobCronyUserDetails) authentication.getPrincipal();

        if (!userDetails.getUser().getRoles().contains(Role.EMPLOYER)){
            throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        }
        Employer employer = (Employer) userDetails.getUser();
        log.info("1");
        Company foundCompany = findByCompanyCode(employer.getCompany().getCompanyCode());
        log.info("2");
        log.info(foundCompany.toString());
        foundCompany.setCompanyDescription(request.getCompanyDescription());
        foundCompany.setCompanyName(request.getCompanyName());
        foundCompany.setCompanyLogo(request.getCompanyLogo());
        foundCompany.setCompanyIndustry(request.getIndustry());
        foundCompany.setContactNumber(request.getContactNumber());
        foundCompany.setNumberOfEmployees(request.getNumberOfEmployees());
        foundCompany.setCompanyWebsiteUrl(request.getWebsite());
        log.info("3");

        System.out.println("This is the request " + request);

        LocationRequest requestedLocation = request.getLocation();
        Location location = Location.builder()
                .postalCode(requestedLocation.getPostalCode())
                .state(requestedLocation.getState())
                .city(requestedLocation.getCity())
                .country(requestedLocation.getCountry())
                .build();
//        Location location = mapper.map(request.getLocation(), Location.class);

        log.info("4");
        location.setCompany(foundCompany);

        log.info("5");
        Location savedLocation = locationService.save(location);
        foundCompany.setLocation(savedLocation);
        log.info("6");
        repository.save(foundCompany);
        log.info("7");

        GenericResponse<String> genericResponse = GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(COMPANY_DETAILS_UPDATED_SUCCESSFULLY)
                .build();
        log.info("8");
        return ResponseEntity.ok().body(genericResponse);
    }


}


