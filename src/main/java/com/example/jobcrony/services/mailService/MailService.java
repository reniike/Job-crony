package com.example.jobcrony.services.mailService;

import com.example.jobcrony.dtos.requests.SendMailRequest;

public interface MailService {
    void sendMail(SendMailRequest sendMailRequest);
}
