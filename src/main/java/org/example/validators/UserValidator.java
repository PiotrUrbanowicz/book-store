package org.example.validators;


import org.example.exceptions.UserValidationException;
import org.example.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static void validateLogin(String login) {
        String regex = "^[a-zA-Z0-9]{5,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(login);
        if(!matcher.matches()) {
            throw new UserValidationException("bledny login");
        }
    }

    public static void validatePassword(String password) {
        String regex = "^[a-zA-Z0-9]{5,}$";
        if(!password.matches(regex)) {
            throw new UserValidationException("bledne haslo");
        }
    }

    public static void validateName(String name) {
        String regex = "^[A-Z]{1}[a-z]+$";
        if(!name.matches(regex)) {
            throw new UserValidationException("bledne imie");
        }
    }

    public static void validateSurname(String surname) {
        String regex = "^[A-Z]{1}[a-z]+(-[A-Z]{1}[a-z]+)?$";
        if(!surname.matches(regex)) {
            throw new UserValidationException("bledne nazwisko");
        }
    }

    public static void validatePasswordsEquality(String password1, String password2) {
        if(!password1.equals(password2)) {
            throw new UserValidationException("bledne powturzone haslo");
        }
    }

    public static void validateRegisterUser(User user, String repeatedPassword) {
        validateName(user.getName());
        validateSurname(user.getSurname());
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
        validatePasswordsEquality(user.getPassword(), repeatedPassword);
    }


}
