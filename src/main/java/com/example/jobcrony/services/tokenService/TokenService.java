package com.example.jobcrony.services.tokenService;

import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class TokenService {
    private PreRegistrationRepository repository;

    @Scheduled(cron = "0 0 * * * ?")
    public void checkTokenExpiration() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<JobSeekerPreRegistration> preRegistrations = repository.findByActiveAndTimeCreatedBefore(true, oneHourAgo);

        preRegistrations = preRegistrations.stream().peek(p -> p.setActive(false)).toList();
        repository.deleteAll(preRegistrations);
    }
}
