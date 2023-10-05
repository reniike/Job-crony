package com.example.jobcrony.dtos.requests;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest{
    private String postalCode;
    private String city;
    private String state;
    private String country;
}
