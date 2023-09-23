package com.example.jobcrony.utilities.validations;

import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.exceptions.CompanyNotFoundException;
import com.example.jobcrony.exceptions.UserAlreadyExistException;
import com.example.jobcrony.services.companyService.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.jobcrony.utilities.AppUtils.USER_ALREADY_EXIST;

@Component
@AllArgsConstructor
public class JobSeekerValidation {
    private PreRegistrationRepository preRegistrationRepository;
    private CompanyService companyService;
    public void validateEmailAddress(String emailAddress) throws UserAlreadyExistException, CompanyNotFoundException {
        if (preRegistrationRepository.findJobSeekerPreRegistrationByEmail(emailAddress).isPresent() || companyService.findByEmail(emailAddress) != null){
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
        }
    }
}
