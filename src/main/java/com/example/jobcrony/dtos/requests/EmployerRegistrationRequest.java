package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.Company;
import com.example.jobcrony.data.models.Location;
import com.example.jobcrony.data.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static com.example.jobcrony.utilities.AppUtils.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class EmployerRegistrationRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    @NotBlank(message = EMAIL_IS_REQUIRED)
    @Email(message = INVALID_EMAIL_ADDRESS)
    private String email;

    @NotBlank(message = PASSWORD_IS_REQUIRED)
    @Size(min = 8, max = 20, message = PASSWORD_SIZE_MESSAGE)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = PASSWORD_PATTERN_MESSAGE)
    private String password;

    @NotBlank
    private CompanyRegistrationRequest company;

    @NotBlank
    private Location location;

    @NotBlank
    private Set<Role> roles;
}
