package com.chernyak.authservice.validators;

import com.chernyak.authservice.dto.RegisterRequestModel;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class RegistrationUserValidator implements Validator {
    private static final int MINIMUM_PASSWORD_LENGTH = 6;
    private static final int MINIMUM_LOGIN_LENGTH = 4;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequestModel.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field required");

        RegisterRequestModel registrationModel = (RegisterRequestModel) o;

        if (registrationModel.getPassword() != null && registrationModel.getPassword().trim().length() < MINIMUM_PASSWORD_LENGTH) {
            errors.rejectValue("password", "field.min.length",
                    new Object[]{Integer.valueOf(MINIMUM_PASSWORD_LENGTH)},
                    "The password must be at least [" + MINIMUM_PASSWORD_LENGTH + "] characters in length.");
        }

        if (registrationModel.getLogin() != null && registrationModel.getLogin().trim().length() < MINIMUM_LOGIN_LENGTH) {
            errors.rejectValue("login", "field.min.length",
                    new Object[]{Integer.valueOf(MINIMUM_LOGIN_LENGTH)},
                    "The login must be at least [" + MINIMUM_LOGIN_LENGTH + "] characters in length.");
        }
    }
}
