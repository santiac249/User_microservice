package com.emazon.User_microservice.application.handler;

import com.emazon.User_microservice.application.dto.RegisterRequest;
import com.emazon.User_microservice.application.mapper.UserRequestMapper;
import com.emazon.User_microservice.domain.api.IUserServicePort;
import com.emazon.User_microservice.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler{

    private final IUserServicePort userServicePort;
    private final UserRequestMapper userRequestMapper;

    @Override
    public void registerUser(RegisterRequest registerRequest) {
        User user = userRequestMapper.toUser(registerRequest);
        userServicePort.registerUser(user);
    }
}
