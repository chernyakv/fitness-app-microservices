package com.chernyak.exerciseservice.service.impl;

import com.chernyak.exerciseservice.entity.GoalConfig;
import com.chernyak.exerciseservice.entity.enums.GoalType;
import com.chernyak.exerciseservice.exception.ItemNotFoundException;
import com.chernyak.exerciseservice.repository.GoalConfigRepository;
import com.chernyak.exerciseservice.service.GoalConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalConfigServiceImpl implements GoalConfigService {

    GoalConfigRepository goalConfigRepository;

    @Autowired
    public GoalConfigServiceImpl(GoalConfigRepository goalConfigRepository) {
        this.goalConfigRepository = goalConfigRepository;
    }

    @Override
    public GoalConfig get(GoalType type) {
        return goalConfigRepository.findByGoalType(type)
                .orElseThrow(() -> new ItemNotFoundException("Goal config for goal with type -  " + type.name() + " not found"));
    }

    @Override
    public GoalConfig save(GoalConfig config) {
        return goalConfigRepository.insert(config);
    }

    @Override
    public GoalConfig update(GoalConfig config) {
        GoalConfig newConfig = get(config.getGoalType());
        BeanUtils.copyProperties(config, newConfig, "id", "goalType" );
        return goalConfigRepository.save(newConfig);
    }
}
