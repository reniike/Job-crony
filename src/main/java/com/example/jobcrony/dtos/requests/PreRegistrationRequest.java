package com.example.jobcrony.dtos.requests;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PreRegistrationRequest {
    @Id
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    private String emailAddress;
}
