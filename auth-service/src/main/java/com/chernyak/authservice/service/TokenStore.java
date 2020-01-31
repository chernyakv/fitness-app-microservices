package com.chernyak.authservice.service;

import com.chernyak.authservice.entity.JwtToken;

public interface TokenStore {
    JwtToken storeToken(JwtToken token);
    void removeToken(String accessToken);
    JwtToken checkToken(String accessToken);
}
