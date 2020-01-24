package com.chernyak.authservice.controller;

import com.chernyak.authservice.dto.LoginRequestModel;
import com.chernyak.authservice.dto.RegisterRequestModel;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import com.chernyak.authservice.exception.UserValidationException;
import com.chernyak.authservice.service.AuthenticationSerivce;
import com.chernyak.authservice.service.TokenStore;
import com.chernyak.authservice.service.impl.AuthenticationServiceImpl;
import com.chernyak.authservice.service.impl.TokenStoreImpl;
import com.chernyak.authservice.validators.LoginUserValidator;
import com.chernyak.authservice.validators.RegistrationUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private AuthenticationSerivce authService;
    private RegistrationUserValidator regUserValidator;
    private LoginUserValidator loginUserValidator;
    private TokenStore tokenStore;

    @Autowired
    public AuthController(AuthenticationServiceImpl authService,
                          RegistrationUserValidator regUserValidator,
                          LoginUserValidator loginUserValidator,
                          TokenStoreImpl tokenStore) {
        this.regUserValidator = regUserValidator;
        this.loginUserValidator = loginUserValidator;
        this.tokenStore = tokenStore;
        this.authService = authService;
    }

    @GetMapping(value="/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("test");
    }

    @PostMapping(value="/register")
    public ResponseEntity<User> signUp(@RequestBody RegisterRequestModel registerRequestModel, BindingResult bindingResult) {
        regUserValidator.validate(registerRequestModel, bindingResult);
        if(bindingResult.hasErrors()){
            throw new UserValidationException(RestExceptionHandler.createExceptionMessage(bindingResult.getAllErrors()));
        }
        return ResponseEntity.ok(authService.registration(registerRequestModel));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtToken> signIn(@RequestBody LoginRequestModel lRequest, BindingResult bindingResult) {
        loginUserValidator.validate(lRequest, bindingResult);
        if(bindingResult.hasErrors()) {
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
        tokenStore.removeToken(accessToken.getAccessToken());
    }
}