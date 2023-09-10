package com.example.jobcrony.exceptions;

public class UserAlreadyExistException extends JobCronyException {

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
