package com.emazon.User_microservice.domain.usecase;

import com.emazon.User_microservice.domain.exception.*;
import com.emazon.User_microservice.infrastructure.exception.*;
import com.emazon.User_microservice.domain.model.Role;
import com.emazon.User_microservice.domain.model.User;
import com.emazon.User_microservice.domain.spi.IUserPersistencePort;
import com.emazon.User_microservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static com.emazon.User_microservice.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUsecaseTest {

    @InjectMocks
    private UserUsecase userUsecase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    void testRegisterUserSuccess() {
        User user = createValidUser();
        when(rolePersistencePort.findById(anyLong())).thenReturn(Optional.of(new Role()));
        when(userPersistencePort.userExistsByDocumentId(anyString())).thenReturn(false);
        when(userPersistencePort.userExistByEmail(anyString())).thenReturn(false);
        when(userPersistencePort.userExistsByPhone(anyString())).thenReturn(false);

        userUsecase.registerUser(user);

        verify(userPersistencePort, times(1)).registerUser(any(User.class));
    }

    @Test
    void testRegisterUserMissingDocumentId() {
        User user = createValidUser();
        user.setDocumentId(null);  // Simula un documento faltante

        Exception exception = assertThrows(MissingDocumentIdException.class, () -> userUsecase.registerUser(user));
        assertEquals(MISSING_DOCUMENTID, exception.getMessage());  // Usamos la constante
    }

    @Test
    void testRegisterUserInvalidDocumentId() {
        User user = createValidUser();
        user.setDocumentId("invalidDoc");  // Documento con formato inválido

        Exception exception = assertThrows(InvalidDocumentException.class, () -> userUsecase.registerUser(user));
        assertEquals(INVALID_DOCUMENT_FORMAT, exception.getMessage());  // Usamos la constante
    }

    @Test
    void testRegisterUserDuplicateDocumentId() {
        User user = createValidUser();

        when(userPersistencePort.userExistsByDocumentId(user.getDocumentId())).thenReturn(true);

        Exception exception = assertThrows(DocumentAlreadyExistsException.class, () -> userUsecase.registerUser(user));
        assertEquals(DOCUMENT_ALREADY_EXISTS, exception.getMessage());  // Usamos la constante
    }

    @Test
    void testRegisterUserMissingEmail() {
        User user = createValidUser();
        user.setEmail(null);  // Simula un email faltante

        Exception exception = assertThrows(MissingEmailException.class, () -> userUsecase.registerUser(user));
        assertEquals(MISSING_EMAIL, exception.getMessage());  // Usamos la constante
    }

    @Test
    void testRegisterUserInvalidEmailFormat() {
        User user = createValidUser();
        user.setEmail("invalid-email");  // Simula un email con formato inválido

        Exception exception = assertThrows(InvalidEmailException.class, () -> userUsecase.registerUser(user));
        assertEquals(INVALID_EMAIL_FORMAT, exception.getMessage());  // Usamos la constante
    }

    @Test
    void testRegisterUserDuplicateEmail() {
        User user = createValidUser();

        when(userPersistencePort.userExistByEmail(user.getEmail())).thenReturn(true);

        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> userUsecase.registerUser(user));
        assertEquals(EMAIL_ALREADY_EXISTS, exception.getMessage());  // Usamos la constante
    }

    @Test
    void testRegisterUserMissingPhone() {
        User user = createValidUser();
        user.setPhone(null);  // Simula un teléfono faltante

        Exception exception = assertThrows(MissingPhoneException.class, () -> userUsecase.registerUser(user));
        assertEquals(MISSING_PHONE, exception.getMessage());  // Usamos la constante
    }


    @Test
    void testRegisterUserInvalidPhone() {
        User user = createValidUser();
        user.setPhone("123abc");  // Simula un número de teléfono con formato inválido

        Exception exception = assertThrows(InvalidPhoneException.class, () -> userUsecase.registerUser(user));
        assertEquals("El número de teléfono debe contener un máximo de 13 caracteres", exception.getMessage());
    }

    @Test
    void testRegisterUserDuplicatePhone() {
        User user = createValidUser();

        when(userPersistencePort.userExistsByPhone(user.getPhone())).thenReturn(true);

        Exception exception = assertThrows(PhoneAlreadyExistsException.class, () -> userUsecase.registerUser(user));
        assertEquals("El número de celular ya está registrado.", exception.getMessage());

    }

    @Test
    void testRegisterUserUnderage() {
        User user = createValidUser();
        user.setBirthDate(LocalDate.now().minusYears(17));  // Simula un usuario menor de edad

        Exception exception = assertThrows(AgeException.class, () -> userUsecase.registerUser(user));
        assertEquals(UNDERAGE_USER, exception.getMessage());
    }

    @Test
    void testRegisterUserMissingPassword() {
        User user = createValidUser();
        user.setPassword(null);  // Simula una contraseña faltante

        Exception exception = assertThrows(MissingPasswordException.class, () -> userUsecase.registerUser(user));
        assertEquals(MISSING_PASSWORD, exception.getMessage());
    }

    @Test
    void testRegisterUserMissingRole() {
        User user = createValidUser();
        user.setRole(null);  // Simula un rol faltante

        Exception exception = assertThrows(MissingRoleException.class, () -> userUsecase.registerUser(user));
        assertEquals(MISSING_ROLE, exception.getMessage());  // Usamos la constante definida
    }

    @Test
    void testRegisterUserRoleNotFound() {
        User user = createValidUser();
        Role role = new Role();
        role.setId(1L);
        user.setRole(role);

        when(rolePersistencePort.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RoleNotFoundException.class, () -> userUsecase.registerUser(user));
        assertEquals(ROLE_NOT_FOUND, exception.getMessage());
    }

    // Métodos auxiliares para crear objetos de prueba
    private User createValidUser() {
        User user = new User();
        user.setDocumentId("12345678");
        user.setEmail("test@example.com");
        user.setPhone("1234567890");
        user.setBirthDate(LocalDate.of(2000, 1, 1));
        user.setName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        Role role = createValidRole();
        user.setRole(role);
        return user;
    }

    private Role createValidRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("USER");
        return role;
    }
}