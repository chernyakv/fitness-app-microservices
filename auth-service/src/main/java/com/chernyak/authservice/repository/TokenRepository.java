package com.chernyak.authservice.repository;

import com.chernyak.authservice.entity.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<JwtToken, String> {
    Optional<JwtToken> findByAccessToken(String accessToken);
}
