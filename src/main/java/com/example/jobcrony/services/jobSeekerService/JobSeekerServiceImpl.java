package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.data.repositories.JobSeekerRepository;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.requests.UpdateProfileRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.*;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.educationService.EducationService;
import com.example.jobcrony.services.experienceService.ExperienceService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.skillService.SkillService;
import com.example.jobcrony.services.tokenService.TokenService;
import com.example.jobcrony.utilities.AuthenticationUtils;
import com.example.jobcrony.utilities.JobCronyMapper;
import com.example.jobcrony.utilities.JwtUtility;
import com.example.jobcrony.utilities.MailUtility;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
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
    private JobCronyMapper mapper;
    private TokenService tokenService;
    private SkillService skillService;


    @Override
    public ResponseEntity<GenericResponse<String>> initiateRegistration(PreRegistrationRequest request) throws UserAlreadyExistException, SendMailException, CompanyNotFoundException {
        validateEmail(request.getEmailAddress());
        tokenService.deletePreviousTokens(request.getEmailAddress());

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

    private void validateEmail(String emailAddress) throws UserAlreadyExistException {
        if (jobSeekerRepository.findJobSeekerByEmail(emailAddress).isPresent()) throw new UserAlreadyExistException(USER_ALREADY_EXIST);
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
        JobSeeker jobSeeker = (JobSeeker) authUtils.getCurrentUser();
        JobSeeker foundJobSeeker = jobSeekerRepository.findJobSeekerByEmail(jobSeeker.getEmail()).orElseThrow(() -> new NotFoundException(NOT_FOUND));
        foundJobSeeker.setFirstName(request.getFirstName());
        foundJobSeeker.setLastName(request.getLastName());
        foundJobSeeker.setPhoneNumber(request.getPhoneNumber());
        foundJobSeeker.setResume(request.getResume());
        foundJobSeeker.setProfilePicture(request.getProfilePicture());

        List<Skill> skills = skillService.saveSkills(request.getSkills());
        foundJobSeeker.setSkills(skills);

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

    @Override
    public JobSeeker getJobSeekerById(Long id) throws UserNotAuthorizedException {
        if (!authUtils.isRole(Role.EMPLOYER)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        return jobSeekerRepository.findJobSeekerById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    @Override
    public JobSeeker getJobSeeker() {
        JobSeeker jobSeeker = (JobSeeker) authUtils.getCurrentUser();
        return jobSeekerRepository.findJobSeekerByEmail(jobSeeker.getEmail()).orElseThrow(()-> new NotFoundException(NOT_FOUND));
    }

    private JobSeekerPreRegistration validateTokenAndGetPreRegistration(String token) throws VerificationFailedException {
        return preRegistrationRepository.findJobSeekerPreRegistrationByToken(token)
                .orElseThrow(() -> new VerificationFailedException(VERIFICATION_FAILED));
    }
}
