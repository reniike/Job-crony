package com.example.jobcrony.configurations;

import com.example.jobcrony.controller.JobSeekerController;
import com.example.jobcrony.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.jobcrony.utilities.AppUtils.*;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final  AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        return https
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(c -> c.requestMatchers(POST, LOGIN_ENDPOINT)
                        .permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, JOBSEEKER_REGISTRATION_PAGE_URL)
                        .permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, JOBSEEKER_COMPLETE_REGISTRATION_PAGE_URL)
                        .permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, ADMIN_REGISTRATION_URL)
                        .permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, ADMIN_SEND_LINK_URL)
                        .permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST,  EMPLOYER_REGISTRATION_URL )
                        .permitAll())
                .authorizeHttpRequests(c -> c.requestMatchers(POST, CREATE_EVENT_URL )
                        .permitAll())
                .authorizeHttpRequests(c->c.anyRequest().authenticated())
                .build();
    }
}
