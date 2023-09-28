package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.data.repositories.ApplicationRepository;
import com.example.jobcrony.dtos.requests.ApplicationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.employerService.EmployerService;
import com.example.jobcrony.services.jobOpeningService.JobOpeningService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository repository;
    private final JobOpeningService jobOpeningService;


    @Override
    public ResponseEntity<GenericResponse<String>> initiateJobApplication(ApplicationRequest request) throws UserNotAuthorizedException, UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JobCronyUserDetails userDetails = (JobCronyUserDetails) authentication.getPrincipal();

        if (!userDetails.getUser().getRoles().contains(Role.JOB_SEEKER)){
            throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        }
        JobOpening jobOpening = jobOpeningService.findJobOpening(request.getJobOpening());
        JobSeeker jobSeeker = (JobSeeker) userDetails.getUser();

        Application application = Application.builder()
                .jobSeeker(jobSeeker)
                .jobOpening(jobOpening)
                .resume(request.getResume())
                .skills(jobSeeker.getSkills())
                .experiences(jobSeeker.getExperienceList())
                .educationList(jobSeeker.getEducationList())
                .coverLetter(request.getCoverLetter())
                .build();

        repository.save(application);

        GenericResponse<String> response = GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(APPLICATION_SENT_SUCCESSFULLY)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public List<Application> saveApplications(List<Application> applications) {
        return repository.saveAll(applications);
    }

}
