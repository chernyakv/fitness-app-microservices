package com.chernyak.exerciseservice.repository;

import com.chernyak.exerciseservice.entity.GoalConfig;
import com.chernyak.exerciseservice.entity.enums.GoalType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GoalConfigRepository extends MongoRepository<GoalConfig, String> {
    Optional<GoalConfig> findByGoalType(GoalType goalType);
}
