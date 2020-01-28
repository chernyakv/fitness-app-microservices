package com.chernyak.authservice.service;

import com.chernyak.authservice.dto.LoginRequestDTO;
import com.chernyak.authservice.dto.RegisterRequestDTO;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import io.jsonwebtoken.ExpiredJwtException;

public interface AuthenticationSerivce {
    public User registration(RegisterRequestDTO registerRequestModel);
    public JwtToken login(LoginRequestDTO loginRequestDTO);
    public JwtToken refresh(String refreshToken) throws  ExpiredJwtException;
    public void logout(String accessToken);
}
