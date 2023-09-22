package com.example.jobcrony.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LocationRequest{
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
