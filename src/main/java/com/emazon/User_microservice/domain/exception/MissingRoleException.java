package com.emazon.User_microservice.domain.exception;

public class MissingRoleException extends RuntimeException {
    public MissingRoleException(String message) {
        super(message);
    }
}
