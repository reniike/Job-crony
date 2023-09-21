package com.example.jobcrony.services.mailService;

import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.exceptions.SendMailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.example.jobcrony.utilities.AppUtils.UNABLE_TO_SEND_MAIL;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
    private final JavaMailSender mailSender;

    @Override
    public void sendMail(SendMailRequest sendMailRequest) throws SendMailException {
        try {
            MimeMessage mailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            helper.setText(sendMailRequest.getText(), true);
            helper.setFrom(sendMailRequest.getFrom());
            helper.setTo(sendMailRequest.getTo());
            helper.setSubject(sendMailRequest.getSubject());
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            throw new SendMailException(UNABLE_TO_SEND_MAIL);
        }

    }
}
