package com.emazon.User_microservice.infrastructure.output.jpa.mapper;

import com.emazon.User_microservice.domain.model.User;
import com.emazon.User_microservice.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User user);
    User toUser(UserEntity userEntity);
}
