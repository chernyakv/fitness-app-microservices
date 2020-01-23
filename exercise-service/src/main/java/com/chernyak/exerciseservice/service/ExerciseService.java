package com.chernyak.exerciseservice.service;

import com.chernyak.exerciseservice.entity.Exercise;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseService {
    Exercise getExerciseForToday(String userId);
    List<Exercise> getByUserIdAndDate(String userId, LocalDate date);
    Exercise save(Exercise exercise);
    Exercise update(String id, Exercise exercise);
}
