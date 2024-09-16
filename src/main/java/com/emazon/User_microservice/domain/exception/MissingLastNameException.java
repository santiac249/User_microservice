package com.emazon.User_microservice.domain.exception;

public class MissingLastNameException extends RuntimeException {
    public MissingLastNameException(String message) {
        super(message);
    }
}
