package com.emazon.User_microservice.application.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String lastName;

    @NotBlank
    private String documentId;

    @NotNull
    @Size(min = 1, max = 13)
    private String phone;

    @NotNull
    private LocalDate birthDate;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private Long role_id;

}
