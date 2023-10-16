package com.example.jobcrony.services.applicationService;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.data.repositories.ApplicationRepository;
import com.example.jobcrony.dtos.requests.ApplicationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.ApplicationAlreadyExistsException;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.services.jobOpeningService.JobOpeningService;
import com.example.jobcrony.utilities.AuthenticationUtils;
import com.example.jobcrony.utilities.JobCronyMapper;
import com.example.jobcrony.utilities.MailUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements ApplicationService{
    private final ApplicationRepository repository;
    private final JobOpeningService jobOpeningService;
    private final JobCronyMapper mapper;
    private final MailUtility mailUtility;
    private final AuthenticationUtils authUtils;

    @Override
    public ResponseEntity<GenericResponse<String>> initiateJobApplication(ApplicationRequest request) throws UserNotAuthorizedException, ApplicationAlreadyExistsException {
        JobOpening jobOpening = jobOpeningService.findJobOpening(request.getJobOpeningId());
        JobSeeker jobSeeker = (JobSeeker) authUtils.getCurrentUser();

        validateDuplicateApplication(jobOpening.getId(), jobSeeker.getId());
        Application application = mapper.map(request, jobOpening, jobSeeker);

        repository.save(application);

        GenericResponse<String> response = GenericResponse.<String>builder()
                .status(HTTP_STATUS_OK)
                .message(APPLICATION_SENT_SUCCESSFULLY)
                .build();
        return ResponseEntity.ok().body(response);
    }

    private void validateDuplicateApplication(Long jobOpeningId, Long jobSeekerId) throws ApplicationAlreadyExistsException {
        if (repository.existsByJobOpening_IdAndJobSeeker_Id(jobOpeningId, jobSeekerId))
            throw new ApplicationAlreadyExistsException(YOU_ALREADY_APPLIED_FOR_THIS_JOB_OPENING);
    }

    @Override
    public ResponseEntity<GenericResponse<String>> withdrawApplication(Long applicationId) throws UserNotAuthorizedException {
        if (!authUtils.isRole(Role.JOB_SEEKER)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        Application foundApplication = repository.findById(applicationId).orElseThrow(()  -> new NotFoundException(NOT_FOUND));
        repository.delete(foundApplication);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().message(WITHDRAWN_SUCCESSFULLY).status(HTTP_STATUS_OK).build());
    }

    @Override
    public ResponseEntity<GenericResponse<String>> reviewApplication(Long applicationId) throws UserNotAuthorizedException {
        if (!authUtils.isRole(Role.EMPLOYER)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        Application foundApplication = repository.findById(applicationId).orElseThrow(()  -> new NotFoundException(NOT_FOUND));
        foundApplication.setApplicationStatus(ApplicationStatus.REVIEWED);
        repository.save(foundApplication);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().status(HTTP_STATUS_OK).message(YOUR_APPLICATION_HAS_BEEN_VIEWED).build());
    }

    @Override
    public ResponseEntity<GenericResponse<String>> acceptApplication(Long applicationId) throws SendMailException, UserNotAuthorizedException {
        if (!authUtils.isRole(Role.EMPLOYER)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        Application foundApplication = repository.findById(applicationId).orElseThrow(()  -> new NotFoundException(NOT_FOUND));
        foundApplication.setApplicationStatus(ApplicationStatus.ACCEPTED);

        Application savedApplication = repository.save(foundApplication);
        mailUtility.sendJobSeekerInterviewMail(savedApplication);

        return ResponseEntity.ok().body(GenericResponse.<String>builder().status(HTTP_STATUS_OK).build());
    }

    @Override
    public ResponseEntity<GenericResponse<String>> rejectApplication(Long applicationId) throws SendMailException, UserNotAuthorizedException {
        if (!authUtils.isRole(Role.EMPLOYER)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        Application foundApplication  = repository.findById(applicationId).orElseThrow(() ->new NotFoundException(NOT_FOUND));
        foundApplication.setApplicationStatus(ApplicationStatus.REJECTED);

        Application savedApplication = repository.save(foundApplication);
        mailUtility.sendJobSeekerRejectionMail(savedApplication);
        return ResponseEntity.ok().body(GenericResponse.<String>builder().status(HTTP_STATUS_OK).build());
    }

    @Override
    public List<Application> getAllApplications(Long jobOpeningId) {
        return repository.findApplicationsByJobOpening_Id(jobOpeningId);
    }


    @Override
    public List<Application> saveApplications(List<Application> applications) {
        return repository.saveAll(applications);
    }

    @Override
    public GenericResponse<?> getApplicationById(Long id) {
        Application application = repository.findById(id).orElseThrow(() -> new NotFoundException(NOT_FOUND));
        return GenericResponse.builder().data(application).build();
    }
}
