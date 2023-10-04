package com.example.jobcrony.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCompanyDetailRequest {
    @NotBlank
    private String companyCode;

    @NotBlank
    private String companyName;

    @NotNull
    private LocationRequest location;

    @NotBlank
    private String website;

    @NotBlank
    private String contactNumber;

    @NotBlank
    private String industry;

    @NotBlank
    private String companyDescription;

    private String companyLogo;

    @NotBlank
    private String numberOfEmployees;
}
