package com.example.jobcrony.services.companyService;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.data.repositories.CompanyRepository;
import com.example.jobcrony.dtos.requests.CompanyRegistrationRequest;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.dtos.responses.CompanyRegistrationResponse;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.LimitExceededException;
import com.example.jobcrony.services.mailService.MailService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private CompanyRepository repository;
    private ModelMapper mapper;
    private MailService mailService;

    @Override
    public Optional<Company> findByCompanyCode(String companyCode) throws CompanyNotFoundException {
        return repository.findCompanyByCompanyCode(companyCode);
    }

    @Override
    public String generateCompanyUniqueCode(Company company) {
        boolean useLetters = true;
        boolean useNumbers = true;
        return RandomStringUtils.random(COMPANY_CODE_LENGTH, useLetters, useNumbers);
    }

    @Override
    public void validateCompany(String code) throws LimitExceededException, CompanyNotFoundException {
        Optional<Company> foundCompany = repository.findCompanyByCompanyCode(code);
        if (foundCompany.isEmpty()) throw new CompanyNotFoundException(COMPANY_DOESNT_EXIST);
        if(foundCompany.get().getMaxEmployees() >= THREE) throw new LimitExceededException(MAX_NUMBER_OF_EMPLOYERS_REACHED);
    }


    @Override
    public ResponseEntity<CompanyRegistrationResponse> registerCompany(CompanyRegistrationRequest request) {
        Company company = this.createCompany(request);
        return ResponseEntity.ok().body(CompanyRegistrationResponse.builder()
                        .company(company)
                        .message(COMPANY_REGISTERED_SUCCESSFULLY)
                .build());
    }

    @Override
    public Company createCompany(CompanyRegistrationRequest request) {
        Company company = mapper.map(request, Company.class);
        String companyCode = generateCompanyUniqueCode(company);
        company.setCompanyCode(companyCode);
        repository.save(company);
        sendCompanyWelcomeEmail(company);
        return company;
    }

    private void sendCompanyWelcomeEmail(Company company) {
        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .to(company.getCompanyEmail())
                .from(SYSTEM_MAIL)
                .text(String.format(COMPANY_CREATED_WELCOME_MAIL, company.getCompanyCode(), SYSTEM_MAIL))
                .subject(HELLO_THERE)
                .build();
        mailService.sendMail(sendMailRequest);
    }

}


