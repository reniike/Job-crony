package com.example.jobcrony.security;

import com.example.jobcrony.data.models.Role;
import com.example.jobcrony.data.models.User;
import com.example.jobcrony.data.repositories.UserRepository;
import com.example.jobcrony.dtos.requests.AuthenticationRequest;
import com.example.jobcrony.dtos.requests.RegistrationRequest;
import com.example.jobcrony.dtos.responses.AuthenticationResponse;
import com.example.jobcrony.utilities.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.example.jobcrony.utilities.AppUtils.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    public AuthenticationResponse register(RegistrationRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(Collections.singleton(Role.JOB_SEEKER))
                .build();
        userRepository.save(user);
        JobCronyUserDetails jobCronyUserDetails = new JobCronyUserDetails(user);
        String jwtToken = jwtUtility.generateToken(user.getRoles(), jobCronyUserDetails);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
       User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND));
       String jwtToken = jwtUtility.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
