package com.example.jobcrony.services.tokenService;

import com.example.jobcrony.data.models.Token;
import com.example.jobcrony.exceptions.InvalidTokenException;

public interface TokenService {
    void deletePreviousTokens(String token);
    void saveToken(Token token);
    Token validateToken(String token) throws InvalidTokenException;
    void disableToken(Token token);
    void findTokenByEmail();
}
