package com.example.jobcrony.dtos.requests;

import com.example.jobcrony.data.models.Location;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRegistrationRequest {
    @NotBlank
    private String companyName;

    @NotBlank
    private Location location;

    @NotBlank
    private String website;

    @NotBlank
    private String contactNumber;

    @NotBlank
    private String email;

    @NotBlank
    private String industry;

    @NotBlank
    private String companyDescription;

    private String companyLogo;

    @NotBlank
    private String numberOfEmployees;

    @NotBlank
    private String companyCode;
}
