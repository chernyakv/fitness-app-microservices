package com.chernyak.userservice.entity;

import com.chernyak.userservice.entity.enums.GoalType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "goal_config")
public class GoalConfig {
    @Id
    String id;
    String activity;
    @Indexed(unique = true)
    GoalType goalType;
    double sleepInHours;
    double weightRatioForCalories;
    double heightRatioForCalories;
    double ageRatioForCalories;
    double weightRatioForWater;
}
