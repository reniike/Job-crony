package com.example.jobcrony.services.tokenService;

import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private PreRegistrationRepository repository;

    public void deletePreviousTokens(String token) {
        List<JobSeekerPreRegistration> preRegistrations = repository.findAllByEmail(token);
        repository.deleteAll(preRegistrations);
    }
}
