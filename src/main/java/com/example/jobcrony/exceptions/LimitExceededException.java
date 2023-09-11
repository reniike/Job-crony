package com.example.jobcrony.exceptions;

public class LimitExceededException extends JobCronyException {

    public LimitExceededException(String message) {
        super(message);
    }
}
