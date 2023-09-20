package com.example.jobcrony.dtos.requests;


import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreRegistrationRequest {
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    private String emailAddress;
}
