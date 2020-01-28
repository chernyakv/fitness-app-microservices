package com.chernyak.userservice.repository;

import com.chernyak.userservice.entity.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> getByUserIdAndDate(String userId, LocalDate date);
}
