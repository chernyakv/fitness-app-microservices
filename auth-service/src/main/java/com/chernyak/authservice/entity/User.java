package com.chernyak.authservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private String id;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private int age;

    private int height;

    private int weight;

    private String avatar;

    private boolean hasGoal;

    private Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
