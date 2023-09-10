package com.example.jobcrony.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class SendMailRequest {
    private String to;
    private String from;
    private String subject;
    private String text;
}
