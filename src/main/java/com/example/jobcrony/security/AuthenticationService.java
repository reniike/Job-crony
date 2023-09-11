package com.example.jobcrony.security;

import com.example.jobcrony.data.models.User;
import com.example.jobcrony.dtos.requests.AuthenticationRequest;
import com.example.jobcrony.dtos.responses.GenericResponse;
import com.example.jobcrony.utilities.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.AUTHENTICATION_FAILED;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;

    public ResponseEntity<GenericResponse<String>> authenticate(AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            User user = (User) authentication.getPrincipal();
            String jwtToken = jwtUtility.generateToken(user);
            return ResponseEntity.ok().body(GenericResponse.<String>builder().data(jwtToken).build());
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(GenericResponse.<String>builder().message(AUTHENTICATION_FAILED).build());
        }
    }

}
