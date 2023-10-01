package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.data.repositories.ApplicationRepository;
import com.example.jobcrony.dtos.requests.ApplicationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.exceptions.UserNotFoundException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.jobOpeningService.JobOpeningService;
import com.example.jobcrony.utilities.MailUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository repository;
    private final JobOpeningService jobOpeningService;
    private final MailUtility mailUtility;


    @Override
    public ResponseEntity<GenericResponse<String>> initiateJobApplication(ApplicationRequest request) throws UserNotAuthorizedException, UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JobCronyUserDetails userDetails = (JobCronyUserDetails) authentication.getPrincipal();

        if (!userDetails.getUser().getRoles().contains(Role.JOB_SEEKER)){
            throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        }
        JobOpening jobOpening = jobOpeningService.findJobOpening(request.getJobOpeningId());
        JobSeeker jobSeeker = (JobSeeker) userDetails.getUser();

        Application application = Application.builder()
                .jobSeeker(jobSeeker)
                .jobOpening(jobOpening)
                .resume(request.getResume())
                .skills(jobSeeker.getSkills())
                .experiences(jobSeeker.getExperienceList())
                .educationList(jobSeeker.getEducationList())
                .coverLetter(request.getCoverLetter())
                .applicationStatus(ApplicationStatus.PENDING)
                .build();

        repository.save(application);

        GenericResponse<String> response = GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(APPLICATION_SENT_SUCCESSFULLY)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<GenericResponse<String>> withdrawApplication(Long applicationId) {
        Application foundApplication = repository.findById(applicationId).orElseThrow(()  -> new NotFoundException(NOT_FOUND));
        repository.delete(foundApplication);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().message(WITHDRAWN_SUCCESSFULLY).status(HTTP_STATUS_OK).build());
    }

    @Override
    public ResponseEntity<GenericResponse<String>> acceptApplication(Long applicationId) {
        Application foundApplication = repository.findById(applicationId).orElseThrow(()  -> new NotFoundException(NOT_FOUND));
        foundApplication.setApplicationStatus(ApplicationStatus.ACCEPTED);
        repository.save(foundApplication);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().status(HTTP_STATUS_OK).message(YOUR_APPLICATION_HAS_BEEN_VIEWED).build());
    }

    @Override
    public List<Application> saveApplications(List<Application> applications) {
        return repository.saveAll(applications);
    }



}
