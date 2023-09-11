package com.example.jobcrony.services.employerService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.data.repositories.EmployerRepository;
import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.dtos.responses.EmployerResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.companyService.CompanyService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.mailService.MailService;
import com.example.jobcrony.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private LocationService locationService;
    private ModelMapper modelMapper;
    private CompanyService companyService;
    private JwtUtility jwtUtility;
    private MailService mailService;

    @Override
    public ResponseEntity<GenericResponse<String>> register(EmployerRegistrationRequest request) throws CompanyNotFoundException, LimitExceededException {
        ResponseEntity<GenericResponse<String>> response;
        String code = request.getCompany().getCompanyCode();
        Company company;
        if (code != null){
            company = companyService.findByCompanyCode(code).orElseThrow(() -> new CompanyNotFoundException(COMPANY_DOESNT_EXIST));
        } else {
            company = companyService.createCompany(request.getCompany());
        }
        Employer employer = modelMapper.map(request, Employer.class);
        employer.setPassword(passwordEncoder.encode(request.getPassword()));
        employer.setCompany(company);
        repository.save(employer);
        sendEmployerWithExistingCompanyWelcomeMail(employer);
        JobCronyUserDetails jobCronyUserDetails = new JobCronyUserDetails(employer);
        String token = jwtUtility.generateToken(employer.getRoles(), jobCronyUserDetails);
        return ResponseEntity.ok().body(GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(ACCOUNT_SUCCESSFULLY_CREATED)
                .data(token)
                .build());
    }

    private void sendEmployerWithExistingCompanyWelcomeMail(Employer employer) {
        SendMailRequest mailRequest = SendMailRequest.builder()
                .to(employer.getEmail())
                .from(SYSTEM_MAIL)
                .subject(WELCOME_TO_JOB_CRONY)
                .text(String.format(EMPLOYER_WELCOME_MAIL, SYSTEM_MAIL))
                .build();
        sendMail(mailRequest);
    }

    private void sendMail(SendMailRequest sendMailRequest) {
        mailService.sendMail(sendMailRequest);
    }

    private void sendEmployerWelcomeEmail(String email) {
        SendMailRequest mailRequest = SendMailRequest.builder()
                .text(String.format(EMPLOYER_WELCOME_MAIL, SYSTEM_MAIL))
                .subject(WELCOME_TO_JOB_CRONY)
                .from(SYSTEM_MAIL)
                .to(email)
                .build();
        sendMail(mailRequest);
    }

    @Override
    public EmployerResponse findByEmail(String email) {
        return null;
    }

}
