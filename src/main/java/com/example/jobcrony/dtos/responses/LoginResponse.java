package com.example.jobcrony.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {
    private String refreshToken;
    private String accessToken;
}
