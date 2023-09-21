package com.example.jobcrony.utilities.validations;

import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.jobcrony.utilities.AppUtils.USER_ALREADY_EXIST;

@Component
@AllArgsConstructor
public class JobSeekerValidation {
    private PreRegistrationRepository preRegistrationRepository;
    public void validateEmailAddress(String emailAddress) throws UserAlreadyExistException {
        if (preRegistrationRepository.findJobSeekerPreRegistrationByEmail(emailAddress).isPresent()){
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
        }
    }
}
