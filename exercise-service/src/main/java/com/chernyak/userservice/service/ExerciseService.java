package com.chernyak.userservice.service;

import com.chernyak.userservice.entity.Exercise;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseService {
    Exercise getExerciseForToday(String userId);
    List<Exercise> getByUserIdAndDate(String userId, LocalDate date);
    Exercise save(Exercise exercise);
    Exercise update(String id, Exercise exercise);
}
