package com.example.jobcrony.exceptions;

public class UserNotAuthorizedException extends JobCronyException {
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
