package com.example.jobcrony.utilities;

import java.util.List;

public class AppUtils {
    public static final String LOGIN_ENDPOINT = "/api/v1/auth/login";
    public static final String LOGIN_SUCCESSFUL = "Login successful";
    public static final String MAGIC_LINK = "MAGIC LINK FROM JOB CRONY";
    public static final String SYSTEM_MAIL= "aliyahrenike@gmail.com";
    public static final String JOBSEEKER_REGISTRATION_PAGE_URL = "/api/v1/job-seeker/initiateRegistration";
    public static final String JOBSEEKER_COMPLETE_REGISTRATION_PAGE_URL = "/api/v1/job-seeker/completeRegistration";
    public static final String ADMIN_REGISTRATION_URL = "/api/v1/admin/register";
    public static final String ADMIN_SEND_LINK_URL = "/api/v1/admin/sendInvitationLink";
    public static final String EMPLOYER_REGISTRATION_URL = "/api/v1/employer/register";
    public static final String CREATE_EVENT_URL = "/api/v1/event/createEvent";
    public static final String ERROR_VALUE = "error";
    public static final String ROLES_VALUE = "roles" ;
    public static final String EMAIL_VALUE = "email" ;
    public static final String CLAIM_VALUE = "claim";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final int INDEX_VALUE = 7;
    public static final String AUTHORIZATION_VALUE = "Authorization";
    public static final String AUTHENTICATION_FAILED_FOR_USER_WITH_EMAIL = "Authentication failed for cultifyUser with email %s";
    public static final String USER_DOES_NOT_EXIST = "User does not exist";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String EMPTY_SPACE_VALUE = " ";
    public static final String ACCESS_TOKEN_VALUE = "access_token" ;
    public static final String JOB_POSTED_SUCCESSFULLY = "Job posted successfully";
    public static final String USER_WITH_EMAIL_NOT_FOUND = "User with email not found";
    public static final String EMAIL_SENT_SUCCESSFULLY = "Email successfully sent!" ;
    public static final String HTTP_STATUS_OK = "200";
    public static final String VERIFICATION_FAILED = "Verification failed!";
    public static final String ACCOUNT_SUCCESSFULLY_CREATED = "Account successfully created!";
    public static final String AUTHENTICATION_FAILED = "Authentication failed";
    public static final String USER_ALREADY_EXIST = "User with email already exists!";
    public static final String COMPANY_DOESNT_EXIST = "Company doesn't exist!" ;
    public static final int COMPANY_CODE_LENGTH = 17;
    public static final String MAX_NUMBER_OF_EMPLOYERS_REACHED = "Max number of employers for this company reached!";
    public static final String WELCOME_TO_JOB_CRONY = "Welcome to Job Crony";
    public static final String UNABLE_TO_SEND_MAIL =  "Unable to send mail" ;
    public static final String HELLO_THERE = "Hello there, Important Info Inside";
    public static final String COMPANY_REGISTERED_SUCCESSFULLY = "Company Registered successfully!";
    public static final String PASSWORD_PATTERN_MESSAGE = "Password must contain at least one uppercase letter, one lowercase letter, and one digit";
    public static final String PASSWORD_SIZE_MESSAGE = "Password must be between 8 and 20 characters";
    public static final String PASSWORD_IS_REQUIRED = "Password is required";
    public static final String INVALID_EMAIL_ADDRESS = "Invalid email address" ;
    public static final String EMAIL_IS_REQUIRED = "Email address is required";
    public static final int THREE = 3;
    public static final String ADMIN_REGISTERED_SUCCESSFULLY = "Admin registered successfully";
    public static final String ADMIN_WITH_EMAIL_EXIST = "Admin with email exist";
    public static final String ADMIN_INVITATION_LINK = "Here's your admin invitation link!";
    public static final String USER_NOT_AUTHORIZED = "User not Authorized";
    public static final String EVENT_REGISTERED_SUCCESSFULLY = "Event registered successfully" ;
    public static final String EVENT_DOESNT_EXIST = "Event doesn't exist" ;

    public static final String COMPANY_EXISTS = "Company exists!" ;
    public static final String CONFIRMATION_MAIL = "Registration Confirmation Mail";
    public static final String REGISTERED_SUCCESSFULLY = "Registered Successfully";
    public static final String JOB_OPENING_URL = "/api/v1/job/postJobOpening";
    public static final String NOT_FOUND = "Not found!";
    public static final String APPLICATION_SENT_SUCCESSFULLY = "Application sent successfully";

    public static List<String> getAuthWhiteList(){
        return List.of(
                 LOGIN_ENDPOINT
        );
    }
}
