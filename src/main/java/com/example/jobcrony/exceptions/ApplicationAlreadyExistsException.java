package com.example.jobcrony.exceptions;

public class ApplicationAlreadyExistsException extends JobCronyException {
    public ApplicationAlreadyExistsException(String message) {
        super(message);
    }
}
