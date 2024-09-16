package com.emazon.User_microservice.infrastructure.output.jpa.adapter;

import com.emazon.User_microservice.domain.model.Role;
import com.emazon.User_microservice.domain.spi.IRolePersistencePort;
import com.emazon.User_microservice.infrastructure.output.jpa.entity.RoleEntity;
import com.emazon.User_microservice.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.emazon.User_microservice.infrastructure.output.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;


    @Override
    public Optional<Role> findById(Long id) {
        Optional<RoleEntity> role = roleRepository.findById(id);
        if(role.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(roleEntityMapper.toRole(role.get()));
    }
}
