package com.chernyak.authservice.service;

import com.chernyak.authservice.dto.LoginRequestModel;
import com.chernyak.authservice.dto.RegisterRequestModel;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import io.jsonwebtoken.ExpiredJwtException;

public interface AuthenticationSerivce {
    public User registration(RegisterRequestModel registerRequestModel);
    public JwtToken login(LoginRequestModel loginRequestModel);
    public JwtToken refresh(String refreshToken) throws  ExpiredJwtException;
    public void logout(String accessToken);
}
