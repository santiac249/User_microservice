package com.emazon.User_microservice.infrastructure.exception;

public class DocumentAlreadyExistsException extends RuntimeException {
    public DocumentAlreadyExistsException(String message) {
        super(message);
    }
}
