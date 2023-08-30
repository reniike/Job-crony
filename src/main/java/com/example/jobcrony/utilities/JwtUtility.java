package com.example.jobcrony.utilities;

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

@Component
public class JwtUtility {
    @Value("${secretKey}")
    private String secretKey;

    @Value("${expirationTime}")
    private Long expirationTime;

    public String extractEmailFrom(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(User user){
        JobCronyUserDetails jobCronyUserDetails = new JobCronyUserDetails(user); // Wrap the User object
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
                .signWith(getSignInKey(), SignatureAlgorithm.ES256)
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
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
