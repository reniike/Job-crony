package com.example.jobcrony.services.jobOpeningService;

import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.data.models.JobOpening;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.models.Skill;
import com.example.jobcrony.data.repositories.JobOpeningRepository;
import com.example.jobcrony.dtos.requests.JobOpeningRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.services.skillService.SkillService;
import com.example.jobcrony.utilities.AuthenticationUtils;
import com.example.jobcrony.utilities.JobCronyMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class JobOpeningServiceImpl implements JobOpeningService{
    private final JobOpeningRepository repository;
    private final SkillService skillService;
    private final JobCronyMapper mapper;
    private final AuthenticationUtils authUtils;

    @Override
    public ResponseEntity<GenericResponse<String>> postJobOpening(JobOpeningRequest request) throws UserNotAuthorizedException {
        Employer employer = (Employer) authUtils.getCurrentUser();

        JobOpening jobOpening = mapper.map(employer, request);

        List<Skill> skills = skillService.saveSkills(request.getRequiredSkills());

        jobOpening.setRequiredSkills(skills);

        repository.save(jobOpening);
        return ResponseEntity.ok().body(GenericResponse.<String>builder()
                        .status(HTTP_STATUS_OK)
                        .message(JOB_POSTED_SUCCESSFULLY)
                        .build());
    }



    @Override
    public JobOpening findJobOpening(Long jobOpeningId) {
        return repository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException(NOT_FOUND));
    }

    @Override
    public GenericResponse<String> verifyJobOpening(Long jobOpeningId) throws UserNotAuthorizedException {
        if (!authUtils.isRole(Role.ADMIN)) throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        JobOpening jobOpening = findJobOpening(jobOpeningId);
        jobOpening.setVerified(true);
        repository.save(jobOpening);
        return GenericResponse.<String>builder()
                .message(JOB_OPENING_VERIFIED)
                .status(HTTP_STATUS_OK)
                .build();
    }

}
