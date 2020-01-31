package com.chernyak.authservice.service;

import com.chernyak.authservice.dto.LoginRequest;
import com.chernyak.authservice.dto.RegisterRequest;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import io.jsonwebtoken.ExpiredJwtException;

public interface AuthenticationService {
    User registration(RegisterRequest registerRequest);
    JwtToken login(LoginRequest loginRequest);
    JwtToken refresh(String refreshToken) throws  ExpiredJwtException;
    void logout(String accessToken);
}
