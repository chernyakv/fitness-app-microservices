package com.chernyak.authservice.service;

import com.chernyak.authservice.entity.JwtToken;

public interface TokenStore {
    public void storeToken(JwtToken token);
    public void removeToken(String accessToken);
    public JwtToken checkToken(String accessToken);
}
