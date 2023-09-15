package com.example.jobcrony.exceptions;

public class EventDoesntExistException extends JobCronyException {
    public EventDoesntExistException(String message) {
        super(message);
    }
}
