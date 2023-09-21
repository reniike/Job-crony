package com.example.jobcrony.services.mailService;

import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.exceptions.SendMailException;

public interface MailService {
    void sendMail(SendMailRequest sendMailRequest) throws SendMailException;
}
