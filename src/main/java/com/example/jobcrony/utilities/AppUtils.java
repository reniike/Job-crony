package com.example.jobcrony.utilities;

import java.util.List;

public class AppUtils {
    public static final String REGISTRATION_ENDPOINT =  "registration";
    public static final String LOGIN_ENDPOINT = "login";
    public static final String ERROR_VALUE = "error";
    public static final String ROLES_VALUE = "roles" ;
    public static final String EMAIL_VALUE = "email" ;
    public static final String CLAIM_VALUE = "claim";
    public static final Long EXPIRATION_TIME = 12000L;



    public static List<String> getAuthWhiteList(){
        return List.of(
                 LOGIN_ENDPOINT, REGISTRATION_ENDPOINT
        );
    }
}
