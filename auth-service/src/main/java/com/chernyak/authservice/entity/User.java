package com.chernyak.authservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public static final String DEFAULT_USER_LOGIN = "default";
    public static final String DEFAULT_USER_PASSWORD = "123456";

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
