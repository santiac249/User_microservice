package com.emazon.User_microservice.domain.exception;

public class MissingBirthDateException extends RuntimeException {
    public MissingBirthDateException(String message) {
        super(message);
    }
}
