package com.chernyak.authservice.entity;

import lombok.Data;

@Data
public class User {
    public static final String DEFAULT_USER_PASSWORD = "123456";
    public static final String DEFAULT_USER_LOGIN = "default";

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

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
