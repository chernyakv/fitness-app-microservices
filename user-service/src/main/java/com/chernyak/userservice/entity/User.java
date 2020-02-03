package com.chernyak.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public static final String DEFAULT_USER_LOGIN = "default";
    public static final String DEFAULT_USER_PASSWORD = "123456";

    @Id
    private String id;

    @Indexed(unique = true)
    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private int age;

    private int height;

    private int weight;

    private String avatar;

    private Goal goal;

    @DBRef
    private Role role;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
