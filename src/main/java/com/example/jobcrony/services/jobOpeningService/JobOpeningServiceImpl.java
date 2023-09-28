package com.example.jobcrony.services.jobOpeningService;

import com.example.jobcrony.data.models.Employer;
import com.example.jobcrony.data.models.JobOpening;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.models.Skill;
import com.example.jobcrony.data.repositories.JobOpeningRepository;
import com.example.jobcrony.dtos.requests.JobOpeningRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.exceptions.UserNotAuthorizedException;
import com.example.jobcrony.security.JobCronyUserDetails;
import com.example.jobcrony.services.skillService.SkillService;
import com.example.jobcrony.utilities.AppUtils;
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
public class JobOpeningServiceImpl implements JobOpeningService{
    private final JobOpeningRepository repository;
    private final SkillService skillService;

    @Override
    public ResponseEntity<GenericResponse<String>> postJobOpening(JobOpeningRequest request) throws UserNotAuthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JobCronyUserDetails userDetails = (JobCronyUserDetails) authentication.getPrincipal();
        if (!userDetails.getUser().getRoles().contains(Role.EMPLOYER)){
            throw new UserNotAuthorizedException(USER_NOT_AUTHORIZED);
        }

        JobOpening jobOpening = JobOpening.builder()
                .employer((Employer) userDetails.getUser())
                .jobStyle(request.getJobStyle())
                .jobDescription(request.getJobDescription())
                .jobTitle(request.getJobTitle())
                .experienceLevel(request.getExperienceLevel())
                .yearsOfExperience(request.getYearsOfExperience())
                .requiredSkills(request.getRequiredSkills())
                .build();

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
        return repository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException(AppUtils.NOT_FOUND));
    }
}
