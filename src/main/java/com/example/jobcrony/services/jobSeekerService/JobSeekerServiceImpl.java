package com.example.jobcrony.services.jobSeekerService;

import com.example.jobcrony.data.models.JobSeeker;
import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.repositories.JobSeekerRepository;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.dtos.requests.JobSeekerRegistrationRequest;
import com.example.jobcrony.dtos.requests.PreRegistrationRequest;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserAlreadyExistException;
import com.example.jobcrony.exceptions.VerificationFailedException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.educationService.EducationService;
import com.example.jobcrony.services.experienceService.ExperienceService;
import com.example.jobcrony.services.locationService.LocationService;
import com.example.jobcrony.services.mailService.MailService;
import com.example.jobcrony.utilities.JwtUtility;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private MailService mailService;
    private JwtUtility jwtUtility;
    private LocationService locationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<GenericResponse<String>> initiateRegistration(PreRegistrationRequest request) throws UserAlreadyExistException {
        validateEmailAddress(request.getEmailAddress());
        JobSeekerPreRegistration jobSeekerPreRegistration = new JobSeekerPreRegistration();
        jobSeekerPreRegistration.setEmail(request.getEmailAddress());
        String token = jwtUtility.generateToken(jobSeekerPreRegistration.getEmail());
        jobSeekerPreRegistration.setToken(token);
        String magicLink = JOBSEEKER_COMPLETE_REGISTRATION_PAGE_URL + token;
        String text = String.format(JOBSEEKER_REGISTRATION_MAIL, magicLink, SYSTEM_MAIL);
        sendJobSeekerWelcomeMail(request.getEmailAddress(), text);
        preRegistrationRepository.save(jobSeekerPreRegistration);
        GenericResponse<String> genericResponse = new GenericResponse<>();
        genericResponse.setMessage(EMAIL_SENT_SUCCESSFULLY);
        return ResponseEntity.ok().body(genericResponse);
    }

    @Override
    public ResponseEntity<GenericResponse<String>> completeRegistration(JobSeekerRegistrationRequest request) throws VerificationFailedException {
        JobSeekerPreRegistration preRegistration = preRegistrationRepository.findJobSeekerPreRegistrationByToken(request.getToken())
                .orElseThrow(() -> new VerificationFailedException(VERIFICATION_FAILED));
        JobSeeker jobSeeker = JobSeeker.builder()
                .profilePicture(request.getProfilePicture())
                .email(preRegistration.getEmail())
                .roles(request.getRoles())
                .firstName(request.getFirstName())
                .password(passwordEncoder.encode(request.getPassword()))
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .build();
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
                .message(ACCOUNT_SUCCESSFULLY_CREATED)
                .build();
        return ResponseEntity.ok().body(genericResponse);
    }

    private void sendJobSeekerWelcomeMail(String emailAddress, String text) {
        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .text(text)
                .subject(MAGIC_LINK)
                .from(SYSTEM_MAIL)
                .to(emailAddress)
                .build();
        sendMail(sendMailRequest);
    }

    private void sendMail(SendMailRequest sendMailRequest) {
        mailService.sendMail(sendMailRequest);
    }

    private void validateEmailAddress(String emailAddress) throws UserAlreadyExistException {
        if (preRegistrationRepository.findJobSeekerPreRegistrationByEmail(emailAddress).isPresent())
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
    }

}
