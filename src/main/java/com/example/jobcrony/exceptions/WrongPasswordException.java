package com.example.jobcrony.exceptions;

public class WrongPasswordException extends JobCronyException{

    public WrongPasswordException(String message) {
        super(message);
    }
}
