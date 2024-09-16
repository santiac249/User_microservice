package com.emazon.User_microservice.domain.spi;

import com.emazon.User_microservice.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {
    Optional<Role> findById(Long id);
}
