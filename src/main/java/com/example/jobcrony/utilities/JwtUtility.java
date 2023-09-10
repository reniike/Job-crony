package com.example.jobcrony.utilities;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.models.User;
import com.example.jobcrony.security.JobCronyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

import static com.example.jobcrony.utilities.AppUtils.EMAIL_VALUE;
import static java.time.Instant.now;

@Component
public class JwtUtility {
    @Value("${expirationTime}")
    private Long expirationTime;

    @Value("${secretKey}")
    private String secretKey;

    public String extractEmailFrom(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user){
        JobCronyUserDetails jobCronyUserDetails = new JobCronyUserDetails(user);
        return generateToken(user.getRoles(), jobCronyUserDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractEmailFrom(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationFrom(token).before(new Date());
    }

    private Date extractExpirationFrom(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Collection<Role> roles, JobCronyUserDetails jobCronyUserDetails){
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);
        return Jwts.builder()
                .claim("roles", roles)
                .setSubject(jobCronyUserDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    public String generateToken(String email) {
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(expirationTime))
                .withClaim(EMAIL_VALUE , email)
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
}
