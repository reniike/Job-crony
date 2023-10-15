package com.example.jobcrony.services.tokenService;

import com.example.jobcrony.data.models.JobSeekerPreRegistration;
import com.example.jobcrony.data.models.Token;
import com.example.jobcrony.data.repositories.PreRegistrationRepository;
import com.example.jobcrony.data.repositories.TokenRepository;
import com.example.jobcrony.exceptions.InvalidTokenException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.jobcrony.utilities.AppUtils.INVALID_TOKEN;

@Component
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private PreRegistrationRepository repository;
    private TokenRepository tokenRepository;

    public void deletePreviousTokens(String token) {
        List<JobSeekerPreRegistration> preRegistrations = repository.findAllByEmail(token);
        repository.deleteAll(preRegistrations);
    }

    @Override
    public void saveToken(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public Token validateToken(String token) throws InvalidTokenException {
        return tokenRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException(INVALID_TOKEN));
    }

    @Override
    public void disableToken(Token token) {
        tokenRepository.delete(token);
    }

    @Override
    public void findTokenByEmail() {

    }

}
