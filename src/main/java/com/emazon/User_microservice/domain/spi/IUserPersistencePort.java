package com.emazon.User_microservice.domain.spi;

import com.emazon.User_microservice.domain.model.User;

public interface IUserPersistencePort {
    void registerUser(User user);
    boolean userExistByEmail(String email);
    boolean userExistsByPhone(String phone);
    boolean userExistsByDocumentId(String documentId);


}
