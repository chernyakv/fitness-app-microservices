package com.chernyak.authservice.controller;

import com.chernyak.authservice.dto.LoginRequest;
import com.chernyak.authservice.dto.RegisterRequest;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import com.chernyak.authservice.exception.UserValidationException;
import com.chernyak.authservice.service.AuthenticationService;
import com.chernyak.authservice.service.TokenStore;
import com.chernyak.authservice.service.impl.AuthenticationServiceImpl;
import com.chernyak.authservice.service.impl.TokenStoreImpl;
import com.chernyak.authservice.validators.LoginUserValidator;
import com.chernyak.authservice.validators.RegistrationUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private AuthenticationService authService;
    private RegistrationUserValidator regUserValidator;
    private LoginUserValidator loginUserValidator;

    @Autowired
    public AuthController(AuthenticationServiceImpl authService,
                          RegistrationUserValidator regUserValidator,
                          LoginUserValidator loginUserValidator) {
        this.regUserValidator = regUserValidator;
        this.loginUserValidator = loginUserValidator;
        this.authService = authService;
    }

    @PostMapping(value="/register")
    public ResponseEntity<User> signUp(@RequestBody RegisterRequest rRequest, BindingResult bindingResult) {
        regUserValidator.validate(rRequest, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.registration(rRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody LoginRequest lRequest, BindingResult bindingResult) {
        loginUserValidator.validate(lRequest, bindingResult);
        if(bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.login(lRequest));
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<JwtToken> refresh(@RequestBody String refreshToken) {
        return ResponseEntity.ok(authService.refresh(refreshToken));
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestBody JwtToken accessToken) {
        authService.logout(accessToken.getAccessToken());
    }
}