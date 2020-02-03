package com.chernyak.authservice.service.impl;

import com.chernyak.authservice.client.UserServiceFeignClient;
import com.chernyak.authservice.dto.LoginRequest;
import com.chernyak.authservice.dto.RegisterRequest;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import com.chernyak.authservice.security.JwtTokenProvider;
import com.chernyak.authservice.service.AuthenticationService;
import com.chernyak.authservice.service.TokenStore;
import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private JwtTokenProvider tokenProvider;
    private TokenStore tokenStore;
    private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    public AuthenticationServiceImpl(
            JwtTokenProvider jwtTokenProvider,
            UserServiceFeignClient userServiceFeignClient,
            TokenStoreImpl tokenStore) {
        this.tokenProvider = jwtTokenProvider;
        this.userServiceFeignClient = userServiceFeignClient;
        this.tokenStore = tokenStore;
    }

    @Override
    public User registration(RegisterRequest registerRequestModel) {
        User newUser = new User();
        newUser.setUsername(registerRequestModel.getLogin());
        newUser.setPassword(registerRequestModel.getPassword());
        return userServiceFeignClient.save(newUser);
    }

    @Override
    public JwtToken login(LoginRequest loginRequest) throws ExpiredJwtException, BadCredentialsException, FeignException {
        User user = userServiceFeignClient.getByLogin(loginRequest.getLogin());
        final String token = tokenProvider.generateToken(user);
        final String refreshToken = tokenProvider.generateRefreshToken(user);
        return tokenStore.storeToken(new JwtToken(token, refreshToken));
    }

    @Override
    public JwtToken refresh(String refreshToken) throws ExpiredJwtException {
        String username = tokenProvider.getUsernameFromToken(refreshToken);
        User userDetails = userServiceFeignClient.getByLogin(username);
        if (tokenProvider.validateToken(refreshToken, userDetails)) {
            final String token = tokenProvider.generateToken(userDetails);
            final String newRefreshToken = tokenProvider.generateRefreshToken(userDetails);
            return new JwtToken(token, newRefreshToken);
        }
        return null;
    }

    @Override
    public void logout(String accessToken) {
        tokenStore.removeToken(accessToken);
    }
}

