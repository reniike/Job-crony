package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerRegistrationRequest {
    @NotBlank
    private String token;
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
    private LocationRequest location;
    private List<EducationRegistrationRequest> educationList;
    private List<ExperienceRegistrationRequest> experienceList;
    private List<Skill> skills;
    private Set<Role> roles;
}