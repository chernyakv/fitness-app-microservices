package com.chernyak.exerciseservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "user_parameter")
public class UserParameters {
    @Id
    private String id;
    private String userId;
    private LocalDateTime date;
    private int weight;
    private int height;

    public UserParameters(String userId, int weight, int height) {
        this.date = LocalDateTime.now();
        this.userId = userId;
        this.height = height;
        this.weight = weight;
    }
}
