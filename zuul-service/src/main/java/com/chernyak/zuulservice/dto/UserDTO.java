package com.chernyak.zuulservice.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String id;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

}
