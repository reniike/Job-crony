package com.example.jobcrony.dtos.requests;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendMailRequest {
    private String to;
    private String from;
    private String subject;
    private String text;
}
