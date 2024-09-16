package com.emazon.User_microservice.domain.exception;

public class NullException extends RuntimeException {
    public NullException(String message) {
        super(message);
    }
}
