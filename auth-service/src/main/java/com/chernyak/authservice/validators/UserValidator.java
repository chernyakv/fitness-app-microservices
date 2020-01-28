package com.chernyak.authservice.validators;

import com.chernyak.authservice.dto.RegisterRequestDTO;
import com.chernyak.authservice.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisterRequestDTO.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        User user = (User) o;
    }
}
