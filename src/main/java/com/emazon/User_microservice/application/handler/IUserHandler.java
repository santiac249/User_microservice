package com.emazon.User_microservice.application.handler;

import com.emazon.User_microservice.application.dto.RegisterRequest;

public interface IUserHandler {
    void registerUser(RegisterRequest registerRequest);
}
