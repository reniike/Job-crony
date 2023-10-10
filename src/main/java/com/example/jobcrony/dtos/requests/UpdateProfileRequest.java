package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.Skill;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

    private String profilePicture;

    private String resume;

    private LocationRequest location;

    private List<EducationRegistrationRequest> educationList;

    private List<ExperienceRegistrationRequest> experienceList;

    private List<Skill> skills;

}
