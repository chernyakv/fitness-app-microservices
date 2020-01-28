package com.chernyak.authservice.validators;

import com.chernyak.authservice.dto.LoginRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service
public class LoginUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return LoginRequestDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");

        LoginRequestDTO loginRequestDTO = (LoginRequestDTO) o;

        if(loginRequestDTO.getPassword().length() < 6){
            errors.rejectValue("password", "negativeValue", new Object[]{"'password'"}, "password length should be > 6");
        }
    }
}
