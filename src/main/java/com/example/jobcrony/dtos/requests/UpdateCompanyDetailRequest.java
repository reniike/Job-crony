package com.example.jobcrony.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateCompanyDetailRequest {

    @NotBlank
    private String companyName;

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

    @Override
    public String toString() {
        return new StringJoiner(", ", UpdateCompanyDetailRequest.class.getSimpleName() + "[", "]")
                .add("companyName='" + companyName + "'")
                .add("location=" + location)
                .add("website='" + website + "'")
                .add("contactNumber='" + contactNumber + "'")
                .add("industry='" + industry + "'")
                .add("companyDescription='" + companyDescription + "'")
                .add("companyLogo='" + companyLogo + "'")
                .add("numberOfEmployees='" + numberOfEmployees + "'")
                .toString();
    }

}
