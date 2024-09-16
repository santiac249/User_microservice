package com.emazon.User_microservice.domain.api;

import com.emazon.User_microservice.domain.model.User;

import java.time.LocalDate;

public interface IUserServicePort {
    void registerUser(User user);
    String encryptPassword(String password);
    boolean isAdult(LocalDate birthDate);
}
