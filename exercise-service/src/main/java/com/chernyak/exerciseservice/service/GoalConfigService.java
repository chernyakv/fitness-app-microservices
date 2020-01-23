package com.chernyak.exerciseservice.service;


import com.chernyak.exerciseservice.entity.enums.GoalType;
import com.chernyak.exerciseservice.entity.GoalConfig;

public interface GoalConfigService {
    GoalConfig get(GoalType type);
    GoalConfig save(GoalConfig config);
    GoalConfig update(GoalConfig config);
}
