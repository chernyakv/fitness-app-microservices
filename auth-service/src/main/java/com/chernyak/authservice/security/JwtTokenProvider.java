package com.chernyak.authservice.security;

import com.chernyak.authservice.entity.User;
import com.chernyak.authservice.service.impl.TokenStoreImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;


@Component
public class JwtTokenProvider implements Serializable {

    @Autowired
    TokenStoreImpl tokenStore;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityJwtConstants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }

    public String generateToken(User user) {

        return Jwts.builder()
                .setSubject(user.getLogin())
                .signWith(SignatureAlgorithm.HS256, SecurityJwtConstants.SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityJwtConstants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public String generateRefreshToken(User user) {
        return  Jwts.builder()
                .setSubject(user.getLogin())
                .signWith(SignatureAlgorithm.HS256, SecurityJwtConstants.SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + SecurityJwtConstants.REFRESH_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public boolean validateToken(String token, User userDetails) {
        tokenStore.checkToken(token);
        final String userName = getUsernameFromToken(token);
        boolean test = userName.equals(userDetails.getLogin()) && !isTokenExpired(token);
        return test;
    }

}
