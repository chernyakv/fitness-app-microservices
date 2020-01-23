package com.chernyak.exerciseservice.repository;

import com.chernyak.exerciseservice.entity.UserParameters;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserParameterRepository extends MongoRepository<UserParameters, String> {
    @Query("{'userId' : {$eq : ?0}, 'date' : {$gte : ?1, $lte : ?2}}")
    List<UserParameters> getUserParametersForPeriod(String id, LocalDate from, LocalDate to, Sort sort);
}
