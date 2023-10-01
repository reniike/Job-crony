package com.example.jobcrony.utilities;

import com.example.jobcrony.data.models.*;
import com.example.jobcrony.dtos.requests.SendMailRequest;
import com.example.jobcrony.exceptions.SendMailException;
import com.example.jobcrony.services.mailService.MailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

import static com.example.jobcrony.utilities.AppUtils.*;
import static com.example.jobcrony.utilities.AppUtils.SYSTEM_MAIL;

@Component
@AllArgsConstructor
public class MailUtility {
    private MailService mailService;
    private SpringTemplateEngine templateEngine;

    public void sendEmployerWelcomeMail(Employer employer) throws SendMailException {

        Context context = new Context();
        context.setVariables(Map.of("systemMail", SYSTEM_MAIL));

        String welcomeMail = templateEngine.process("employer_welcome_mail", context);

        SendMailRequest mailRequest = SendMailRequest.builder()
                .to(employer.getEmail())
                .from(SYSTEM_MAIL)
                .subject(WELCOME_TO_JOB_CRONY)
                .text(welcomeMail)
                .build();
        mailService.sendMail(mailRequest);
    }

    public void sendCompanyWelcomeEmail(Company company) throws SendMailException {
        Context context = new Context();
        context.setVariables(Map.of("companyCode", company.getCompanyCode(), "systemMail", SYSTEM_MAIL));

        String mailContent = templateEngine.process("company_created_welcome_mail", context);

        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .to(company.getCompanyEmail())
                .from(SYSTEM_MAIL)
                .text(mailContent)
                .subject(HELLO_THERE)
                .build();
        mailService.sendMail(sendMailRequest);
    }

    public void sendJobSeekerWelcomeMail(String emailAddress, String magicLink, String systemMail) throws SendMailException {

        Context context = new Context();
        context.setVariables(Map.of("magicLink", magicLink, "systemMail", systemMail));

        String mailContent = templateEngine.process("jobseeker_registration_mail", context);

        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .text(mailContent)
                .subject(MAGIC_LINK)
                .from(SYSTEM_MAIL)
                .to(emailAddress)
                .build();
        mailService.sendMail(sendMailRequest);
    }

    public void sendJobSeekerInterviewMail(Application savedApplication) throws SendMailException {
        JobSeeker jobSeeker = savedApplication.getJobSeeker();
        String jobSeekerEmail = jobSeeker.getEmail();
        String firstName = jobSeeker.getFirstName();

        JobOpening jobOpening = savedApplication.getJobOpening();
        String jobTitle = jobOpening.getJobTitle();
        String companyName = jobOpening.getEmployer().getCompany().getCompanyName();

        Context context = new Context();
        context.setVariables(Map.of("firstName", firstName, "jobTitle", jobTitle, "companyName", companyName));

        String mailContent = templateEngine.process("interview_email", context);
        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .subject(NEXT_INTERVIEW)
                .text(mailContent)
                .to(jobSeekerEmail)
                .from(SYSTEM_MAIL)
                .build();
        mailService.sendMail(sendMailRequest);
    }

    public void sendJobSeekerRejectionMail(Application savedApplication) throws SendMailException {
        JobSeeker jobSeeker = savedApplication.getJobSeeker();
        String firstName = jobSeeker.getFirstName();
        String jobSeekerEmail = jobSeeker.getEmail();

        JobOpening jobOpening = savedApplication.getJobOpening();
        String jobTitle = jobOpening.getJobTitle();
        String companyName = jobOpening.getEmployer().getCompany().getCompanyName();

        Context context = new Context();
        context.setVariables(Map.of("firstName", firstName, "companyName", companyName , "jobTitle", jobTitle));

        String mailContent = templateEngine.process("rejection_email", context);
        SendMailRequest sendMailRequest = SendMailRequest.builder()
                .subject(REJECTION_UPDATE)
                .text(mailContent)
                .to(jobSeekerEmail)
                .from(SYSTEM_MAIL)
                .build();

        mailService.sendMail(sendMailRequest);
    }
}
