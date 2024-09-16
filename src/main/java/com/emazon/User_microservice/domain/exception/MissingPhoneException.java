package com.emazon.User_microservice.domain.exception;

public class MissingPhoneException extends RuntimeException {
    public MissingPhoneException(String message) {
        super(message);
    }
}
