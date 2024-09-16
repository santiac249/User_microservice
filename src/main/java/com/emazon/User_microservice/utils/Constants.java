package com.emazon.User_microservice.utils;

public class Constants {


    public static final String ENCRYPTION_ERROR = "Error al encriptar la contraseña.";
    public static final String USER_CREATED_SUCCESSFULLY = "Usuario creado exitosamente.";

    //Missing
    public static final String MISSING_BIRTHDATE = "La fecha de nacimiento no puede ser nula ni estar vacía.";
    public static final String MISSING_DOCUMENTID = "El documento de identidad no puede ser nulo ni estar vacío.";
    public static final String MISSING_EMAIL = "El correo electrónico no puede ser nulo ni estar vacío.";
    public static final String MISSING_LASTNAME = "El apellido no puede ser nulo ni estar vacío.";
    public static final String MISSING_PASSWORD = "La contraseña no puede ser nula ni estar vacía.";
    public static final String MISSING_PHONE = "El número de teléfono no puede ser nulo ni estar vacío.";
    public static final String MISSING_ROLE = "El rol no puede ser nulo ni estar vacío.";
    public static final String MISSING_NAME = "El nombre no puede ser nulo ni estar vacío";

    //Regex
    public static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_+&*-]+(\\.[a-zA-Z0-9_+&*-]+)?@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,7}+(\\.[a-zA-Z]{2,7}+)?$";
    public static final String DOCUMENT_VALIDATION_REGEX = "^\\d+$";
    public static final String PHONE_VALIDATION_REGEX = "^\\+?[0-9]{10,13}$";

    //Invalid format
    public static final String INVALID_EMAIL_FORMAT = "El formato del correo electrónico es inválido.";
    public static final String INVALID_PHONE_FORMAT = "El número de teléfono debe contener un máximo de 13 caracteres";
    public static final String INVALID_DOCUMENT_FORMAT = "El documento de identidad debe ser numérico.";

    //Already exists
    public static final String EMAIL_ALREADY_EXISTS = "El correo electrónico ya está registrado.";
    public static final String DOCUMENT_ALREADY_EXISTS = "El documento de identidad ya está registrado.";
    public static final String PHONE_ALREADY_EXISTS = "El número de celular ya está registrado.";

    //Age
    public static final String UNDERAGE_USER = "El usuario debe ser mayor de edad.";
    public static final Integer ADULT = 18;

    //Role
    public static final String INVALID_ROLE = "Rol inválido.";

    //Not found
    public static final String USER_NOT_FOUND = "Usuario no encontrado.";
    public static final String ROLE_NOT_FOUND = "Rol no encontrado.";
}
