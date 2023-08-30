package com.example.jobcrony.utilities;

import java.util.List;

public class AppUtils {
    public static final String REGISTRATION_ENDPOINT =  "registration";
    public static final String LOGIN_ENDPOINT = "login";
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



    public static List<String> getAuthWhiteList(){
        return List.of(
                 LOGIN_ENDPOINT, REGISTRATION_ENDPOINT
        );
    }
}
