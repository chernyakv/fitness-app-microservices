package com.chernyak.authservice.service.impl;

import com.chernyak.authservice.client.UserServiceFeignClient;
import com.chernyak.authservice.dto.LoginRequestDTO;
import com.chernyak.authservice.dto.RegisterRequestDTO;
import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.entity.User;
import com.chernyak.authservice.security.JwtTokenProvider;
import com.chernyak.authservice.service.AuthenticationSerivce;
import com.chernyak.authservice.service.TokenStore;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationSerivce {

    private JwtTokenProvider tokenProvider;
    private TokenStore tokenSotre;
    private UserServiceFeignClient userServiceFeignClient;

    @Autowired
    public AuthenticationServiceImpl(
                                     JwtTokenProvider jwtTokenProvider,
                                     UserServiceFeignClient userServiceFeignClient,
                                     TokenStoreImpl tokenStore) {
        this.tokenProvider = jwtTokenProvider;
        this.userServiceFeignClient = userServiceFeignClient;
        this.tokenSotre = tokenStore;
    }

    @Override
    public User registration(RegisterRequestDTO registerRequestModel) {
        User newUser = new User();
        User defaultUser = userServiceFeignClient.getByLogin(User.DEFAULT_USER_LOGIN);
        newUser.setLogin(registerRequestModel.getLogin());
        newUser.setPassword(registerRequestModel.getPassword());
        return userServiceFeignClient.save(newUser);
    }

    @Override
    public JwtToken login(LoginRequestDTO loginRequest) throws ExpiredJwtException {
        User user = userServiceFeignClient.getByLogin(loginRequest.getLogin());
        final String token = tokenProvider.generateToken(user);
        final String refreshToken = tokenProvider.generateRefreshToken(user);

        JwtToken jwtToken = new JwtToken(token, refreshToken);
        tokenSotre.storeToken(jwtToken);
        return jwtToken;
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
        tokenSotre.removeToken(accessToken);
    }
}

