package com.chernyak.exerciseservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    public static final String DEFAULT_USER_LOGIN = "default";
    public static final String DEFAULT_USER_PASSWORD = "123456";

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

    private boolean hasGoal;

    @DBRef
    private Role role;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
