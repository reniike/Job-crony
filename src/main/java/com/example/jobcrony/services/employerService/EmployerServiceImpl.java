package com.example.jobcrony.services.employerService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.repositories.EmployerRepository;
import com.example.jobcrony.dtos.requests.EmployerRegistrationRequest;
import com.example.jobcrony.dtos.responses.EmployerResponse;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.companyService.CompanyService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.mailService.MailService;
import com.example.jobcrony.utilities.JobCronyMapper;
import com.example.jobcrony.utilities.JwtUtility;
import com.example.jobcrony.utilities.MailUtility;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
@Slf4j
public class EmployerServiceImpl implements EmployerService {
    private final EmployerRepository repository;
    private LocationService locationService;
    private ModelMapper modelMapper;
    private CompanyService companyService;
    private JwtUtility jwtUtility;
    private MailUtility mailUtility;
    private JobCronyMapper mapper;
    @Override
    @Transactional
    public ResponseEntity<GenericResponse<String>> register(EmployerRegistrationRequest request) throws CompanyNotFoundException, LimitExceededException, CompanyExistsException, SendMailException {
        String code = request.getCompany().getCompanyCode();
        Company company;
        if (code != null){
            company = companyService.findByCompanyCode(code);
            validateCompany(company);
        } else {
            company = companyService.createCompany(request.getCompany());
        }

        Employer employer = mapper.map(request, company);
        repository.saveAndFlush(employer);

        mailUtility.sendEmployerWelcomeMail(employer);

        Location location = modelMapper.map(request.getLocation(), Location.class);
        location.setUser(employer);
        locationService.save(location);

        JobCronyUserDetails jobCronyUserDetails = new JobCronyUserDetails(employer);
        String token = jwtUtility.generateToken(employer.getRoles(), jobCronyUserDetails);
        return ResponseEntity.ok().body(GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(ACCOUNT_SUCCESSFULLY_CREATED)
                .data(token)
                .build());
    }

    @Override
    public int countEmployersByCompanyId(Long companyId) {
        return repository.countEmployersByCompany_Id(companyId);
    }

    @Override
    public Employer findEmployer(Long employerId) throws UserNotFoundException {
        return repository.findById(employerId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    private void validateCompany(Company company) throws LimitExceededException {
        int employerCount = repository.countEmployersByCompany_Id(company.getId());
        if(employerCount >= THREE) throw new LimitExceededException(MAX_NUMBER_OF_EMPLOYERS_REACHED);
    }

}
