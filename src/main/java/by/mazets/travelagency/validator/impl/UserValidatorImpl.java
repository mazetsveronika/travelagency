package by.mazets.travelagency.validator.impl;

import by.mazets.travelagency.validator.UserValidator;

import java.util.Map;

import static by.mazets.travelagency.command.RequestParameter.*;

public class UserValidatorImpl implements UserValidator {
    private static final String INCORRECT_VALUE_PARAMETER = "incorrect";
    private static final String LOGIN_REGEX = "[\\p{Alpha}][\\p{Alpha}\\d]{4,29}";
    private static final String PASSWORD_REGEX = "[\\p{Alpha}][\\p{Alpha}\\d]{7,29}";
    private static final String SURNAME_REGEX = "[А-Я\\p{Upper}][а-я\\p{Lower}]{1,20}";
    private static final String NAME_REGEX = "[А-Я\\p{Upper}][а-яё\\p{Lower}]{1,15}";
    private static final String EMAIL_REGEX = "(([\\p{Alpha}\\d._]+){5,25}@([\\p{Lower}]+){3,7}\\.([\\p{Lower}]+){2,3})";
    private static final String NUMBER_REGEX = "\\+375\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
    private static final UserValidator instance = new UserValidatorImpl();

    private UserValidatorImpl() {
    }


    public static UserValidator getInstance() {
        return instance;
    }

    @Override
    public boolean checkLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean checkPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean checkSurname(String surname) {
        return surname != null && surname.matches(SURNAME_REGEX);
    }

    @Override
    public boolean checkName(String name) {
        return name != null && name.matches(NAME_REGEX);
    }

    @Override
    public boolean checkEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }

    @Override
    public boolean checkNumber(String number) {
        return number != null && number.matches(NUMBER_REGEX);
    }

    @Override
    public boolean checkUserData(Map<String, String> userData) {
        boolean isValid = true;
        if (!checkLogin(userData.get(LOGIN))) {
            userData.put(LOGIN, userData.get(LOGIN) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkPassword(userData.get(PASSWORD))) {
            userData.put(PASSWORD, userData.get(LOGIN) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        isValid = checkUserPersonalData(userData) && isValid;
        return isValid;
    }

    @Override
    public boolean checkUserPersonalData(Map<String, String> userData) {
        boolean isValid = true;
        if (!checkSurname(userData.get(SURNAME))) {
            userData.put(SURNAME, userData.get(SURNAME) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkName(userData.get(NAME))) {
            userData.put(NAME, userData.get(NAME) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkEmail(userData.get(EMAIL))) {
            userData.put(EMAIL, userData.get(EMAIL) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        if (!checkNumber(userData.get(PHONE_NUMBER))) {
            userData.put(PHONE_NUMBER, userData.get(PHONE_NUMBER) + INCORRECT_VALUE_PARAMETER);
            isValid = false;
        }
        return isValid;
    }
}