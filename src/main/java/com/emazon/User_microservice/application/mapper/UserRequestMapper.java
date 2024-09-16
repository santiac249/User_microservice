package com.emazon.User_microservice.application.mapper;

import com.emazon.User_microservice.application.dto.RegisterRequest;
import com.emazon.User_microservice.domain.model.Role;
import com.emazon.User_microservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel= "spring")
public interface UserRequestMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "documentId", source = "documentId")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "role", source = "role_id", qualifiedByName = "mapRoleId")
    User toUser(RegisterRequest registerRequest);

    @Named("mapRoleId")
    default Role mapRoleId(Long roleId) {
        if (roleId == null) {
            return null;
        }
        return new Role(roleId, null); // Aquí solo se asigna el ID; puedes ajustar el nombre si es necesario.
    }
}
