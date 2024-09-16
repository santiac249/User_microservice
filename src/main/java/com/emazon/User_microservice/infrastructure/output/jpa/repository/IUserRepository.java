package com.emazon.User_microservice.infrastructure.output.jpa.repository;

import com.emazon.User_microservice.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByDocumentId(String documentId);
}
