package com.example.jobcrony.exceptions;

public class UserNotFoundException extends JobCronyException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
