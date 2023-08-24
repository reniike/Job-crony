package com.example.jobcrony.security.filters;

import com.auth0.jwt.interfaces.Claim;
import com.example.jobcrony.exceptions.AuthenticationException;
import com.example.jobcrony.utilities.JwtUtility;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.jobcrony.utilities.AppUtils.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        boolean isPathInAuthWhitelist = getAuthWhiteList().contains(request.getServletPath()) &&
                request.getMethod().equals(HttpMethod.POST.name());
        if (isPathInAuthWhitelist) filterChain.doFilter(request, response);
        else authorizeRequest(request, response, filterChain);
}

    private void authorizeRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            authorize(request);
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            Map<String, String> errors = new HashMap<>();
            errors.put(ERROR_VALUE, exception.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            mapper.writeValue(response.getOutputStream(), errors);
        }
    }

    private void authorize(HttpServletRequest request) throws AuthenticationException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        System.out.println(authorizationHeader);
        boolean isValidAuthorizationHeader = authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX);
        if (isValidAuthorizationHeader) {
            String token = parseTokenFrom(authorizationHeader);
            authorize(token);
        }
    }

    private String parseTokenFrom(String authorizationHeader) {
        return authorizationHeader.substring(TOKEN_PREFIX.length());
    }

    private void authorize(String token) throws AuthenticationException {
        Map<String, Claim> map = jwtUtility.extractClaimsFrom(token);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Claim claim = map.get(ROLES_VALUE);
        addClaimToUserAuthorities(authorities, claim);
        Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private static void addClaimToUserAuthorities(List<SimpleGrantedAuthority> authorities, Claim claim) {
        for (int i = 0; i < claim.asMap().size(); i++) {
            String role = claim.asMap().get(CLAIM_VALUE + (i + 1)).toString();
            authorities.add(new SimpleGrantedAuthority(role));
        }
    }
}