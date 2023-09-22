package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.repositories.JobSeekerRepository;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserAlreadyExistException;
import com.example.jobcrony.exceptions.VerificationFailedException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.educationService.EducationService;
import com.example.jobcrony.services.experienceService.ExperienceService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.tokenService.TokenService;
import com.example.jobcrony.utilities.JobCronyMapper;
import com.example.jobcrony.utilities.JwtUtility;
import com.example.jobcrony.utilities.MailUtility;
import com.example.jobcrony.utilities.validations.JobSeekerValidation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService {
    private ModelMapper modelMapper;
    private final PreRegistrationRepository preRegistrationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private EducationService educationService;
    private ExperienceService experienceService;
    private MailUtility mailUtility;
    private JwtUtility jwtUtility;
    private LocationService locationService;
    private JobSeekerValidation validation;
    private JobCronyMapper mapper;
    private TokenService tokenService;


    @Override
    public ResponseEntity<GenericResponse<String>> initiateRegistration(PreRegistrationRequest request) throws UserAlreadyExistException, SendMailException {
        tokenService.deletePreviousTokens(request.getEmailAddress());
        validation.validateEmailAddress(request.getEmailAddress());

        JobSeekerPreRegistration jobSeekerPreRegistration = new JobSeekerPreRegistration();
        jobSeekerPreRegistration.setEmail(request.getEmailAddress());

        String token = jwtUtility.generateToken(jobSeekerPreRegistration.getEmail());
        jobSeekerPreRegistration.setToken(token);

        String magicLink = JOBSEEKER_COMPLETE_REGISTRATION_PAGE_URL + token;

        mailUtility.sendJobSeekerWelcomeMail(request.getEmailAddress(), magicLink, SYSTEM_MAIL);

        preRegistrationRepository.save(jobSeekerPreRegistration);

        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setMessage(EMAIL_SENT_SUCCESSFULLY);
        return ResponseEntity.ok().body(genericResponse);
    }

    @Override
    public ResponseEntity<GenericResponse<String>> completeRegistration(JobSeekerRegistrationRequest request) throws VerificationFailedException {
        JobSeekerPreRegistration preRegistration = preRegistrationRepository.findJobSeekerPreRegistrationByToken(request.getToken())
                .orElseThrow(() -> new VerificationFailedException(VERIFICATION_FAILED));

        JobSeeker jobSeeker = mapper.map(request, preRegistration.getEmail());
        jobSeekerRepository.saveAndFlush(jobSeeker);

        Location location = modelMapper.map(request.getLocation(), Location.class);
        location.setUser(jobSeeker);
        locationService.save(location);
        experienceService.save(request.getExperienceList(), jobSeeker);
        educationService.save(request.getEducationList(), jobSeeker);

        preRegistrationRepository.delete(preRegistration);

        JobCronyUserDetails jobCronyUserDetails = new JobCronyUserDetails(jobSeeker);
        String jwtToken = jwtUtility.generateToken(jobSeeker.getRoles(), jobCronyUserDetails);
        GenericResponse<String> genericResponse = GenericResponse.<String>builder()
                .data(jwtToken)
                .status(HTTP_STATUS_OK)
                .message(ACCOUNT_SUCCESSFULLY_CREATED)
                .build();
        return ResponseEntity.ok().body(genericResponse);
    }
}
