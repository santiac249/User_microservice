package com.emazon.User_microservice.domain.exception;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }
}
