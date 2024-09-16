package com.emazon.User_microservice.domain.exception;

public class MissingEmailException extends RuntimeException {
    public MissingEmailException(String message) {
        super(message);
    }
}
