package com.example.jobcrony.services.mailService;

import com.example.jobcrony.dtos.requests.SendMailRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(SendMailRequest sendMailRequest) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(sendMailRequest.getText());
        mailMessage.setTo(sendMailRequest.getTo());
        mailMessage.setSubject(sendMailRequest.getSubject());
        mailMessage.setFrom(sendMailRequest.getFrom());
        javaMailSender.send(mailMessage);
    }
}
