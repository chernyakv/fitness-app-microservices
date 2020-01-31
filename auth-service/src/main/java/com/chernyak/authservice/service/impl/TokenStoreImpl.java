package com.chernyak.authservice.service.impl;

import com.chernyak.authservice.entity.JwtToken;
import com.chernyak.authservice.exception.UserValidationException;
import com.chernyak.authservice.repository.TokenRepository;
import com.chernyak.authservice.service.TokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenStoreImpl implements TokenStore {

    private TokenRepository tokenRepository;

    @Autowired
    public TokenStoreImpl(TokenRepository tokenRepository)  {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public JwtToken storeToken(JwtToken token) {
        return tokenRepository.save(token);
    }

    @Override
    public void removeToken(String accessToken) {
        tokenRepository.deleteById(accessToken);
    }

    @Override
    public JwtToken checkToken(String accessToken) {
        return tokenRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new UserValidationException("Token has been invalid"));
    }
}
