package com.emazon.User_microservice.domain.exception;

public class MissingNameException extends RuntimeException {
    public MissingNameException(String message) {
        super(message);
    }
}
