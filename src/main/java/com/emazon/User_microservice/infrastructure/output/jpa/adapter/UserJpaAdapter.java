package com.emazon.User_microservice.infrastructure.output.jpa.adapter;

import com.emazon.User_microservice.domain.model.User;
import com.emazon.User_microservice.domain.spi.IUserPersistencePort;
import com.emazon.User_microservice.infrastructure.output.jpa.entity.UserEntity;
import com.emazon.User_microservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.User_microservice.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void registerUser(User user) {
        this.userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public boolean userExistByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.isPresent();
    }

    @Override
    public boolean userExistsByPhone(String phone) {
        Optional<UserEntity> userEntity = userRepository.findByPhone(phone);
        return userEntity.isPresent();
    }

    @Override
    public boolean userExistsByDocumentId(String documentId) {
        Optional<UserEntity> userEntity = userRepository.findByDocumentId(documentId);
        return userEntity.isPresent();
    }

}
