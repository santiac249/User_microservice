package com.emazon.User_microservice.domain.exception;

public class MissingDocumentIdException extends RuntimeException {
    public MissingDocumentIdException(String message) {
        super(message);
    }
}
