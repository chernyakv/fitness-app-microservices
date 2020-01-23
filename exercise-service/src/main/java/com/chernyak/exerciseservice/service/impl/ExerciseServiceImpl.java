package com.chernyak.exerciseservice.service.impl;

import com.chernyak.exerciseservice.client.UserServiceFeignClient;
import com.chernyak.exerciseservice.entity.Exercise;
import com.chernyak.exerciseservice.entity.GoalConfig;
import com.chernyak.exerciseservice.entity.User;
import com.chernyak.exerciseservice.entity.enums.GoalType;
import com.chernyak.exerciseservice.repository.ExerciseRepository;
import com.chernyak.exerciseservice.service.ExerciseService;
import com.chernyak.exerciseservice.service.GoalConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    UserServiceFeignClient userServiceFeignClient;
    ExerciseRepository exerciseRepository;
    GoalConfigService goalConfigService;

    @Autowired
    public ExerciseServiceImpl(UserServiceFeignClient userServiceFeignClient, ExerciseRepository exerciseRepository, GoalConfigServiceImpl goalConfigService) {
        this.userServiceFeignClient = userServiceFeignClient;
        this.goalConfigService = goalConfigService;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> getByUserIdAndDate(String userId, LocalDate date) {
        return exerciseRepository.getByUserIdAndDate(userId, date);
    }

    @Override
    public Exercise getExerciseForToday(String userId) {
        User user = userServiceFeignClient.getUser(userId);
        GoalConfig goalConfig = goalConfigService.get(GoalType.LOSE);
        LocalDate date = LocalDate.now();
        List<Exercise> exercise = getByUserIdAndDate(userId, date);
        if (exercise == null || exercise.size() <= 0) {
            int calories = (int) (66.5 + goalConfig.getWeightRatioForCalories() * user.getWeight() + goalConfig.getHeightRatioForCalories() * user.getHeight() - goalConfig.getAgeRatioForCalories() * user.getAge());
            double water = goalConfig.getWeightRatioForWater() * user.getWeight();
            Exercise exercise1 = new Exercise(calories, water, goalConfig.getSleepInHours(), goalConfig.getActivity(), date, userId);
            return save(exercise1);
        } else {
            return exercise.get(0);
        }
    }

    @Override
    public Exercise update(String id, Exercise exercise) {
        Exercise updatedExercise = exerciseRepository.findById(id).get();
        BeanUtils.copyProperties(exercise, updatedExercise, "id");
        return exerciseRepository.save(updatedExercise);
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
