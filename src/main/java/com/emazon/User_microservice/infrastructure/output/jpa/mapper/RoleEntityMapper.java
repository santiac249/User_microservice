package com.emazon.User_microservice.infrastructure.output.jpa.mapper;

import com.emazon.User_microservice.domain.model.Role;
import com.emazon.User_microservice.infrastructure.output.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleEntity toEntity(Role role);
    Role toRole(RoleEntity roleEntity);
}
