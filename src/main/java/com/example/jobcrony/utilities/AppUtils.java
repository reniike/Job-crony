package com.example.jobcrony.utilities;

import java.util.List;

public class AppUtils {
    public static final String REGISTRATION_ENDPOINT =  "registration";
    public static final String LOGIN_ENDPOINT = "login";
    public static final String MAGIC_LINK = "Your magic link from Job Crony";
    public static final String SYSTEM_MAIL= "aliyahrenike@gmail.com";
    public static final String JOBSEEKER_REGISTRATION_PAGE_URL = "http://localhost:8080/initiateRegistration";
    public static final String ADMIN_REGISTRATION_URL = "http://localhost:8080/adminRegistration";
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
            """
                    Hello,

                    Here's your magic link ➡️ %s

                    Just click it, and you'll be taken to a short form to complete your registration. Simple and fast!

                    Keep this link safe; it's unique to you.

                    Need help? Reach out to us at %s.

                    Thanks for choosing us for your job search.

                    Best, Job Crony
                    """;
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
    public static final String EMPLOYER_WELCOME_MAIL = """
            Hello,

            Your employer account is ready to go!\s

            Get started right away by posting job listings, finding the best talent, and managing your hiring process hassle-free.

            If you have any questions or need help, just drop us a message at %s.

            Thanks for choosing us for your hiring needs.
            """;
    public static final String COMPANY_CREATED_WELCOME_MAIL = """
            Hello,

            Great news! Your company account is all set up.

            Your unique company code is: %s

            Share this code with up to 3 representatives from your company. They can use it during their registration to become employers on our platform.

            If you have any questions or need help, please don't hesitate to reach out to us at %s.

            Thank you for choosing us for your hiring needs.

            Best regards,
            Job Crony
            """;
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

    public static final String EVENT_EMAIL = """
            Dear %s,

            We're thrilled to have you join us!

            Date: %s
            %s
            %s

            Thank you for registering. Get ready for an unforgettable experience filled with knowledge, connections, and fun!

            See you soon!

            Best regards.""";


    public static List<String> getAuthWhiteList(){
        return List.of(
                 LOGIN_ENDPOINT, REGISTRATION_ENDPOINT
        );
    }
}
