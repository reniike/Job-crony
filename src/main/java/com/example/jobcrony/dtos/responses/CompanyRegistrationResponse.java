package com.example.jobcrony.dtos.responses;


import com.example.jobcrony.data.models.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CompanyRegistrationResponse {
    private Company company;
    private String message;
}
