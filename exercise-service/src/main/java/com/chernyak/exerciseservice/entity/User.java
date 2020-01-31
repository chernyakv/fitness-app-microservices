package com.chernyak.exerciseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {
    @Id
    private String id;

    private String login;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private int age;

    private int height;

    private int weight;

    private String avatar;

    private Goal goal;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
