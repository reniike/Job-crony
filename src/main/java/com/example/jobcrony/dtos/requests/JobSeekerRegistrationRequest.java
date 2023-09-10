package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.*;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
public class JobSeekerRegistrationRequest {
    @NotBlank
    private String token;
    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one digit")
    private String password;
    private String profilePicture;
    private String resume;
    private Location location;
    private List<Education> educationList;
    private List<Experience> experienceList;
    private List<Skill> skills;
    private Set<Role> roles;
}
