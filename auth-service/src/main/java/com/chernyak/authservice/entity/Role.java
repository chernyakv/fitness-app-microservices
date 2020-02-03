package com.chernyak.authservice.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Role implements GrantedAuthority {
    private String id;

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
