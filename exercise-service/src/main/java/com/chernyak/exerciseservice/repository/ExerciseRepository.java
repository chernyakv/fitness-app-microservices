package com.chernyak.exerciseservice.repository;

import com.chernyak.exerciseservice.entity.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> getByUserIdAndDate(String userId, LocalDate date);
}
