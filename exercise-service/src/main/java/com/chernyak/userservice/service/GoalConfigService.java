package com.chernyak.userservice.service;


import com.chernyak.userservice.entity.enums.GoalType;
import com.chernyak.userservice.entity.GoalConfig;

public interface GoalConfigService {
    GoalConfig get(GoalType type);
    GoalConfig save(GoalConfig config);
    GoalConfig update(GoalConfig config);
}
