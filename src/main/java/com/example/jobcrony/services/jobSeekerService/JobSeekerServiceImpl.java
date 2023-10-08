package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.repositories.JobSeekerRepository;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.LocationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.requests.UpdateProfileRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.educationService.EducationService;
import com.example.jobcrony.services.experienceService.ExperienceService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.tokenService.TokenService;
import com.example.jobcrony.utilities.AuthenticationUtils;
import com.example.jobcrony.utilities.JobCronyMapper;
import com.example.jobcrony.utilities.JwtUtility;
import com.example.jobcrony.utilities.MailUtility;
import com.example.jobcrony.utilities.validations.JobSeekerValidation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
@Transactional
public class JobSeekerServiceImpl implements JobSeekerService {
    private ModelMapper modelMapper;
    private final PreRegistrationRepository preRegistrationRepository;
    private final JobSeekerRepository jobSeekerRepository;
    private final EducationService educationService;
    private final ExperienceService experienceService;
    private MailUtility mailUtility;
    private AuthenticationUtils authUtils;
    private JwtUtility jwtUtility;
    private LocationService locationService;
    private JobSeekerValidation validation;
    private JobCronyMapper mapper;
    private TokenService tokenService;


    @Override
    public ResponseEntity<GenericResponse<String>> initiateRegistration(PreRegistrationRequest request) throws UserAlreadyExistException, SendMailException, CompanyNotFoundException {
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
        JobSeekerPreRegistration preRegistration = validateTokenAndGetPreRegistration(request.getToken());

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

    @Override
    public ResponseEntity<GenericResponse<String>> updateProfile(UpdateProfileRequest request) throws UserNotAuthorizedException {
        if (!authUtils.isRole(Role.JOB_SEEKER)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);

        JobSeeker foundJobSeeker = jobSeekerRepository.findJobSeekerByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException(NOT_FOUND));
        foundJobSeeker.setFirstName(request.getFirstName());
        foundJobSeeker.setLastName(request.getLastName());
        foundJobSeeker.setPhoneNumber(request.getPhoneNumber());
        foundJobSeeker.setResume(request.getResume());
        foundJobSeeker.setProfilePicture(request.getProfilePicture());
        foundJobSeeker.setSkills(request.getSkills());

        Location location = mapper.map(foundJobSeeker, request.getLocation());
        locationService.save(location);
        experienceService.save(request.getExperienceList(), foundJobSeeker);
        educationService.save(request.getEducationList(), foundJobSeeker);

        GenericResponse<String> genericResponse = GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(PROFILE_UPDATED_SUCCESSFULLY)
                .build();
        return ResponseEntity.ok().body(genericResponse);
    }

    private JobSeekerPreRegistration validateTokenAndGetPreRegistration(String token) throws VerificationFailedException {
        return preRegistrationRepository.findJobSeekerPreRegistrationByToken(token)
                .orElseThrow(() -> new VerificationFailedException(VERIFICATION_FAILED));
    }
}
