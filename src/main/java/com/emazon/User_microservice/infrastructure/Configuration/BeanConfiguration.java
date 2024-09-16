package com.emazon.User_microservice.infrastructure.Configuration;

import com.emazon.User_microservice.domain.api.IUserServicePort;
import com.emazon.User_microservice.domain.spi.IRolePersistencePort;
import com.emazon.User_microservice.domain.spi.IUserPersistencePort;
import com.emazon.User_microservice.domain.usecase.UserUsecase;
import com.emazon.User_microservice.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.emazon.User_microservice.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.emazon.User_microservice.infrastructure.output.jpa.mapper.RoleEntityMapper;
import com.emazon.User_microservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.User_microservice.infrastructure.output.jpa.repository.IRoleRepository;
import com.emazon.User_microservice.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort(@Lazy IUserPersistencePort userPersistencePort, @Lazy IRolePersistencePort rolePersistencePort) {
        return new UserUsecase(userPersistencePort, rolePersistencePort);
    }

    @Bean
    public IRolePersistencePort rolePersitencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }

}
