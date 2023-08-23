package com.example.jobcrony.security;

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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.jobcrony.utilities.AppUtils.ERROR_VALUE;
import static com.example.jobcrony.utilities.AppUtils.getAuthWhiteList;
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

    private void authorize(HttpServletRequest request) {
    }
}