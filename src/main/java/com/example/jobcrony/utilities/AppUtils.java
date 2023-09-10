package com.example.jobcrony.utilities;

import java.util.List;

public class AppUtils {
    public static final String REGISTRATION_ENDPOINT =  "registration";
    public static final String LOGIN_ENDPOINT = "login";
    public static final String MAGIC_LINK = "Your magic link from Job Crony";
    public static final String SYSTEM_MAIL= "aliyahrenike@gmail.com";
    public static final String JOBSEEKER_REGISTRATION_PAGE_URL = "http://localhost:8080/initiateRegistration";
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

    public static final String USER_WITH_EMAIL_NOT_FOUND = "User with email %s not found";
    public static final String JOBSEEKER_REGISTRATION_MAIL =
            "Hello,\n" +
                    "\n" +
                    "Here's your magic link ➡️ %s\n" +
                    "\n" +
                    "Just click it, and you'll be taken to a short form to complete your registration. Simple and fast!\n" +
                    "\n" +
                    "Keep this link safe; it's unique to you.\n" +
                    "\n" +
                    "Need help? Reach out to us at %s.\n" +
                    "\n" +
                    "Thanks for choosing us for your job search.\n" +
                    "\n" +
                    "Best, " +
                    "Job Crony\n";
    public static final String EMAIL_SENT_SUCCESSFULLY = "Email successfully sent!" ;
    public static final String HTTP_STATUS_OK = "200";
    public static final String VERIFICATION_FAILED = "Verification failed!";
    public static final String ACCOUNT_SUCCESSFULLY_CREATED = "Account successfully created!";
    public static final String USER_ALREADY_EXIST = "User with email already exists!";


    public static List<String> getAuthWhiteList(){
        return List.of(
                 LOGIN_ENDPOINT, REGISTRATION_ENDPOINT
        );
    }
}
