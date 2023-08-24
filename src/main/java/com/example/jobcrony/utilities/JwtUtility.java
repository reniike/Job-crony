package com.example.jobcrony.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.jobcrony.utilities.AppUtils.*;
import static java.time.Instant.now;
import static org.springframework.security.oauth2.core.OAuth2ErrorCodes.INVALID_TOKEN;

@Component
public class JwtUtility {
    @Value("${secretKey}")
    private String secretKey;

    @Value("${expirationTime}")
    private Long expirationTime;

    public Map<String, Claim> extractClaimsFrom(String token) throws JWTVerificationException {
        DecodedJWT decodedJwt = validateToken(token);
        if (decodedJwt.getClaim(ROLES_VALUE) == null) throw new JWTVerificationException(INVALID_TOKEN);
        return decodedJwt.getClaims();
    }

    public DecodedJWT validateToken(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(secretKey))
                .build();
        return verifier.verify(token);
    }

    public String extractEmailFrom(String token) throws JWTVerificationException {
        DecodedJWT decodedJwt = validateToken(token);
        if (decodedJwt.getClaim(EMAIL_VALUE) == null) throw new JWTVerificationException(INVALID_TOKEN);
        return decodedJwt.getClaim(EMAIL_VALUE).asString();
    }

    public String generateEncryptedLink(String userEmail) {
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(expirationTime))
                .withClaim(EMAIL_VALUE, userEmail)
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }

    public String generateAccessToken(Collection<? extends GrantedAuthority> authorities){
        Map<String, String> map = new HashMap<>();
        int count = 1;
        for (GrantedAuthority authority : authorities) {
            map.put(CLAIM_VALUE + count, authority.getAuthority());
            count++;
        }
        Date expirationDate = Date.from(now().plusSeconds(expirationTime));
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(expirationDate)
                .withClaim(ROLES_VALUE, map)
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
}
