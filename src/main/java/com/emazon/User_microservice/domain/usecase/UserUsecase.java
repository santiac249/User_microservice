package com.emazon.User_microservice.domain.usecase;

import com.emazon.User_microservice.domain.api.IUserServicePort;
import com.emazon.User_microservice.domain.exception.*;
import com.emazon.User_microservice.domain.model.Role;
import com.emazon.User_microservice.domain.model.User;
import com.emazon.User_microservice.domain.spi.IUserPersistencePort;
import com.emazon.User_microservice.domain.spi.IRolePersistencePort;
import com.emazon.User_microservice.infrastructure.exception.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.Period;

import static com.emazon.User_microservice.utils.Constants.*;


public class UserUsecase implements IUserServicePort {


    public UserUsecase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final BCryptPasswordEncoder passwordEncoder;



    @Override
    public void registerUser(User user) {

        //Validaciones del documento
        if(user.getDocumentId() == null || user.getDocumentId().isEmpty()) {
            throw new MissingDocumentIdException(MISSING_DOCUMENTID);
        }
        if (!user.getDocumentId().matches(DOCUMENT_VALIDATION_REGEX)) {
            throw new InvalidDocumentException(INVALID_DOCUMENT_FORMAT);
        }
        if(userPersistencePort.userExistsByDocumentId(user.getDocumentId())){
            throw new DocumentAlreadyExistsException(DOCUMENT_ALREADY_EXISTS);
        }

        //Validaciones del email
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new MissingEmailException(MISSING_EMAIL);
        }
        if (!user.getEmail().matches(EMAIL_VALIDATION_REGEX)) {
            throw new InvalidEmailException(INVALID_EMAIL_FORMAT);
        }
        if(userPersistencePort.userExistByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXISTS);
        }

        //Validaciones del número de celular
        if (user.getPhone()== null || user.getPhone().isEmpty()) {
            throw new MissingPhoneException(MISSING_PHONE);
        }
        if (!user.getPhone().matches(PHONE_VALIDATION_REGEX)) {
            throw new InvalidPhoneException(INVALID_PHONE_FORMAT);
        }
        if(userPersistencePort.userExistsByPhone(user.getPhone())){
            throw new PhoneAlreadyExistsException(PHONE_ALREADY_EXISTS);
        }

        //Validaciones de la fecha de nacimiento
        if(user.getBirthDate() == null) {
            throw new MissingBirthDateException(MISSING_BIRTHDATE);
        }
        if(!isAdult(user.getBirthDate())){
            throw new AgeException(UNDERAGE_USER);
        }


        if(user.getName() == null || user.getName().isEmpty()) {
            throw new MissingNameException(MISSING_NAME);
        }
        if(user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new MissingLastNameException(MISSING_LASTNAME);
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new MissingPasswordException(MISSING_PASSWORD);
        }
        if (user.getRole() == null) {
            throw new MissingRoleException(MISSING_ROLE);
        }

        Role role = rolePersistencePort.findById(user.getRole().getId())
                .orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND));

        user.setRole(role);

        user.setPassword(encryptPassword(user.getPassword()));

        this.userPersistencePort.registerUser(user);
    }

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean isAdult(LocalDate birthDate){
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return age >= ADULT;
    }
}
