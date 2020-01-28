package com.chernyak.zuulservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
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

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
